import {Injectable} from '@angular/core';
import {environment} from "../../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Profile} from "./profile.model";

@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  private apiUrl = environment.baseUrl + '/api'

  constructor(private http: HttpClient) {
  }

  getProfile() {
    return this.http.get<Profile>(this.apiUrl + "/profile")
  }

  updateProfile(profile: Profile) {
    return  this.http.put<Profile>(this.apiUrl + '/profile', profile)
  }
}
