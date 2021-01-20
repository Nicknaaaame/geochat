import {Injectable} from '@angular/core';
import {environment} from "../../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {User} from "./user.model";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = environment.baseUrl + "/api/user"

  constructor(private http: HttpClient) {
  }

  getUsersAround() {
    return this.http.get<User[]>(this.apiUrl)
  }
}
