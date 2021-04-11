package com.challenge.twitterapi.service;

import com.challenge.twitterapi.dto.TweetDto;

import java.util.Collection;

/**
 * Interface service that defines the functionality of tweets
 */
public interface ITweetService {

    /**
     * Save Tweet
     * @param tweetDto Tweet that you want to save
     * @return Saved tweet
     */
    TweetDto save(TweetDto tweetDto);

    /**
     * Find all saved tweets
     * @return Tweet collection
     */
    Collection<TweetDto> findAll();

    /**
     * Validate tweet
     * @param id Tweet id
     * @return Validated tweet
     */
    TweetDto validate(Long id);

    /**
     * Find all validated tweets of a user
     * @param user Name of user tweet
     * @return Tweet collecion
     */
    Collection<TweetDto> findByUser(String user);


}
