package com.TechEnd.AI.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ariji on 10/28/2017.
 */
public class handleSendException extends Exception {

    @Override
    public String toString() {
        return "Message could not be sent. An unexpected error occurred.";
    }
}
