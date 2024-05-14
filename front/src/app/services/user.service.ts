import { Injectable } from "@angular/core";
import { Observable, BehaviorSubject } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { User } from "models/user.model";
import { tap } from "rxjs/operators"

@Injectable({
  providedIn: "root",
})
export class UserService {
  private loggedIn = new BehaviorSubject<string>("");

  constructor(private http: HttpClient) {}

  private usersUrl = "http://localhost:8080/gamers";

  findAll(): Observable<User[]> {
    return this.http.get<User[]>(this.usersUrl);
  }

  findById(id: number): Observable<User> {
    return this.http.get<User>(`${this.usersUrl}/${id}`);
  }

  register(user: User): Observable<User> {
    return this.http.post<User>(`${this.usersUrl}/register`, user);
  }

  login(username: string, password: string): Observable<any> {
    // Vous pouvez adapter cette méthode en fonction de votre API de login
    return this.http.post<any>(`${this.usersUrl}/login`, { username, password })
      .pipe(
        // Si l'appel réussit, mettez à jour l'état de connexion du service avec le nom d'utilisateur
        tap(() => {
          this.loggedIn.next(username);
        })
      );
  }

  logout(): Observable<any> {
    // Réinitialiser l'état de connexion côté client
    this.loggedIn.next("");

    // Déconnexion côté serveur
    return this.http.post<any>(`${this.usersUrl}/logout`, {});
  }

  // Méthode pour vérifier si l'utilisateur est connecté
  isAuthenticated(): boolean {
    return !!this.loggedIn.value;
  }

  // Méthode pour obtenir le nom d'utilisateur actuellement connecté
  getUsername(): string {
    return this.loggedIn.value;
  }
  // Accesseur pour récupérer la valeur de loggedIn
  getLoggedIn(): Observable<string> {
    return this.loggedIn.asObservable();
  }
}
