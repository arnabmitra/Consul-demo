package com.amitra.consulclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/pets")
public class PetsController {

    @Autowired
    DiscoveryClient client;


    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/{name}")
    public String[] getPets(@PathVariable("name") String petName)
    {
        ServiceInstance serviceInstance=client.getInstances("pet-service")
                .stream().findFirst().
                        orElseThrow(()-> new RuntimeException("pet-service not found"));

        String url = serviceInstance.getUri().toString()+ "/pets/"+petName;
        return restTemplate.getForObject(url,String[].class);
    }
}
