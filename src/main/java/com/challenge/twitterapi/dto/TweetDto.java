package com.challenge.twitterapi.dto;

import java.io.Serializable;

/**
 * Dto Tweet class
 */
public class TweetDto implements Serializable {

    private static final long serialVersionUID = 4894729030347835498L;

    private Long id;
    private String user;
    private String text;
    private String location;
    private Boolean validation = false;

    /**
     * Constructor
     */
    public TweetDto(){

    }

    /**
     * Alternative constructor
     * @param id Tweet id
     * @param user Tweet user
     * @param text Tweet content
     * @param location Tweet location
     */
    public TweetDto(Long id, String user, String text, String location) {
        this.id = id;
        this.user = user;
        this.text = text;
        this.location = location;
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
     *
     * @param validation
     */
    public void setValidation(Boolean validation) {
        this.validation = validation;
    }

    /**
     *
     * @return String with the data object
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TweetDto{")
                .append("id=").append(this.id)
                .append(", user='").append(this.user).append('\'')
                .append(", text='").append(this.text).append('\'')
                .append(", location='").append(this.location).append('\'')
                .append(", validation=").append(this.validation)
                .append('}');

        return builder.toString();
    }

}