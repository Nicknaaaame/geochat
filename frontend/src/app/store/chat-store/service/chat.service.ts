import {Injectable} from '@angular/core';
import {environment} from "../../../../environments/environment";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Chat} from "./chat.model";
import {Location} from "../../location-store/service/location.model";

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  apiUrl = environment.baseUrl + "/api"

  constructor(private http: HttpClient) {
  }

  getChatsAround() {
    // let params = new HttpParams().set("latitude", String(location.latitude)).set("longitude", String(location.longitude))
    // let params = new HttpParams().set("locationId", String(location.id))
    return this.http.get<Chat[]>(this.apiUrl + '/chat')
  }
}
