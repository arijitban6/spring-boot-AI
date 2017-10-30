package com.TechEnd.AI.event;

import com.github.messenger4j.receive.events.AccountLinkingEvent;
import com.github.messenger4j.receive.handlers.AccountLinkingEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ariji on 10/30/2017.
 */
public class AccountLinkingEventHandlerImpl implements AccountLinkingEventHandler {
    private static final Logger logger = LoggerFactory.getLogger(AccountLinkingEventHandlerImpl.class);

    @Override
    public void handle(AccountLinkingEvent accountLinkingEvent) {
        logger.debug("Received AccountLinkingEvent: {}", accountLinkingEvent);

        final String senderId = accountLinkingEvent.getSender().getId();
        final AccountLinkingEvent.AccountLinkingStatus accountLinkingStatus = accountLinkingEvent.getStatus();
        final String authorizationCode = accountLinkingEvent.getAuthorizationCode();

        logger.info("Received account linking event for user '{}' with status '{}' and auth code '{}'",
                senderId, accountLinkingStatus, authorizationCode);
    }
}
