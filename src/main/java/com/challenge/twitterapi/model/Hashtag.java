package com.challenge.twitterapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Hashtag Entity
 */
@Entity
public class Hashtag{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String key;
    private Long replays;

    @ManyToMany(mappedBy = "hashtags")
    private Set<Tweet> tweets = new HashSet<>();

    /**
     * Constructor
     */
    public Hashtag(){

    }

    /**
     * Alternative constructor
     * @param key Hashtag name
     * @param replays Number of times the hashtag is repeated
     */
    public Hashtag(String key, Long replays) {
        this.key = key;
        this.replays = replays;
    }

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
    public Set<Tweet> getTweets() {
        return tweets;
    }

    /**
     *
     * @param tweets
     */
    public void setTweets(Set<Tweet> tweets) {
        this.tweets = tweets;
    }
}