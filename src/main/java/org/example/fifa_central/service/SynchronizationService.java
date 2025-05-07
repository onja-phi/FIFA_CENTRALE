package org.example.fifa_central.service;

import org.example.fifa_central.dto.ClubStatisticDto;
import org.example.fifa_central.dto.CoachDto;
import org.example.fifa_central.dto.PlayerDto;
import org.example.fifa_central.model.Club;
import org.example.fifa_central.model.Coach;
import org.example.fifa_central.model.Player;
import org.example.fifa_central.model.PlayerPosition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.example.fifa_central.repository.SynchronizationRepository;
import org.springframework.web.client.RestTemplate;

//@Service
//@EnableAsync
//public class SynchronizationService {
//
//    private final SynchronizationRepository repository;
//    private final RestTemplate restTemplate;
//
//    @Value("${external.api.url}")
//    private String externalApiUrl;
//
//    @Value("${external.api.token}")
//    private String apiToken;
//
//    public SynchronizationService(SynchronizationRepository repository, RestTemplate restTemplate) {
//        this.repository = repository;
//        this.restTemplate = restTemplate;
//    }
//
//    public void synchronizeData() {
//        String url = externalApiUrl + "/players";
//        try {
//            ResponseEntity<PlayerDto[]> response = restTemplate.exchange(
//                    url,
//                    HttpMethod.GET,
//                    null, // pas de headers
//                    PlayerDto[].class
//            );
//
//            PlayerDto[] players = response.getBody();
//            System.out.println("Response from API: " + response);
//
//
//            for (PlayerDto dto : players) {
//                // Coach
//                Coach coach = null;
//                if (dto.getClub() != null && dto.getClub().getCoach() != null) {
//                    CoachDto coachDto = dto.getClub().getCoach();
//                    coach = new Coach(coachDto.getName(), coachDto.getNationality());
//                    repository.saveCoachData(coach);
//                }
//
//                // Club
//                Club club = null;
//                if (dto.getClub() != null) {
//                    club = new Club(
//                            dto.getClub().getId(),
//                            dto.getClub().getName(),
//                            dto.getClub().getAcronym(),
//                            dto.getClub().getYearCreation(),
//                            dto.getClub().getStadium()
//                    );
//                    club.setCoach(coach);
//                    repository.saveClubData(club);
//                }
//
//                // Player
//                Player player = new Player(
//                        dto.getId(),
//                        dto.getName(),
//                        dto.getNumber(),
//                        PlayerPosition.valueOf(dto.getPosition()),
//                        dto.getNationality(),
//                        dto.getAge(),
//                        club
//                );
//
//                repository.savePlayerData(player);
//            }
//
//            System.out.println("Calling URL: " + url);
//
//
//
//            // Synchronisation des clubs
//            String clubUrl = externalApiUrl + "/clubs";
//            ResponseEntity<Club[]> clubResponse = restTemplate.exchange(
//                    clubUrl,
//                    HttpMethod.GET,
//                    null,
//                    Club[].class
//            );
//            Club[] clubs = clubResponse.getBody();
//            System.out.println("Clubs from API: " + clubUrl);
//
//            for (Club dto : clubs) {
//                // Coach
//                Coach coach = null;
//                if (dto.getCoach() != null) {
//                    coach = new Coach(dto.getCoach().getName(), dto.getCoach().getNationality());
//                    repository.saveCoachData(coach);
//                }
//
//                // Club
//                Club club = new Club(
//                        dto.getId(),
//                        dto.getName(),
//                        dto.getAcronym(),
//                        dto.getYearCreation(),
//                        dto.getStadium()
//                );
//                club.setCoach(coach);
//                repository.saveClubData(club);
//            }
//
//            String clubStatisticUrl = externalApiUrl + "/clubs/statistics/" + seasonYear.getYear() + "?hasToBeClassified=" + hasToBeClassified;
//
//                try {
//                    ResponseEntity<ClubStatisticDto[]> responseList = restTemplate.exchange(
//                            url,
//                            HttpMethod.GET,
//                            null,
//                            ClubStatisticDto[].class
//                    );
//
//                    ClubStatisticDto[] statistics = responseList.getBody();
//                    System.out.println("Club statistics fetched from API: " + url);
//
//                    assert statistics != null;
//                    for (ClubStatisticDto dto : statistics) {
//                        repository.saveClubStatisticsData(dto); // Appelle la méthode qu’on a définie plus haut
//                    }
//
//                } catch (Exception e) {
//                    throw new RuntimeException("Failed to synchronize club statistics", e);
//                }
//
//
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to synchronize data", e);
//        }
//    }
//}
//
//            /*for (PlayerDto dto : players) {
//                String id = dto.getId();
//                String name = dto.getName();
//                Integer number = dto.getNumber();
//                PlayerPosition position = PlayerPosition.valueOf(dto.getPosition());
//                String nationality = dto.getNationality();
//                Integer age = dto.getAge();
//
//                // Si tu veux ajouter club plus tard, fais-le ici (selon ce que ton DTO contient)
//                Player player = new Player(id, name, number, position, nationality, age, null); // temporairement sans club
//                repository.savePlayerData(player);
//            }
//
//
//
//            for (PlayerDto dto : players) {
//                Club club = null;
//
//                if (dto.getClub() != null) {
//                    CoachDto coachDto = dto.getClub().getCoach();
//                    Coach coach = null;
//                    if (coachDto != null) {
//                        coach = new Coach(coachDto.getName(), coachDto.getNationality());
//                        repository.saveCoachData(coach);
//                    }
//
//                    club = new Club(
//                            dto.getClub().getId(),
//                            dto.getClub().getName(),
//                            dto.getClub().getAcronym(),
//                            dto.getClub().getYearCreation(),
//                            dto.getClub().getStadium()
//                    );
//                    club.setCoach(coach);
//
//                    repository.saveClubData(club);
//                }
//
//                Player player = new Player(
//                        dto.getId(),
//                        dto.getName(),
//                        dto.getNumber(),
//                        PlayerPosition.valueOf(dto.getPosition()),
//                        dto.getNationality(),
//                        dto.getAge(),
//                        club
//                );
//
//                repository.savePlayerData(player);
//            }
//
//            System.out.println("Calling URL: " + url);
//
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to synchronize data", e);
//        }
//    }
//}
//*/


import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.LocalDate;

@Service
@EnableAsync
public class SynchronizationService {

    private final SynchronizationRepository repository;
    private final RestTemplate restTemplate;

    @Value("${external.api.url}")
    private String externalApiUrl;

    @Value("${external.api.token}")
    private String apiToken;

    public SynchronizationService(SynchronizationRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public void synchronizeData() {
        try {
            // Lancer la synchronisation des joueurs, des clubs et des statistiques en parallèle
            synchronizePlayers();  // Exécution asynchrone
            synchronizeClubs();    // Exécution asynchrone
            synchronizeClubStatistics();  // Exécution asynchrone

        } catch (Exception e) {
            throw new RuntimeException("Failed to synchronize data", e);
        }
    }

    @Async
    public void synchronizePlayers() {
        String url = externalApiUrl + "/players";
        try {
            ResponseEntity<PlayerDto[]> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null, // pas de headers
                    PlayerDto[].class
            );

            PlayerDto[] players = response.getBody();
            System.out.println("Response from API: " + response);

            for (PlayerDto dto : players) {
                // Coach
                Coach coach = null;
                if (dto.getClub() != null && dto.getClub().getCoach() != null) {
                    CoachDto coachDto = dto.getClub().getCoach();
                    coach = new Coach(coachDto.getName(), coachDto.getNationality());
                    repository.saveCoachData(coach);
                }

                // Club
                Club club = null;
                if (dto.getClub() != null) {
                    club = new Club(
                            dto.getClub().getId(),
                            dto.getClub().getName(),
                            dto.getClub().getAcronym(),
                            dto.getClub().getYearCreation(),
                            dto.getClub().getStadium()
                    );
                    club.setCoach(coach);
                    repository.saveClubData(club);
                }

                // Player
                Player player = new Player(
                        dto.getId(),
                        dto.getName(),
                        dto.getNumber(),
                        PlayerPosition.valueOf(dto.getPosition()),
                        dto.getNationality(),
                        dto.getAge(),
                        club
                );

                repository.savePlayerData(player);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to synchronize players", e);
        }
    }

    @Async
    public void synchronizeClubs() {
        String clubUrl = externalApiUrl + "/clubs";
        try {
            ResponseEntity<Club[]> clubResponse = restTemplate.exchange(
                    clubUrl,
                    HttpMethod.GET,
                    null,
                    Club[].class
            );
            Club[] clubs = clubResponse.getBody();
            System.out.println("Clubs from API: " + clubUrl);

            for (Club dto : clubs) {
                // Coach
                Coach coach = null;
                if (dto.getCoach() != null) {
                    coach = new Coach(dto.getCoach().getName(), dto.getCoach().getNationality());
                    repository.saveCoachData(coach);
                }

                // Club
                Club club = new Club(
                        dto.getId(),
                        dto.getName(),
                        dto.getAcronym(),
                        dto.getYearCreation(),
                        dto.getStadium()
                );
                club.setCoach(coach);
                repository.saveClubData(club);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to synchronize clubs", e);
        }
    }



    @Async
    public void synchronizeClubStatistics() {
        String clubStatisticUrl = externalApiUrl + "/clubs/statistics/2024-12-12?hasToBeClassified=true"; // Exemple d'URL
        try {
            ResponseEntity<ClubStatisticDto[]> responseList = restTemplate.exchange(
                    clubStatisticUrl,
                    HttpMethod.GET,
                    null,
                    ClubStatisticDto[].class
            );

            ClubStatisticDto[] statistics = responseList.getBody();
            System.out.println("Club statistics fetched from API: " + clubStatisticUrl);

            for (ClubStatisticDto dto : statistics) {
                repository.saveClubStatisticsData(dto); // Appel de la méthode pour sauvegarder les données
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to synchronize club statistics", e);
        }
    }
}
