package com.example.demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.example.demo.models.Career;
import com.example.demo.models.Education;
import com.example.demo.repository.CareerRepository;
import com.example.demo.repository.EducationRepository;


@RestController
public class ChatController {
    
    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${openai.model}")
    private String model;
    
    @Value("${openai.api.url}")
    private String apiUrl;

    @Autowired
    private CareerRepository careerRepo;

    @Autowired
    private EducationRepository educationRepo;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/chatDB")
    public JsonResponse chatInDB(@RequestParam String prompt) {
        
        return null;
    }
    
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


        JsonResponse json = new JsonResponse(
                response.getChoices().get(0).getMessage().getContent(),
                response2.getChoices().get(0).getMessage().getContent()
        );
        parseInformationToDatabase(json, prompt);
        // create and return a JSON response objecT

        return json;
    }
    
    private void parseInformationToDatabase(JsonResponse input, String prompt){
        String salaryRange = input.getSalaryRange();

        
        String salaryPattern = "\\$([0-9,]+) (and|to) \\$([0-9,]+)";
        Pattern pattern = Pattern.compile(salaryPattern);
        Matcher matcher = pattern.matcher(salaryRange);
        String lowSalary = "";
        String highSalary = "";
        double lSal = 0.0;
        double hSal = 0.0;
        if (matcher.find()) {
            lowSalary = matcher.group(1);
            highSalary = matcher.group(3);

            System.out.println("Low Salary: $" + lowSalary);
            System.out.println("High Salary: $" + highSalary);
        }

        try{
            lowSalary = lowSalary.replace(",", "");
            highSalary = highSalary.replace(",", "");
            lSal = Double.parseDouble(lowSalary);
            hSal = Double.parseDouble(highSalary);
        } catch (Exception e){
            System.out.println("Dang that's crazy");
        }
        
        Career career =  new Career(prompt, "ParsingDescription",lSal, hSal, 0);
        careerRepo.save(career);
        
        List<Career> careers = careerRepo.findByTitleContaining(prompt);
        int careerId = careers.get(0).getCareer_id();

        String educationRequirements = input.getEducationRequirements();

        String educationPattern = "(need|requires) a (.+)";
        pattern = Pattern.compile(educationPattern);
        matcher = pattern.matcher(educationRequirements);

        if (matcher.find()) {
            String requirementsText = matcher.group(2);
            System.out.println("Education Requirements: " + requirementsText);
        }

        Education education = new Education(careerId, educationRequirements, 4, "Education Description");
        educationRepo.save(education);
        
    }
}
