package com.challenge.twitterapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Tweet entitiy
 */
@Entity
public class Tweet{

    @Id
    private Long id;
    private String user;
    @Lob
    private String text;
    private String location;
    private Boolean validation;

    @ManyToMany()
    private Set<Hashtag> hashtags = new HashSet<>();

    /**
     * Constructor
     */
    public Tweet(){

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
     * @return user
     */
    public String getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     *
     * @return text
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     *
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     *
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     *
     * @return validation
     */
    public Boolean getValidation() {
        return validation;
    }

    /**
     * validation
     * @param validation
     */
    public void setValidation(Boolean validation) {
        this.validation = validation;
    }

    /**
     *
     * @return Set<Hashtag>
     */
    public Set<Hashtag> getHashtags() {
        return hashtags;
    }

    /**
     *
     * @param hashtags
     */
    public void setHashtags(Set<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }
}