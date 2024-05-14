package com.takima.back.controllers;

import com.takima.back.DTO.GamerDto;
import com.takima.back.DTO.GamerMapper;
import com.takima.back.models.Gamer;
import com.takima.back.services.GamerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@CrossOrigin
@RestController // Remplacez ceci par l'URL de votre application Angular
@RequestMapping("/gamers")
public class GamerController {

    @Autowired
    private GamerService gamerService;

    @PostMapping("/register")
    public ResponseEntity<?> registerGamer(@RequestBody GamerDto gamerDto) {
        // Mapper le GamerDto en Gamer
        Gamer gamer = GamerMapper.fromDto(gamerDto);

        // Enregistrer le Gamer
        ResponseEntity<?> response = gamerService.registerGamer(gamer);

        // Vérifier la réponse du service
        if (response.getStatusCode() == HttpStatus.CREATED) {
            // Mapper le Gamer en GamerDto
            GamerDto savedGamerDto = GamerMapper.toDto((Gamer) Objects.requireNonNull(response.getBody()));
            // Retourner le GamerDto créé avec le statut CREATED
            return ResponseEntity.status(HttpStatus.CREATED).body(savedGamerDto);
        } else {
            // Retourner la réponse du service
            return response;
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        ResponseEntity<?> response = gamerService.loginGamer(loginData);
        if (response.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.ok(response.getBody());
        } else {
            return response;
        }
    }




}
