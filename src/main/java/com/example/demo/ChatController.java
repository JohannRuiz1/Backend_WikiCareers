package com.example.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.example.demo.models.Career;
import com.example.demo.models.Education;
import com.example.demo.models.Risk;
import com.example.demo.models.CareerInfo;
import com.example.demo.repository.CareerRepository;
import com.example.demo.repository.EducationRepository;
import com.example.demo.repository.RiskRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



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

    @Autowired
    private RiskRepository riskRepo;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/chatDB")
    public String chatInDB(@RequestParam String name) {
        // Create an ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        
        Career career = careerRepo.findByName(name);

        if(career == null){
            return "No object in database";
        }
        String json = "";

        try {
            // Convert the object to JSON
            json = objectMapper.writeValueAsString(career);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/careerInfo")
    public String careerInfo(@RequestParam String name) {
        // Create an ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        
        CareerInfo career = careerRepo.findCareerInfoByTitle(name);
        
        if(career == null){
            return "No object in database";
        }
        String json = "";

        try {
            // Convert the object to JSON
            json = objectMapper.writeValueAsString(career);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/chat")
    public String chat(@RequestParam String prompt) {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            // NORMALIZE INPUT
            prompt = prompt.toLowerCase();
            
            CareerInfo output = careerRepo.findCareerInfoByTitle(prompt);
            if(output != null){
                return objectMapper.writeValueAsString(output);
            }
            

            // create a request
            ChatRequest request = new ChatRequest(model, prompt + " salary range one sentence with numbers");
            
            // call the API
            ChatResponse response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);
            
            if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
                return objectMapper.writeValueAsString(new ChatTransform("No response", "no response", "no response", "no response"));
            }
            
            ChatRequest request2 = new ChatRequest(model, prompt + " education requirements one sentence");
            
            // call the API
            ChatResponse response2 = restTemplate.postForObject(apiUrl, request2, ChatResponse.class);
            
            if (response2 == null || response2.getChoices() == null || response2.getChoices().isEmpty()) {
                return objectMapper.writeValueAsString(new ChatTransform("No response", "no response", "no response", "no response"));
            }

            
            ChatRequest request3 = new ChatRequest(model, "give a one sentence description about what " +
            prompt);
            
            // call the API
            ChatResponse response3 = restTemplate.postForObject(apiUrl, request3, ChatResponse.class);
            
            if (response3 == null || response3.getChoices() == null || response3.getChoices().isEmpty()) {
                return objectMapper.writeValueAsString(new ChatTransform("No response", "no response", "no response", "no response"));
            }



            ChatRequest request4 = new ChatRequest(model, "give one sentence about the job security of  a" +
            prompt + "'s do");
            
            // call the API
            ChatResponse response4 = restTemplate.postForObject(apiUrl, request4, ChatResponse.class);
            
            if (response4 == null || response4.getChoices() == null || response4.getChoices().isEmpty()) {
                return objectMapper.writeValueAsString(new ChatTransform("No response", "no response", "no response", "no response"));
            }

            ChatTransform json = new ChatTransform(
                    response.getChoices().get(0).getMessage().getContent(),
                    response2.getChoices().get(0).getMessage().getContent(),
                    response3.getChoices().get(0).getMessage().getContent(),
                    response4.getChoices().get(0).getMessage().getContent()
            );

            parseInformationToDatabase(json, prompt);

            return objectMapper.writeValueAsString(careerRepo.findCareerInfoByTitle(prompt));
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
    private void parseInformationToDatabase(ChatTransform input, String prompt){
        String salaryRange = input.getSalaryRange();

        
        String salaryPattern = "\\$([0-9,]+) (and|to) \\$([0-9,]+)";
        Pattern pattern = Pattern.compile(salaryPattern);
        Matcher matcher = pattern.matcher(salaryRange);
        String lowSalary = "";
        String highSalary = "";
        double lSal = 0.0;
        double hSal = 0.0;
        String description = input.getDescription();
        String riskDescription = input.getRiskDescription();
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
        
        Career career =  new Career(prompt, description,lSal, hSal, 0);
        careerRepo.save(career);
        
        career = careerRepo.findByName(prompt);
        int careerId = career.getCareer_id();

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

        
        Risk risk = new Risk(careerId, riskDescription, 9999);
        riskRepo.save(risk);
        
    }
}
