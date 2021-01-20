import {Injectable} from '@angular/core';
import {environment} from "../../../../environments/environment";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Chat} from "./chat.model";
import {Location} from "../../location-store/service/location.model";
import {SaveChatRequest} from "./save-chat.request";

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private apiUrl = environment.baseUrl + "/api/chat"

  constructor(private http: HttpClient) {
  }

  getChatsAround() {
    // let params = new HttpParams().set("latitude", String(location.latitude)).set("longitude", String(location.longitude))
    // let params = new HttpParams().set("locationId", String(location.id))
    return this.http.get<Chat[]>(this.apiUrl)
  }

  saveChat(request: SaveChatRequest) {
    console.log(request)
    return this.http.post<Chat>(this.apiUrl, request)
  }
}
