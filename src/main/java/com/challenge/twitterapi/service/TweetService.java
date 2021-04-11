package com.challenge.twitterapi.service;

import com.challenge.twitterapi.conf.DozerUtil;
import com.challenge.twitterapi.dao.TweetRepository;
import com.challenge.twitterapi.dto.HashtagDto;
import com.challenge.twitterapi.dto.TweetDto;
import com.challenge.twitterapi.model.Hashtag;
import com.challenge.twitterapi.model.Tweet;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Service that implements the functionality of tweets
 */
@Service
public class TweetService implements ITweetService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TweetRepository dao;

    @Autowired
    HashtagService hashtagService;

    @Autowired
    Mapper mapper;

    /**
     * Save Tweet. Process the hashtags of the tweet and save them updating their number of repetitions
     * @param tweetDto Tweet that you want to save
     * @return Saved tweet
     */
    @Override
    @Transactional
    public TweetDto save(TweetDto tweetDto){

        Tweet tweetModel = mapper.map(tweetDto, Tweet.class);

        tweetModel.setHashtags(findHastags(tweetModel));

        tweetDto = mapper.map(dao.saveAndFlush(tweetModel), TweetDto.class);

        logger.info("TweetDto saved: " + tweetDto.toString());

        return tweetDto;
    }

    /**
     * Find all saved tweets
     * @return Tweet collection
     */
    @Override
    public Collection<TweetDto> findAll(){
        return DozerUtil.collectionMapper(dao.findAll(), TweetDto.class, mapper);
    }

    /**
     * Validate tweet
     * @param id Tweet id
     * @return Validated tweet
     */
    @Override
    public TweetDto validate(Long id){
        com.challenge.twitterapi.model.Tweet tweet = dao.findById(id);

        if(tweet == null)
            throw new EntityNotFoundException("Tweet not found");

        tweet.setValidation(true);
        return mapper.map(dao.saveAndFlush(tweet), TweetDto.class);
    }

    /**
     * Find all validated tweets of a user
     * @param user Name of user tweet
     * @return Tweet collecion
     */
    @Override
    public Collection<TweetDto> findByUser(String user){
        return DozerUtil.collectionMapper(dao.findByUserAndValidation(user, true), TweetDto.class, mapper);
    }

    /**
     * Find hashtags in tweet content, save them updating their number of repetitions
     * and return the updated hashtags set
     * @param tweet Tweet
     * @return Set<Hashtags>
     */
    private Set<Hashtag> findHastags(Tweet tweet){

        Pattern pattern = Pattern.compile("[#]+[A-Za-z0-9-_]+\\b");
        Matcher matcher = pattern.matcher(tweet.getText());

        Set<Hashtag> hashtags = new HashSet<>();
        while(matcher.find()){
            HashtagDto hashtagDto = hashtagService.findByKey(matcher.group());
            Hashtag hashtag = null;
            if(hashtagDto == null)
                hashtag = new Hashtag(matcher.group(), new Long(1));
            else {
                hashtag = mapper.map(hashtagDto, Hashtag.class);
                hashtag.setReplays(hashtag.getReplays() + 1);
            }
            hashtagDto = hashtagService.save(mapper.map(hashtag, HashtagDto.class));
            hashtags.add(mapper.map(hashtagDto, Hashtag.class));
        }

        return hashtags;

    }


}