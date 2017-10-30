package com.TechEnd.AI.event;

import com.github.messenger4j.receive.events.PostbackEvent;
import com.github.messenger4j.receive.handlers.PostbackEventHandler;
import com.github.messenger4j.send.MessengerSendClient;
import com.TechEnd.AI.handler.SendTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by ariji on 10/30/2017.
 */
public class PostbackEventHandlerImpl implements PostbackEventHandler {

    private static final Logger logger = LoggerFactory.getLogger(PostbackEventHandlerImpl.class);

    private SendTextMessage SendTextMessage;

    public PostbackEventHandlerImpl(MessengerSendClient sendClient) {
        this.SendTextMessage = new SendTextMessage(sendClient);
    }

    @Override
    public void handle(PostbackEvent postbackEvent) {
        logger.debug("Received PostbackEvent: {}", postbackEvent);

        final String senderId = postbackEvent.getSender().getId();
        final String recipientId = postbackEvent.getRecipient().getId();
        final String payload = postbackEvent.getPayload();
        final Date timestamp = postbackEvent.getTimestamp();

        logger.info("Received postback for user '{}' and page '{}' with payload '{}' at '{}'",
                senderId, recipientId, payload, timestamp);

        SendTextMessage.sendText(senderId, "Postback called");
    }
}
