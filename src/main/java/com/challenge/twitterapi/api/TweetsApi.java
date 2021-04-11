package com.challenge.twitterapi.api;

import com.challenge.twitterapi.dto.HashtagDto;
import com.challenge.twitterapi.dto.TweetDto;
import com.challenge.twitterapi.service.HashtagService;
import com.challenge.twitterapi.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Optional;

/**
 * REST API that returns the actions specified in the challenge
 */
@RestController
public class TweetsApi {

    @Value("${com.challenge.twitterapi.hashtagNumber:10}")
    private Integer hashtagNumber;

    @Autowired
    TweetService tweetService;

    @Autowired
    HashtagService hashtagService;


    /**
     * Get saved tweets
     * @return Collection of saved tweets that satisfy user language and followers restrictions
     */
    @GetMapping("/tweets")
    public Collection<TweetDto> getTweets(){

        return tweetService.findAll();
    }

    /**
     * Validate tweet
     * @param id tweet id
     * @return Validated tweet or throw error if tweet doesn't exist
     */
    @GetMapping("/tweet/validate/{id}")
    public TweetDto validateTweet(@PathVariable("id") Long id){
        try {
            return tweetService.validate(id);
        } catch(EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }

    }

    /**
     * Get validated tweets from a user
     * @param user Screen name user
     * @return Collection of validated tweets
     */
    @GetMapping("/tweets/{user}")
    public Collection<TweetDto> findTweetsByUser(@PathVariable("user") String user){
        return tweetService.findByUser(user);
    }

    /**
     * Get most used hashtags
     * @param number Maximum number of hashtags to display
     * @return Collection of hashtags with their tweets
     */
    @GetMapping(value = {"/hashtags", "/hashtags/{number}"})
    public Collection<HashtagDto> findHashtags(@PathVariable(name = "number", required = false) Optional<Integer> number){
        if (number.isPresent())
            return hashtagService.findHashtags(number.get());
        else
            return hashtagService.findHashtags(hashtagNumber);
    }
}