package com.challenge.twitterapi.service;


import com.challenge.twitterapi.dto.HashtagDto;

import java.util.Collection;

/**
 * Interface service that defines the functionality of hashtags
 */
public interface IHashtagService {

    /**
     * Save Hashtag
     * @param hashtagDto Hashtag that you want to save
     * @return Saved Hashtag
     */
    HashtagDto save(HashtagDto hashtagDto);

    /**
     * Find the most used hashtags
     * @param number Maximum number of hashtags
     * @return Hashtag collection
     */
    Collection<HashtagDto> findHashtags(Integer number);

    /**
     * Find the hashtag with the indicated key
     * @param key Hastahg name
     * @return Hashtag
     */
    HashtagDto findByKey(String key);

}
