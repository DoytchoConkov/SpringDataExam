package com.example.football.service.impl;

import com.example.football.models.dto.PlayerSeedDto;
import com.example.football.models.dto.rootDto.PlayerRootSeedDto;
import com.example.football.models.entity.Player;
import com.example.football.repository.PlayerRepository;
import com.example.football.service.PlayerService;
import com.example.football.service.StatService;
import com.example.football.service.TeamService;
import com.example.football.service.TownService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private static final String PLAYERS_FILE_PATH = "src/main/resources/files/xml/players.xml";

    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final TownService townService;
    private final TeamService teamService;
    private final StatService statService;

    public PlayerServiceImpl(PlayerRepository playerRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil, TownService townService, TeamService teamService, StatService statService) {
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.townService = townService;
        this.teamService = teamService;
        this.statService = statService;
    }

    @Override
    public boolean areImported() {
        return playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return Files
                .readString(Path.of(PLAYERS_FILE_PATH));
    }

    @Override
    public String importPlayers() throws JAXBException, FileNotFoundException {

        StringBuilder sb = new StringBuilder();

        List<PlayerSeedDto> players = xmlParser
                .fromFile(PLAYERS_FILE_PATH, PlayerRootSeedDto.class)
                .getPlayers();

        players.stream()
                .filter(playerSeedDto -> {
                    boolean isValid = validationUtil.isValid(playerSeedDto)
                            && townService.getTownByName(playerSeedDto.getTown().getName()) != null
                            && teamService.getTeamByName(playerSeedDto.getTeam().getName()) != null
                            && statService.getStatById(playerSeedDto.getStat().getId()) != null
                            && !playerRepository.findByEmail(playerSeedDto.getEmail()).isPresent();
                    sb
                            .append(isValid ? String.format("Successfully import Player %s %s - %s",
                                    playerSeedDto.getFirstName(),
                                    playerSeedDto.getLastName(),
                                    playerSeedDto.getPosition().name())
                                    : "Invalid Player")
                            .append(System.lineSeparator());

                    return isValid;
                })
                .map(playerSeedDto -> {
                    Player player = modelMapper.map(playerSeedDto, Player.class);
                    player.setTown(townService.getTownByName(playerSeedDto.getTown().getName()));
                    player.setTeam(teamService.getTeamByName(playerSeedDto.getTeam().getName()));
                    player.setStat(statService.getStatById(playerSeedDto.getStat().getId()));
                    return player;
                })
                .forEach(playerRepository::save);

        return sb.toString();
    }

    @Override
    public String exportBestPlayers() {

        StringBuilder sb = new StringBuilder();

        List<Player> players = playerRepository.getAllPlayerOrdered(LocalDate.of(1995, 01, 01), LocalDate.of(2003, 01, 01));

        players.forEach(player -> {
            sb.append(String.format("Player - %s %s\n" +
                            "\tPosition - %s\n" +
                            "\tTeam - %s\n" +
                            "\tStadium - %s\n",
                    player.getFirstName(), player.getLastName(), player.getPosition().name(), player.getTeam().getName(),
                    player.getTeam().getStadiumName()));
        });

        return sb.toString();
    }
}
