package com.challenge.twitterapi.service;


import com.challenge.twitterapi.dto.TweetDto;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import twitter4j.*;
import twitter4j.LoggerFactory;

import java.util.List;

/**
 * Service that allows you to read twitter tweets
 */
@Service
public class TwitterStreamReaderService {

    private final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    @Value("${com.challenge.twitterapi.minFollowers:1500}")
    private Integer minFollowers;

    @Value("${com.challenge.twitterapi.languages:es,fr,it}")
    private List<String> languages;


    @Autowired
    TweetService tweetService;

    /**
     * This function is launched when the application starts and consumes
     * the tweets that are published on Twitter
     */
    @EventListener(ApplicationReadyEvent.class)
    public void readTwitterFeed() {

        /**
         * Listener of tweets published on Twitter
         */
        StatusListener listener = new StatusListener() {

            /**
             * Error hanler
             * @param e Exception
             */
            @Override
            public void onException(Exception e) {
                logger.error("Exception occured:" + e.getMessage());
                e.printStackTrace();
            }

            /**
             * Track limitation notice
             * @param n Number of limitations
             */
            @Override
            public void onTrackLimitationNotice(int n) {
                logger.info("Track limitation notice for " + n);
            }

            /**
             * Save tweets whose users have more than a certain number of followers
             * and the content of the tweet is in the supported languages
             * @param status Tweet
             */
            @Override
            public void onStatus(Status status) {
                if(status.getUser().getFollowersCount() > minFollowers && languages.contains(status.getLang())) {
                    TweetDto tweetDto = new TweetDto(status.getId(), status.getUser().getScreenName(), status.getText(),
                            status.getPlace() != null ? status.getPlace().getFullName() +
                                    "(" + status.getPlace().getCountry() + ")" : null);
                    tweetService.save(tweetDto);
                }
            }

            /**
             * Warning handler
             * @param arg0 Warning
             */
            @Override
            public void onStallWarning(StallWarning arg0) {
                logger.warn("Stall warning");
            }

            /**
             * Got scrub_geo event
             * @param arg0 user id
             * @param arg1 up to status id
             */
            @Override
            public void onScrubGeo(long arg0, long arg1) {
                logger.info("Scrub geo with:" + arg0 + ":" + arg1);
            }

            /**
             * Got a status deletion notice id
             * @param arg0 Status deletion notice
             */
            @Override
            public void onDeletionNotice(StatusDeletionNotice arg0) {
                logger.info("Status deletion notice");
            }
        };

        TwitterStream stream = new TwitterStreamFactory().getInstance();


        stream.addListener(listener);
        stream.sample();
    }
}
