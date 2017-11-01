package com.TechEnd.AI.util;

import com.TechEnd.AI.event.*;
import com.github.messenger4j.MessengerPlatform;
import com.github.messenger4j.exceptions.MessengerVerificationException;
import com.github.messenger4j.receive.MessengerReceiveClient;
import com.github.messenger4j.send.MessengerSendClient;
import com.TechEnd.AI.entity.ValidatorBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by arijit on 10/28/2017.
 */

public class authenticationValidator {
    private static final Logger logger = LoggerFactory.getLogger(authenticationValidator.class);
    private final MessengerReceiveClient receiveClient;
    //private final MessengerSendClient sendClient;

    private ValidatorBean validatorBean;

    private MessengerSendClient sendClient ;
    @Autowired
    public authenticationValidator(ValidatorBean validatorBean,MessengerSendClient sendClient) {
        this.sendClient = sendClient;
        logger.debug("Initializing MessengerReceiveClient - appSecret: {} | verifyToken: {}", validatorBean.getAppSecret(), validatorBean.getVerifyToken());
        this.receiveClient = MessengerPlatform.newReceiveClientBuilder(validatorBean.getAppSecret(), validatorBean.getVerifyToken())
                .onTextMessageEvent(new TextMessageEventHandlerImpl(sendClient))
                .onQuickReplyMessageEvent(new QuickReplyMessageEventHandlerImpl(sendClient))
                .onPostbackEvent(new PostbackEventHandlerImpl (sendClient))
                .onAccountLinkingEvent(new AccountLinkingEventHandlerImpl())
                .onOptInEvent(new OptInEventHandlerImpl(sendClient))
                .onEchoMessageEvent(new EchoMessageEventHandlerImpl())
                .onMessageDeliveredEvent(new MessageDeliveredEventHandlerImpl())
                .onMessageReadEvent(new MessageReadEventHandlerImpl())
                .fallbackEventHandler(new FallbackEventHandlerImpl())
                .build();
        // TODO check
    }

    public MessengerReceiveClient getReceiveClient() {
        return receiveClient;
    }

    public MessengerSendClient getSendClient() {
        return sendClient;
    }

    public String verify(String mode, String varifyToken, String challenge) throws MessengerVerificationException {
        return this.receiveClient.verifyWebhook(mode, varifyToken, challenge);
    }
}
