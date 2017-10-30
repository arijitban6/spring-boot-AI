package com.TechEnd.AI.event;

import com.github.messenger4j.receive.events.MessageReadEvent;
import com.github.messenger4j.receive.handlers.MessageReadEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by ariji on 10/30/2017.
 */
public class MessageReadEventHandlerImpl implements MessageReadEventHandler {
    private static final Logger logger = LoggerFactory.getLogger(MessageDeliveredEventHandlerImpl.class);
    @Override
    public void handle(MessageReadEvent messageReadEvent) {

        logger.debug("Received MessageReadEvent: {}", messageReadEvent);

        final Date watermark = messageReadEvent.getWatermark();
        final String senderId = messageReadEvent.getSender().getId();

        logger.info("All messages before '{}' were read by user '{}'", watermark, senderId);
    }
}
