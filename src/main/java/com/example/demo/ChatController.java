package com.example.demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;


@RestController
public class ChatController {
    
    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${openai.model}")
    private String model;
    
    @Value("${openai.api.url}")
    private String apiUrl;
    
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/chat")
    public JsonResponse chat(@RequestParam String prompt) {
        // create a request
        ChatRequest request = new ChatRequest(model, prompt + " salary range one sentence");
        
        // call the API
        ChatResponse response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);
        
        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            return new JsonResponse("No response", "no response");
        }
        
        ChatRequest request2 = new ChatRequest(model, prompt + " education requirements one sentence");
        
        // call the API
        ChatResponse response2 = restTemplate.postForObject(apiUrl, request2, ChatResponse.class);
        
        if (response2 == null || response2.getChoices() == null || response2.getChoices().isEmpty()) {
            return new JsonResponse("No response", "no response");
        }

        // create and return a JSON response object
        return new JsonResponse(
                response.getChoices().get(0).getMessage().getContent(),
                response2.getChoices().get(0).getMessage().getContent()
        );
    }
}
