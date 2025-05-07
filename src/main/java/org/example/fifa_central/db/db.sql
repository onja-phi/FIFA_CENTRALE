\c postgres

DROP TABLE IF EXISTS players, clubs, coaches;
DROP TYPE IF EXISTS player_position;

create database fifa_central;
\c fifa_central


-- Recrée tout
CREATE TYPE player_position AS ENUM ('STRIKER', 'MIDFIELDER', 'DEFENSE', 'GOAL_KEEPER');

CREATE TABLE coaches (
                         name VARCHAR PRIMARY KEY,
                         nationality VARCHAR(50)
);

CREATE TABLE clubs (
                       id VARCHAR PRIMARY KEY,
                       name VARCHAR NOT NULL,
                       acronym VARCHAR(3),
                       year_creation INTEGER,
                       stadium VARCHAR(100),
                       coach_name VARCHAR(100),
                       CONSTRAINT fk_coach_name FOREIGN KEY (coach_name) REFERENCES coaches(name)
);

CREATE TABLE players (
                         id VARCHAR PRIMARY KEY,
                         name VARCHAR NOT NULL,
                         number INTEGER,
                         position player_position,
                         nationality VARCHAR(50),
                         age INTEGER,
                         club_id VARCHAR,
                         coach_name VARCHAR,
                         FOREIGN KEY (club_id) REFERENCES clubs(id),
                         FOREIGN KEY (coach_name) REFERENCES coaches(name)
);

CREATE TABLE club_statistics (
                                 id SERIAL PRIMARY KEY,
                                 club_id VARCHAR REFERENCES clubs(id),
                                 ranking_points INTEGER,
                                 scored_goals INTEGER,
                                 conceded_goals INTEGER,
                                 difference_goals INTEGER,
                                 clean_sheet_number INTEGER,
                                 year_creation INTEGER,
                                 stadium VARCHAR(100),
                                 coach_name VARCHAR(100),
                                 coach_nationality VARCHAR(100)
);

