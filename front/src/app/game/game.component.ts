import { Component, EventEmitter, OnInit, Output } from "@angular/core"
import { GameService } from '../services/game.service';
import { UserService } from '../services/user.service';
import { Router } from "@angular/router";


@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {
  message: string = '';
  protected username: string = '';
  constructor(private gameService: GameService, private router: Router,private userService: UserService) { }

  ngOnInit(): void {
    this.username = this.userService.getUsername();
    this.gameService.startGame(this.username).subscribe({
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
