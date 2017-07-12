package com.amitra.democonsul;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class PetsController {

    @RequestMapping("/pets/{name}")
    public String[] capital(@PathParam("name") String name) {

        return new String[]{
                "Liam", "Fifi", "Loki", "Argos",
                "Scooby", "Scrappy"};
    }

}
