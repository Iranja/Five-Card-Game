import { Component } from "@angular/core";
import { Router } from "@angular/router";
import { UserService } from "../services/user.service";
import { tap, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.scss"]
})
export class RegisterComponent {
  user = {
    username: "",
    email: "",
    password: ""
  };

  constructor(private userService: UserService, private router: Router) {}

  register() {
    this.userService.register(this.user)
      .pipe(
        tap(() => {
          // Inscription réussie, redirection vers la page de login
          this.router.navigate(["/login"]);
        }),
        catchError((error) => {
          console.error("Error:", error);
          alert("Erreur lors de l'inscription");
          return throwError(error); // Renvoyer l'erreur pour la gestion ultérieure
        })
      )
      .subscribe();
  }

  goToLogin() {
    // Redirection vers la page de login
    this.router.navigate(["/login"]);
  }

  goBack(): void {
    this.router.navigate([""]); // Remplacez '/' par l'URL de la page précédente si nécessaire
  }

}
