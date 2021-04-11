package com.challenge.twitterapi.service;

import com.challenge.twitterapi.conf.DozerUtil;
import com.challenge.twitterapi.dao.HashtagRepository;
import com.challenge.twitterapi.dto.HashtagDto;
import com.challenge.twitterapi.model.Hashtag;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Service that implements the functionality of hashtags
 */
@Service
public class HashtagService implements IHashtagService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    HashtagRepository dao;

    @Autowired
    Mapper mapper;

    /**
     * Save Hashtag
     * @param hashtagDto Hashtag that you want to save
     * @return Saved Hashtag
     */
    @Override
    public HashtagDto save(HashtagDto hashtagDto){
        Hashtag hashtag = mapper.map(hashtagDto, Hashtag.class);

        hashtagDto = mapper.map(dao.saveAndFlush(hashtag), HashtagDto.class);

        logger.info("HashtagDto saved: " + hashtagDto.toString());
        return hashtagDto;
    }

    /**
     * Find the most used hashtags
     * @param number Maximum number of hashtags
     * @return Hashtag collection
     */
    @Override
    public Collection<HashtagDto> findHashtags(Integer number){
        Page<Hashtag> hashtags =  dao.findAll(PageRequest.of(0, number, Sort.by(Sort.Direction.DESC, "replays")));
        return DozerUtil.collectionMapper(hashtags.getContent(), HashtagDto.class, mapper);
    }

    /**
     * Find the hashtag with the indicated key
     * @param key Hastahg name
     * @return Hashtag
     */
    @Override
    public HashtagDto findByKey(String key){
        Hashtag hashtag = dao.findByKey(key);

        if (hashtag == null)
            return null;
        else
            return mapper.map(hashtag, HashtagDto.class);
    }


}