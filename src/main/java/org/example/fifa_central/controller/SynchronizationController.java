package org.example.fifa_central.controller;

import org.example.fifa_central.service.SynchronizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SynchronizationController {

    private final SynchronizationService synchronizationService;

    public SynchronizationController(SynchronizationService synchronizationService) {
        this.synchronizationService = synchronizationService;
    }

    @PostMapping("/synchronization")
    public ResponseEntity<Void> synchronize() {
        synchronizationService.synchronizeData();
        return ResponseEntity.ok().build();
    }
}
