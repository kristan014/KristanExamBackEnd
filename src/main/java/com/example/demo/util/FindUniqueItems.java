package com.example.demo.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindUniqueItems {
    
    // return unique items in a list
    public static <T> List<T> findUniqueItems(List<T> list) {
        Set<T> uniqueSet = new HashSet<>(list);
        return new ArrayList<>(uniqueSet);
    }
}
