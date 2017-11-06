package com.TechEnd.AI.repository;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

import java.util.ArrayList;

/**
 * Created by arijit on 11/3/2017.
 */
public class WatsonConversationClient {
    public String getResponse(String message){
        String plainResponse;
        ConversationService service = new ConversationService(ConversationService.VERSION_DATE_2016_07_11);
        service.setUsernameAndPassword("636bc056-3b7b-4c42-9217-997f8b5695e3", "G5D71T0Bk8bU");
        service.setEndPoint("https://gateway.watsonplatform.net/conversation/api");

        MessageRequest newMessage = new MessageRequest.Builder().inputText(message).build();
        MessageResponse response = service.message("edbe5d3c-e9b3-47ae-9b3a-1ffc3c0e0ac2", newMessage).execute();
       ArrayList arrayList =(ArrayList) response.getOutput().get("text");
       plainResponse=(String)arrayList.get(0);
        System.out.println("full response:"+plainResponse);
        return plainResponse;
    }
}
