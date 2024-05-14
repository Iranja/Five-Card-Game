package com.takima.back.services;

import com.takima.back.DAO.GameDao;
import com.takima.back.DAO.GamerDao;
import com.takima.back.DTO.GameDto;
import com.takima.back.models.Game;
import com.takima.back.models.Gamer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final GameDao gameDao;
    private final GamerDao gamerDao;

    public GameService(GameDao gameDao, GamerDao gamerDao) { // Ajouter GamerDao au constructeur
        this.gameDao = gameDao;
        this.gamerDao = gamerDao;
    }

    public List<GameDto> getGamesByUserId(Long userId) {
        List<Game> games = gameDao.findByUserId(userId);
        return games.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<Game> getGamesByUsername(String username) {
        Optional<Gamer> optionalGamer = gamerDao.findByUsername(username);
        if (optionalGamer.isPresent()) {
            Gamer gamer = optionalGamer.get();
            return gameDao.findAllByUserIdOrderById(gamer.getId());
        } else {
            return List.of();
        }
    }


    private GameDto convertToDto(Game game) {
        GameDto gameDto = new GameDto();
        gameDto.setId(game.getId());
        gameDto.setPlace(game.getPlace());
        gameDto.setPoints(game.getPoints());
        gameDto.setDate(game.getDate());
        gameDto.setTime(game.getTime());
        return gameDto;
    }
}
