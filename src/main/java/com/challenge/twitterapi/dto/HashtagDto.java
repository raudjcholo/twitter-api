package com.challenge.twitterapi.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Dto Hashtag class
 */
public class HashtagDto implements Serializable {

    private static final long serialVersionUID = 489472903034783548L;

    private Long id;
    private String key;
    private Long replays;

    private Set<TweetDto> tweets = new HashSet<>();

    /**
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return key
     */
    public String getKey() {
        return key;
    }

    /**
     *
     * @param key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     *
     * @return replays
     */
    public Long getReplays() {
        return replays;
    }

    /**
     *
     * @param replays
     */
    public void setReplays(Long replays) {
        this.replays = replays;
    }

    /**
     *
     * @return Set<Tweet>
     */
    public Set<TweetDto> getTweets() {
        return tweets;
    }

    /**
     *
     * @param tweets
     */
    public void setTweets(Set<TweetDto> tweets) {
        this.tweets = tweets;
    }

    /**
     * Constructor
     */
    public HashtagDto(){

    }

    /**
     *
     * @return String with the data object
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("HashtagDto{")
                .append("id=").append(this.id)
                .append(", key='").append(this.key).append('\'')
                .append(", replays=").append(this.replays)
                .append('}');

        return builder.toString();
    }

}