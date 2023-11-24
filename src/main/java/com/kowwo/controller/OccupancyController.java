package com.kowwo.controller;

import com.kowwo.service.OccupancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OccupancyController {

    private final OccupancyService occupancyService;

    @Autowired
    public OccupancyController(OccupancyService occupancyService) {
        this.occupancyService = occupancyService;
    }

    @GetMapping("/status")
    public String getStatus() {
        return "OK!";
    }

    @GetMapping("/calculateOccupancy")
    public ResponseEntity<String> calculateOccupancy(@RequestParam("economyRooms") Integer economyRooms,
                                                     @RequestParam("premiumRooms") Integer premiumRooms) {
        return ResponseEntity.ok(occupancyService.calculateRevenue(economyRooms, premiumRooms).toString());
    }
}
