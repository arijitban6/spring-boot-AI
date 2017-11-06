package com.TechEnd.AI.event;

import com.TechEnd.AI.handler.WatsonConversationClientHandler;
import com.TechEnd.AI.util.authenticationValidator;
import com.TechEnd.AI.util.handleSendException;
import com.github.messenger4j.exceptions.MessengerApiException;
import com.github.messenger4j.exceptions.MessengerIOException;
import com.github.messenger4j.receive.events.TextMessageEvent;
import com.github.messenger4j.receive.handlers.TextMessageEventHandler;
import com.github.messenger4j.send.MessengerSendClient;
import com.TechEnd.AI.handler.SendTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by ariji on 10/30/2017.
 */
public class TextMessageEventHandlerImpl implements TextMessageEventHandler {

    private static final Logger logger = LoggerFactory.getLogger(authenticationValidator.class);

    private SendTextMessage SendTextMessage;
    private Action Action;
    private WatsonConversationClientHandler watsonConversationClientHandler= new WatsonConversationClientHandler();

    public TextMessageEventHandlerImpl (MessengerSendClient sendClient) {
        this.SendTextMessage = new SendTextMessage(sendClient);
        this.Action = new Action(sendClient);
    }
    @Override
    public void handle(TextMessageEvent textMessageEvent) {

        logger.debug("Received TextMessageEvent: {}", textMessageEvent);

        final String messageId = textMessageEvent.getMid();
        final String messageText = textMessageEvent.getText();
        final String senderId = textMessageEvent.getSender().getId();
        final Date timestamp = textMessageEvent.getTimestamp();

        logger.info("Received message '{}' with text '{}' from user '{}' at '{}'",
                messageId, messageText, senderId, timestamp);

        try {
            Action.sendReadReceipt(senderId);
            Action.sendTypingOn(senderId);
            SendTextMessage.sendText(senderId, watsonConversationClientHandler.getMesaage(messageText));
            Action.sendTypingOff(senderId);
            /*switch (messageText.toLowerCase()) {


                case "yo":
                    SendTextMessage.sendText(senderId, "Hello, What I can do for you ? Type the word you're looking for");
                    break;

                case "great":
                    SendTextMessage.sendText(senderId, "You're welcome :) keep rocking");
                    break;


                default:
                    Action.sendReadReceipt(senderId);
                    Action.sendTypingOn(senderId);
                    //TODO gif
                    //sendSpringDoc(senderId, messageText);
                    //sendQuickReply(senderId);
                    Action.sendTypingOff(senderId);*/
            //}
        } catch (MessengerApiException | MessengerIOException e) {
            logger.error(new handleSendException().toString(),e);
        } /*catch (IOException e) {
            logger.error(new handleIOException().toString(),e);
            TODO gif exception
        }*/
    }
}
