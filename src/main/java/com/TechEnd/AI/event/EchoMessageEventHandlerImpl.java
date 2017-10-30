package com.TechEnd.AI.event;

import com.github.messenger4j.receive.events.EchoMessageEvent;
import com.github.messenger4j.receive.handlers.EchoMessageEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by ariji on 10/30/2017.
 */
public class EchoMessageEventHandlerImpl implements EchoMessageEventHandler {
    private static final Logger logger = LoggerFactory.getLogger(EchoMessageEventHandlerImpl.class);
    @Override
    public void handle(EchoMessageEvent echoMessageEvent) {
        logger.debug("Received EchoMessageEvent: {}", echoMessageEvent);

        final String messageId = echoMessageEvent.getMid();
        final String recipientId = echoMessageEvent.getRecipient().getId();
        final String senderId = echoMessageEvent.getSender().getId();
        final Date timestamp = echoMessageEvent.getTimestamp();

        logger.info("Received echo for message '{}' that has been sent to recipient '{}' by sender '{}' at '{}'",
                messageId, recipientId, senderId, timestamp);

    }
}
