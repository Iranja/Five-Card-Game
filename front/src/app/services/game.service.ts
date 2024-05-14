import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Game } from "../models/game.model"

@Injectable({
  providedIn: 'root'
})
export class GameService {

  constructor(private http: HttpClient) { }

  private gameUrl = "http://localhost:8080/game";

  startGame(username: string): Observable<any> {
    return this.http.post<any>('http://localhost:8080/game/partie', { username });
  }

  // Récupère les jeux par nom d'utilisateur
  getGamesByUsername(username: string): Observable<Game[]> {
    const url = `${this.gameUrl}/historique?username=${username}`;
    return this.http.get<Game[]>(url);
  }

  // Ajoutez d'autres méthodes pour récupérer des messages spécifiques depuis le backend
}
