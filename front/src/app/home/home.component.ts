import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { UserService } from "../services/user.service";

@Component({
  selector: "five-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.scss"]
})
export class HomeComponent implements OnInit {

  constructor(private userService: UserService, private router: Router) {}

  ngOnInit(): void {

  }

  goToGame() {
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

  goToProfile() {
    // Vérifier si l'utilisateur est connecté
    const isAuthenticated = this.userService.isAuthenticated();

    // Si l'utilisateur est connecté, rediriger vers la page du jeu ("/game")
    if (isAuthenticated) {
      //this.router.navigate(["/profile"]);
    } else {
      // Sinon, rediriger vers la page de connexion
      this.router.navigate(["/login"]);
    }
  }

  goToOptions() {
    //this.router.navigate(["/options"]);
  }
}
