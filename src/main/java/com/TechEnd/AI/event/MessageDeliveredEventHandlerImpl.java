package com.TechEnd.AI.event;

import com.github.messenger4j.receive.events.MessageDeliveredEvent;
import com.github.messenger4j.receive.handlers.MessageDeliveredEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * Created by ariji on 10/30/2017.
 */
public class MessageDeliveredEventHandlerImpl implements MessageDeliveredEventHandler {
    private static final Logger logger = LoggerFactory.getLogger(MessageDeliveredEventHandlerImpl.class);
    @Override
    public void handle(MessageDeliveredEvent messageDeliveredEvent) {
        logger.debug("Received MessageDeliveredEvent: {}", messageDeliveredEvent);

        final List<String> messageIds = messageDeliveredEvent.getMids();
        final Date watermark = messageDeliveredEvent.getWatermark();
        final String senderId = messageDeliveredEvent.getSender().getId();

        if (messageIds != null) {
            messageIds.forEach(messageId -> {
                logger.info("Received delivery confirmation for message '{}'", messageId);
            });
        }

        logger.info("All messages before '{}' were delivered to user '{}'", watermark, senderId);
    }
}
