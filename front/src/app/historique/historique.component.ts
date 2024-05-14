import { Component, OnInit } from '@angular/core';
import { Game } from '../models/game.model';
import { GameService } from '../services/game.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-historique',
  templateUrl: './historique.component.html',
  styleUrls: ['./historique.component.scss']
})
export class HistoriqueComponent implements OnInit {
  games: Game[] = [];
  username: string = "";

  constructor(private gameService: GameService, private userService: UserService) { }

  ngOnInit(): void {
    this.username = this.userService.getUsername();
    this.loadGames();
  }

  loadGames() {
    this.gameService.getGamesByUsername(this.username).subscribe(games => {
      this.games = games;
    });
  }

  convertTimeToDateTime(time: string): Date {
    const today = new Date();
    const timeParts = time.split(':');
    today.setHours(parseInt(timeParts[0], 10));
    today.setMinutes(parseInt(timeParts[1], 10));
    today.setSeconds(parseInt(timeParts[2], 10));
    return today;
  }

}
