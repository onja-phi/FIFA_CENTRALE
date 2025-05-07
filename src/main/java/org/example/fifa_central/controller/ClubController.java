package org.example.fifa_central.controller;

import org.example.fifa_central.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
public class ClubController {

    @Autowired private ClubService clubService;

    @GetMapping("/bestClubs")
    public ResponseEntity<Object> getBestClubs(@RequestParam(defaultValue = "5") int top) {
        return ResponseEntity.ok(clubService.getBestClubs(top));
    }
}
