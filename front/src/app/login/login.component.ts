import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { UserService } from "../services/user.service";
import { Subscription } from "rxjs";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.scss"]
})
export class LoginComponent implements OnInit {
  user = {
    username: "",
    password: ""
  };

  subscription: Subscription | undefined;

  constructor(private userService: UserService, private router: Router) {}

  ngOnInit(): void {}

  // Dans le composant LoginComponent
  login() {
    this.subscription = this.userService.login(this.user.username, this.user.password).subscribe({
      next: (response) => {
        // Authentification réussie, redirection vers la page home
        this.router.navigate([""]);
      },
      error: (error) => {
        // Gestion de l'erreur, affichage du message d'erreur
        console.error("Error:", error);
        alert("Nom d'utilisateur ou mot de passe incorrect");
      }
    });
  }

  ngOnDestroy() {
    // N'oubliez pas de désabonner pour éviter les fuites de mémoire
    this.subscription?.unsubscribe();
  }

  goToRegister() {
    // Redirection vers la page de registration
    this.router.navigate(["/register"]);
  }

  goBack(): void {
    this.router.navigate([""]); // Remplacez '/' par l'URL de la page précédente si nécessaire
  }
}
