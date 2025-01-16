package com.example.dronepizza.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;


@Controller
public class DEFAULTController {
    @GetMapping("/drones")
    public String homePage(){
        return "homePage";
    }

    @GetMapping("/deliveries")
    public String showDeliveries(){
        return "showDeliveries";
    }

    @GetMapping("/deliveries/queue")
    public String showMissingDroneDeliveries(){
        return "showMissingDroneDeliveries";
    }
}
