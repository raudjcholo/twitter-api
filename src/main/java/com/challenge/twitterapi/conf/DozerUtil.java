package com.challenge.twitterapi.conf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dozer.Mapper;

/**
 * Class of utilities to be able to map sets of objects
 */
public class DozerUtil {

    /**
     * Function that converts a collection of data of one type to another collection of data of a specified type
     * @param data Data collection
     * @param clzz Class to map to
     * @param mapper Mapping class
     * @param <T> Any type of object
     * @return Data oollection of the new class
     */
    public static <T> Collection<T> collectionMapper(Collection<?> data, Class<T> clzz, Mapper mapper) {
        return collectionListMapper(data, clzz, mapper);
    }

    /**
     * Function that converts a collection of data of one type to list of data of a specified type
     * @param data Data collection
     * @param clzz Class to map to
     * @param mapper Mapping class
     * @param <T> Any type of object
     * @return Data list of the new class
     */
    public static <T> List<T> collectionListMapper(Collection<?> data, Class<T> clzz, Mapper mapper) {
        List<T> result = new ArrayList<T>(data.size());
        T dto = null;
        for (Object item : data) {
            dto = mapper.map(item, clzz);
            result.add(dto);
        }
        return result;
    }

}
