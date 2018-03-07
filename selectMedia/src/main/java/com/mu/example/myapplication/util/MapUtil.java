package com.mu.example.myapplication.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by mu on 2018/3/6.
 */

public class MapUtil {
    public static <T, V extends List> boolean contains(Map<T, V> map, Object o, Set<T> keys) {
        for (T key : keys) {
            V value = map.get(key);
            boolean result = value.contains(o);
            if (result) {
                return result;
            }
        }
        return false;
    }
}
