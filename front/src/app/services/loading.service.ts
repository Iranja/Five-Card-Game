import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoadingService {
  private loadingSubject: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  public loadingStatus = this.loadingSubject.asObservable();

  constructor() { }

  // Méthode pour démarrer le chargement
  startLoading(): void {
    this.loadingSubject.next(true);
  }

  // Méthode pour arrêter le chargement
  stopLoading(): void {
    this.loadingSubject.next(false);
  }
}
