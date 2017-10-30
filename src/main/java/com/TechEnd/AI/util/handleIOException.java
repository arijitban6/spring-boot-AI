package com.TechEnd.AI.util;

/**
 * Created by ariji on 10/28/2017.
 */
public class handleIOException extends Exception {
    @Override
    public String toString() {
        return "Could not open Spring.io page. An unexpected error occurred.";
    }
}
