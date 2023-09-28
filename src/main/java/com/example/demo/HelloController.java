package com.example.demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
public class HelloController {

	@GetMapping("/")
	public String index() {
		try {
            String url = "https://en.wikipedia.org/wiki/Firefighter";
            Document doc = Jsoup.connect(url).get();
            return doc.body().toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error occurred";
        }
	}

}