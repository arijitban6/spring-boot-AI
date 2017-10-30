package com.TechEnd.AI.handler;

import com.github.messenger4j.exceptions.MessengerApiException;
import com.github.messenger4j.exceptions.MessengerIOException;
import com.github.messenger4j.send.MessengerSendClient;
import com.github.messenger4j.send.NotificationType;
import com.github.messenger4j.send.QuickReply;
import com.github.messenger4j.send.Recipient;

import java.util.List;

/**
 * Created by ariji on 10/28/2017.
 */
public class SendTextMessage {
    public static final String GOOD_ACTION = "DEVELOPER_DEFINED_PAYLOAD_FOR_GOOD_ACTION";
    public static final String NOT_GOOD_ACTION = "DEVELOPER_DEFINED_PAYLOAD_FOR_NOT_GOOD_ACTION";
    private MessengerSendClient sendClient;

    public SendTextMessage(MessengerSendClient sendClient) {
        this.sendClient = sendClient;
    }

    public void sendText(String recipientId, String text) {
        try {
            final Recipient recipient = Recipient.newBuilder().recipientId(recipientId).build();
            final NotificationType notificationType = NotificationType.REGULAR;
            final String metadata = "DEVELOPER_DEFINED_METADATA";

            this.sendClient.sendTextMessage(recipient, notificationType, text, metadata);
        } catch (MessengerApiException | MessengerIOException e) {
            //handleSendException(e);
        }
    }
    public void sendGif(String recipientId, String gif) throws MessengerApiException, MessengerIOException {
        this.sendClient.sendImageAttachment(recipientId, gif);
    }

    void sendQuickReply(String recipientId) throws MessengerApiException, MessengerIOException {
        final List<QuickReply> quickReplies = QuickReply.newListBuilder()
                .addTextQuickReply("Looks good", GOOD_ACTION).toList()
                .addTextQuickReply("Nope!", NOT_GOOD_ACTION).toList()
                .build();

        this.sendClient.sendTextMessage(recipientId, "Was this helpful?!", quickReplies);
    }

}
