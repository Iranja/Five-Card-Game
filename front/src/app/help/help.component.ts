import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-help',
  templateUrl: './help.component.html',
  styleUrls: ['./help.component.scss']
})
export class HelpComponent {
  showDropdown: string | null = null;

  constructor(private router: Router) {}

  toggleDropdown(dropdown: string): void {
    this.showDropdown = this.showDropdown === dropdown ? null : dropdown;
  }

  goBack(): void {
    this.router.navigate([""]); // Remplacez '/' par l'URL de la page précédente si nécessaire
  }
}
