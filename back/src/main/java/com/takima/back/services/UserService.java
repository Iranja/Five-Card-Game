package com.takima.back.services;

import com.takima.back.DAO.UserDao;
import com.takima.back.DTO.UserDto;
import com.takima.back.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String addUser(UserDto userDto){

        User newUser = new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userDao.save(newUser);
        return newUser.getUsername();
    }

    public ResponseEntity<?> registerUser(UserDto userDto) {
        // Vérifier si l'utilisateur existe déjà
        Optional<User> existingUser = userDao.findByUsername(userDto.getUsername());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("Nom d'utilisateur déjà utilisé");
        }

        // Créer un nouvel utilisateur
        User newUser = new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // Enregistrer l'utilisateur dans la base de données
        userDao.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(newUser); // Retourne le nouvel utilisateur enregistré
    }

    public ResponseEntity<?> loginUser(String username, String password) {
        // Rechercher l'utilisateur dans la base de données par nom d'utilisateur
        Optional<User> user = userDao.findByUsername(username);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nom d'utilisateur ou mot de passe incorrect");
        }

        // Vérifier si le mot de passe correspond
        if (!passwordEncoder.matches(password, user.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nom d'utilisateur ou mot de passe incorrect");
        }

        // Authentification réussie
        return ResponseEntity.ok("Connexion réussie");
    }
}
