package org.example.fifa_central.service;

import org.example.fifa_central.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class ClubService {

    @Autowired private ClubRepository clubRepository;

    public ResponseEntity<Object> getBestClubs(int top){
        return ResponseEntity.ok().body(clubRepository.getClubsStatistics().stream().limit(top).toList());
    }
}
