package com.challenge.twitterapi.dao;

import com.challenge.twitterapi.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

/**
 * Interface that allows accessing the data of the Tweet entity
 */
public interface TweetRepository extends JpaRepository<Tweet, Integer> {

    /**
     * Get validated tweets from a user
     * @param user Screen name of user
     * @param validation Validate field
     * @return Tweet collection with the user and validate specified
     */
    Collection<Tweet> findByUserAndValidation(String user, Boolean validation);

    /**
     * Get tweet through your id
     * @param id Tweet id
     * @return Tweet with the id specified
     */
    Tweet findById(Long id);

}