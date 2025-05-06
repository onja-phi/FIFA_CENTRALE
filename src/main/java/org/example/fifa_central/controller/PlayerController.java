/*package org.example.fifa_central.controller;

import lombok.RequiredArgsConstructor;

import org.example.fifa_central.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bestPlayers")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    @GetMapping
    public ResponseEntity<?> getBestPlayers(
            @RequestParam(defaultValue = "5") int top,
            @RequestParam String playingTimeUnit) {
        return ResponseEntity.ok(playerService.getBestPlayers(top, playingTimeUnit));
    }
}*/