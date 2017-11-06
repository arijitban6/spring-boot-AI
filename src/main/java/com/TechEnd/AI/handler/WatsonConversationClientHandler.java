package com.TechEnd.AI.handler;

import com.TechEnd.AI.repository.WatsonConversationClient;

/**
 * Created by ariji on 11/4/2017.
 */
public class WatsonConversationClientHandler {
    WatsonConversationClient watsonConversationClient= new WatsonConversationClient();
    public String getMesaage(String inputeMassage){
       return  watsonConversationClient.getResponse(inputeMassage);
    }
}
