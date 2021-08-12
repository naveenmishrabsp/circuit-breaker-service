package com.example.circuitbreakerservice.controller;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Log4j2
@RestController
public class CircuitBreakerController {


    //localhost:8090/sample-api
    @GetMapping("/sample-api")
    @Retry(name = "sample-api", fallbackMethod = "hardCodedResponse")
   // @Bulkhead(name="sample-api", fallbackMethod = "hardCodedResponse")
    public ResponseEntity<String> getMessage(){
    	log.info("Sample api call");
        new RestTemplate().getForEntity("https://resilience4j.readme.io0/docs",String.class);
        return ResponseEntity.ok("Hello");
    }

    public ResponseEntity<String> hardCodedResponse(Exception exception){
        return ResponseEntity.ok("fallbackMethod");
    }
}
