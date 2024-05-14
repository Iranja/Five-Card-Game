import { Component, OnDestroy, OnInit } from "@angular/core"
import { Router } from "@angular/router";
import { UserService } from "../services/user.service";
import { Link } from "../models/links.model";
import { Subscription } from "rxjs";

@Component({
  selector: "epf-navbar",
  templateUrl: "./navbar.component.html",
  styleUrls: ["./navbar.component.scss"],
})
export class NavbarComponent implements OnDestroy {
  links: Link[] = [];
  loggedIn: boolean = false;
  loggedInSubscription!: Subscription; // Initialisation de la subscription

  constructor(private userService: UserService, private router: Router) {
    // Abonnement à l'observable loggedInSubscription pour mettre à jour loggedIn dans NavbarComponent
    this.loggedInSubscription = this.userService.getLoggedIn().subscribe((loggedIn: string) => {
      this.loggedIn = !!loggedIn; // Convertir la valeur en boolean si nécessaire
      this.updateNavbar();
    });
  }

  ngOnDestroy(): void {
    // Nettoyer la subscription lors de la destruction du composant pour éviter les fuites de mémoire
    this.loggedInSubscription.unsubscribe();
  }

  logout(): void {
    // Déclencher la méthode logout du UserService
    this.userService.logout().subscribe(() => {
      // Rediriger l'utilisateur vers la page d'accueil après la déconnexion
      this.router.navigate([""]);
    });
  }

  updateNavbar(): void {
    // Effacer les éléments existants dans la liste de liens
    this.links = [];

    // Ajouter les liens en fonction de l'état de connexion
    if (this.loggedIn) {
      this.links.push({ name: "Historique", href: "/historique" });
      this.links.push({ name: "Logout", href: "" });
    } else {
      this.links.push({ name: "Login", href: "/login" });
      this.links.push({ name: "Register", href: "/register" });
    }
  }

  onLinkClicked(link: Link): void {
    if (link.name === "Logout") {
      const confirmLogout = confirm("Voulez-vous vraiment vous déconnecter ?");
      if (confirmLogout) {
        this.logout();
      }
    }
  }


}
