import { Component, OnInit } from '@angular/core';
import { GameService } from '../services/game.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {
  message: string = '';

  constructor(private gameService: GameService, private userService: UserService) { }

  ngOnInit(): void {
    const username = this.userService.getUsername();
    this.gameService.startGame(username).subscribe({
      next: response => {
        this.message = response;
      },
      error: error => {
        console.error('Erreur lors du démarrage de la partie', error);
        // Gérez les erreurs, le cas échéant
      }
    });
  }
}
