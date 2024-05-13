import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { User } from "models/user.model";

@Injectable({
  providedIn: "root",
})
export class UserService {
  constructor(private http: HttpClient) {}

  private usersUrl = "http://localhost:8080/users";

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
    return this.http.post<any>(`${this.usersUrl}/login`, { username, password });
  }
}
