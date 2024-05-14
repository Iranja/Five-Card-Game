import { NgModule } from "@angular/core"
import { RouterModule, Routes } from "@angular/router"
import { HomeComponent } from "home/home.component"
import { LoginComponent } from "login/login.component"; // Importez le LoginComponent
import { RegisterComponent } from "./register/register.component"; // Importez le LoginComponent ici
import { GameComponent } from "./game/game.component";
import { HistoriqueComponent } from "./historique/historique.component";
import { HelpComponent } from "./help/help.component";

const routes: Routes = [
  { path: "", component: HomeComponent },
  { path: "login", component: LoginComponent }, // Ajoutez la route pour la page de login
  { path: "register", component: RegisterComponent },
  { path: "game", component: GameComponent },
  { path: "historique", component: HistoriqueComponent },
  { path: "help", component: HelpComponent },

]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
