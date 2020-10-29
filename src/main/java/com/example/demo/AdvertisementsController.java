package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@RequestMapping("/adv")
@RestController
public class AdvertisementsController {

    @Async("asyncExecutor")
    @RequestMapping(value= "/", method = RequestMethod.GET)
    public CompletableFuture<String> getAdvertisement() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response =  restTemplate.getForEntity("http://psuaddservice.fenris.ucn.dk", String.class);
        if(response.getBody() != null){
            if(response.getBody().contains("div")){
                return CompletableFuture.completedFuture(response.getBody());
            }else {
                return null;
            }
        }else{
            return null;
        }

    }
}