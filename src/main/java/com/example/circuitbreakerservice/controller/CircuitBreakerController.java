package com.example.circuitbreakerservice.controller;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Log4j2
@RestController
public class CircuitBreakerController {


    //localhost:8090/sample-api/Naveen
    @GetMapping("/sample-api/{name}")
    @Retry(name = "sample-api", fallbackMethod = "hardCodedResponse")
   // @Bulkhead(name="sample-api", fallbackMethod = "hardCodedResponse")
    public ResponseEntity<String> getMessage(@PathVariable String name){
    	log.info("Sample api call");
        new RestTemplate().getForEntity("https://www.google.cojm",String.class);
        return ResponseEntity.ok("<h1 color:red>Hello:"+name+"<h1/>");
    }

    public ResponseEntity<String> hardCodedResponse(Exception exception){
        log.info("newly added fallback method hardCodedResponse");
        return ResponseEntity.ok("fallbackMethod");
    }
}
