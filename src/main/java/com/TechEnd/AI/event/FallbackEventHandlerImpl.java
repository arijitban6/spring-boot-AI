package com.TechEnd.AI.event;

import com.github.messenger4j.receive.events.FallbackEvent;
import com.github.messenger4j.receive.handlers.FallbackEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ariji on 10/30/2017.
 */
public class FallbackEventHandlerImpl implements FallbackEventHandler {

    private static final Logger logger = LoggerFactory.getLogger(FallbackEventHandlerImpl.class);
    /**
     * This handler is called when either the message is unsupported or when the event handler for the actual event type
     * is not registered. In this showcase all event handlers are registered. Hence only in case of an
     * unsupported message the fallback event handler is called.
     */
    @Override
    public void handle(FallbackEvent fallbackEvent) {
        logger.debug("Received FallbackEvent: {}", fallbackEvent);

        final String senderId = fallbackEvent.getSender().getId();
        logger.info("Received unsupported message from user '{}'", senderId);
    }
}
