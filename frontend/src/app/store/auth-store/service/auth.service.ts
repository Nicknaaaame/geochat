import {Injectable} from '@angular/core';
import {environment} from "../../../../environments/environment";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {AuthData} from "../store/auth.reducer";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private authorizeEndpoint = '/login/oauth2/authorization/'
  private tokenEndpoint = '/login/oauth2/code/'
  private apiUrl = environment.baseUrl

  constructor(private http: HttpClient) {
  }

  openLoginPage(providerId: string) {
    window.open(this.apiUrl + this.authorizeEndpoint + providerId, '_self');
  }

  fetchToken(code: string, state: string, providerId: string): Observable<AuthData> {
    return this.http.get<AuthData>(this.apiUrl + this.tokenEndpoint + providerId + '?code=' + code + '&state=' + state);
  }
}
