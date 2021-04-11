package com.challenge.twitterapi.dao;

import com.challenge.twitterapi.model.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Interface that allows accessing the data of the Hashtag entity
 */
public interface HashtagRepository extends JpaRepository<Hashtag, Integer> {

    /**
     * Get Hashtag from a key
     * @param key Name of the hashtag
     * @return Hashtag with the key specified
     */
    Hashtag findByKey(String key);

}