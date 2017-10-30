package com.TechEnd.AI.event;

import com.github.messenger4j.receive.events.OptInEvent;
import com.github.messenger4j.receive.handlers.OptInEventHandler;
import com.github.messenger4j.send.MessengerSendClient;
import com.TechEnd.AI.handler.SendTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by ariji on 10/30/2017.
 */
public class OptInEventHandlerImpl implements OptInEventHandler {

    private SendTextMessage SendTextMessage;

    public OptInEventHandlerImpl(MessengerSendClient sendClient) {
        this.SendTextMessage = new SendTextMessage(sendClient);
    }

    private static final Logger logger = LoggerFactory.getLogger(OptInEventHandlerImpl.class);
    @Override
    public void handle(OptInEvent optInEvent) {
        logger.debug("Received OptInEvent: {}", optInEvent);

        final String senderId = optInEvent.getSender().getId();
        final String recipientId = optInEvent.getRecipient().getId();
        final String passThroughParam = optInEvent.getRef();
        final Date timestamp = optInEvent.getTimestamp();

        logger.info("Received authenticationValidator for user '{}' and page '{}' with pass through param '{}' at '{}'",
                senderId, recipientId, passThroughParam, timestamp);

        SendTextMessage.sendText(senderId, "Authentication successful");
    }
}
