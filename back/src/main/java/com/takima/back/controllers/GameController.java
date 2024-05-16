package com.takima.back.controllers;

import com.takima.back.DTO.GameDto;
import com.takima.back.controllers.five_game.Partie;
import com.takima.back.models.Game;
import com.takima.back.services.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController // Remplacez ceci par l'URL de votre application Angular
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/partie")
    public String startGame(@RequestBody String username) {
        String message = "Partie lancée avec succès pour " + username;
        System.out.println(message);
        //Partie partie = new Partie(username);
        return message;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<GameDto>> getGamesByUserId(@PathVariable Long userId) {
        List<GameDto> games = gameService.getGamesByUserId(userId);
        return ResponseEntity.ok(games);
    }

    // Endpoint pour récupérer les jeux par nom d'utilisateur
    @GetMapping("/historique")
    public List<Game> getGamesByUsername(@RequestParam String username) {
        return gameService.getGamesByUsername(username);
    }

    // Ajoutez d'autres endpoints pour renvoyer d'autres messages spécifiques
}
