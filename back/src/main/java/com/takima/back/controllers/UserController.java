package com.takima.back.controllers;

import com.takima.back.DTO.UserDto;
import com.takima.back.models.User;
import com.takima.back.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController // Remplacez ceci par l'URL de votre application Angular
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto) {
        return userService.registerUser(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDto userDto) {
        return userService.loginUser(userDto.getUsername(), userDto.getPassword());
    }

    @PostMapping("/save")
    public String saveUser(@Valid @RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }

    @PostMapping("/test")
    public ResponseEntity<?> testUser(@Valid @RequestBody UserDto userDto) {
        // Créer un nouvel utilisateur avec les données reçues
        User newUser = new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(userDto.getPassword());

        // Simuler l'ajout de l'utilisateur à la base de données
        // Notez que dans un environnement de production, vous utiliseriez le service UserService pour effectuer cette opération
        // Ici, nous utilisons simplement une réponse de réussite pour simuler l'ajout de l'utilisateur
        String message = "Utilisateur ajouté avec succès : " + newUser.getUsername();

        // Retourner une réponse avec le message de succès
        return ResponseEntity.ok(message);
    }


}
