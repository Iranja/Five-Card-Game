import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { UserService } from "../services/user.service";

@Component({
  selector: "epf-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.scss"]
})
export class HomeComponent implements OnInit {

  constructor(private userService: UserService, private router: Router) {}

  ngOnInit(): void {

  }

  goToLogin() {
    // Vérifier si l'utilisateur est connecté
    const isAuthenticated = this.userService.isAuthenticated();

    // Si l'utilisateur est connecté, rediriger vers la page du jeu ("/game")
    if (isAuthenticated) {
      this.router.navigate(["/game"]);
    } else {
      // Sinon, rediriger vers la page de connexion
      this.router.navigate(["/login"]);
    }
  }

  goToHelp() {
    this.router.navigate(["/help"]);
  }
}
