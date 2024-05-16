import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { NavbarComponent } from "./navbar/navbar.component";
import { MatListModule } from "@angular/material/list";
import { HomeComponent } from "./home/home.component";
import { FormsModule } from "@angular/forms";
import { MatIconModule } from "@angular/material/icon";
import { MatButtonModule } from "@angular/material/button";
import { HttpClientModule, HTTP_INTERCEPTORS  } from "@angular/common/http";
import { LoginComponent } from "./login/login.component"; // Importez le LoginComponent ici
import { RegisterComponent } from "./register/register.component"; // Importez le LoginComponent ici
import { GameComponent } from "./game/game.component";
import { HistoriqueComponent } from "./historique/historique.component";
import { HelpComponent } from "./help/help.component";
import { LoadingComponent } from './loading/loading.component';
import { LoadingInterceptor } from './loading/loading.interceptor';
@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    GameComponent,
    HistoriqueComponent,
    HelpComponent,
    LoadingComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatListModule,
    FormsModule,
    MatIconModule,
    MatButtonModule,
    HttpClientModule,
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: LoadingInterceptor, multi: true }],
  bootstrap: [AppComponent],
})
export class AppModule {}
