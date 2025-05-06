package org.example.fifa_central.service;

import org.example.fifa_central.dto.CoachDto;
import org.example.fifa_central.dto.PlayerDto;
import org.example.fifa_central.model.Club;
import org.example.fifa_central.model.Coach;
import org.example.fifa_central.model.Player;
import org.example.fifa_central.model.PlayerPosition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.example.fifa_central.repository.SynchronizationRepository;
import org.springframework.web.client.RestTemplate;

@Service
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

            System.out.println("Calling URL: " + url);

        } catch (Exception e) {
            throw new RuntimeException("Failed to synchronize data", e);
        }
    }
}

            /*for (PlayerDto dto : players) {
                String id = dto.getId();
                String name = dto.getName();
                Integer number = dto.getNumber();
                PlayerPosition position = PlayerPosition.valueOf(dto.getPosition());
                String nationality = dto.getNationality();
                Integer age = dto.getAge();

                // Si tu veux ajouter club plus tard, fais-le ici (selon ce que ton DTO contient)
                Player player = new Player(id, name, number, position, nationality, age, null); // temporairement sans club
                repository.savePlayerData(player);
            }



            for (PlayerDto dto : players) {
                Club club = null;

                if (dto.getClub() != null) {
                    CoachDto coachDto = dto.getClub().getCoach();
                    Coach coach = null;
                    if (coachDto != null) {
                        coach = new Coach(coachDto.getName(), coachDto.getNationality());
                        repository.saveCoachData(coach);
                    }

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

            System.out.println("Calling URL: " + url);

        } catch (Exception e) {
            throw new RuntimeException("Failed to synchronize data", e);
        }
    }
}
*/