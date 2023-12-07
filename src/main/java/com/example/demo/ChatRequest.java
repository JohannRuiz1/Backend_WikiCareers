/*  ChatRequest.java WikiCareers (Mike Lundquist) Virginia Tech
class used in chatcontroller.java for the request to api
December 2023
*/ 
package com.example.demo;
import java.util.ArrayList;
import java.util.List;
public class ChatRequest {
    private String model;
    private List<Message> messages;

    public ChatRequest(String model, String prompt) {
        this.model = model;

        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", prompt));
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}