import { Component, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { LoadingService } from '../services/loading.service';

@Component({
  selector: 'app-loading',
  templateUrl: './loading.component.html',
  styleUrls: ['./loading.component.scss']
})
export class LoadingComponent implements OnDestroy {
  isLoading = false;
  private loadingSubscription: Subscription;

  constructor(private loadingService: LoadingService) {
    // Souscrire au service de chargement pour recevoir les mises à jour sur l'état de chargement
    this.loadingSubscription = this.loadingService.loadingStatus.subscribe((status: boolean) => {
      this.isLoading = status;
    });
  }

  ngOnDestroy(): void {
    // Nettoyer la subscription lors de la destruction du composant
    this.loadingSubscription.unsubscribe();
  }
}
