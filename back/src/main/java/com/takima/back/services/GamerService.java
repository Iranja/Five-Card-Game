package com.takima.back.services;

import com.takima.back.DAO.GamerDao;
import com.takima.back.models.Gamer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class GamerService {

    @Autowired
    private GamerDao gamerDao;

    public ResponseEntity<?> registerGamer(Gamer gamer) {
        // Vérifier si l'username existe déjà dans la base de données
        Optional<Gamer> existingUsername = gamerDao.findByUsername(gamer.getUsername());
        if (existingUsername.isPresent()) {
            return ResponseEntity.badRequest().body("Nom d'utilisateur déjà utilisé");
        }

        // Vérifier si l'email existe déjà dans la base de données
        Optional<Gamer> existingEmail = gamerDao.findByEmail(gamer.getEmail());
        if (existingEmail.isPresent()) {
            return ResponseEntity.badRequest().body("Email déjà utilisé");
        }

        // Enregistrer le nouveau Gamer dans la base de données
        Gamer savedGamer = gamerDao.save(gamer);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedGamer);
    }

    public ResponseEntity<?> loginGamer(Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");

        Optional<Gamer> optionalGamer = gamerDao.findByUsername(username);
        if (optionalGamer.isPresent()) {
            Gamer gamer = optionalGamer.get();
            if (gamer.getPassword().equals(password)) {
                // Le mot de passe est correct, authentification réussie
                // Vous pouvez éventuellement générer un token JWT ici
                Map<String, String> response = new HashMap<>();
                response.put("message", "Login successful");
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mot de passe incorrect");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé");
        }
    }
}
