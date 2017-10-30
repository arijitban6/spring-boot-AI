package com.TechEnd.AI.event;

import com.TechEnd.AI.handler.SendTextMessage;
import com.TechEnd.AI.util.handleIOException;
import com.TechEnd.AI.util.handleSendException;
import com.github.messenger4j.exceptions.MessengerApiException;
import com.github.messenger4j.exceptions.MessengerIOException;
import com.github.messenger4j.receive.events.QuickReplyMessageEvent;
import com.github.messenger4j.receive.handlers.QuickReplyMessageEventHandler;
import com.github.messenger4j.send.MessengerSendClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ariji on 10/30/2017.
 */
public class QuickReplyMessageEventHandlerImpl implements QuickReplyMessageEventHandler {
    private static final Logger logger = LoggerFactory.getLogger(QuickReplyMessageEventHandlerImpl.class);
    public static final String GOOD_ACTION = "DEVELOPER_DEFINED_PAYLOAD_FOR_GOOD_ACTION";
    public static final String NOT_GOOD_ACTION = "DEVELOPER_DEFINED_PAYLOAD_FOR_NOT_GOOD_ACTION";

    private com.TechEnd.AI.handler.SendTextMessage SendTextMessage;

    public QuickReplyMessageEventHandlerImpl(MessengerSendClient sendClient) {
        this.SendTextMessage = new SendTextMessage(sendClient);
    }

    @Override
    public void handle(QuickReplyMessageEvent quickReplyMessageEvent) {
        logger.debug("Received QuickReplyMessageEvent: {}", quickReplyMessageEvent);

        final String senderId = quickReplyMessageEvent.getSender().getId();
        final String messageId = quickReplyMessageEvent.getMid();
        final String quickReplyPayload = quickReplyMessageEvent.getQuickReply().getPayload();

        logger.info("Received quick reply for message '{}' with payload '{}'", messageId, quickReplyPayload);


        try {
            if(quickReplyPayload.equals(GOOD_ACTION))
                this.SendTextMessage.sendGif(senderId, "https://media.giphy.com/media/3oz8xPxTUeebQ8pL1e/giphy.gif");
            else
                this.SendTextMessage.sendGif(senderId, "https://media.giphy.com/media/26ybx7nkZXtBkEYko/giphy.gif");
        } catch (MessengerApiException e) {
            logger.error(new handleSendException().toString(),e);
        } catch (MessengerIOException e) {
            logger.error(new handleIOException().toString(),e);
        }

        this.SendTextMessage.sendText(senderId, "Let's try another one :D!");
    }

}
