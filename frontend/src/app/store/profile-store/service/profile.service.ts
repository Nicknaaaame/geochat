import {Injectable} from '@angular/core';
import {environment} from "../../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Profile} from "./profile.model";
import {UpdateProfileRequest} from "./update-profile.request";
import {Location} from "../../location-store/service/location.model";

@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  private apiUrl = environment.baseUrl + '/api/profile'

  constructor(private http: HttpClient) {
  }

  getProfile() {
    return this.http.get<Profile>(this.apiUrl)
  }

  updateProfile(request: UpdateProfileRequest) {
    let formData = new FormData()
    formData.append('name', request.name)
    if (request.picture)
      formData.append('picture', request.picture)
    return this.http.put<Profile>(this.apiUrl, formData)
  }

  updateProfileLocation(location: Location) {
    return this.http.put<Profile>(this.apiUrl + '/location', location)
  }
}
