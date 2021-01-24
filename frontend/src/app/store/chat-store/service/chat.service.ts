import {Injectable} from '@angular/core';
import {environment} from "../../../../environments/environment";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Chat} from "./chat.model";
import {Location} from "../../location-store/service/location.model";
import {SaveLocalChatRequest} from "./save-local-chat.request";

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
    return this.http.get<Chat[]>(this.apiUrl + '/around')
  }

  saveLocalChat(request: SaveLocalChatRequest) {
    return this.http.post<Chat>(this.apiUrl + '/local', request)
  }

  getUserChats() {
    return this.http.get<Chat[]>(this.apiUrl + '/user')
  }

  joinLocalChat(chat: Chat) {
    let request: JoinLocalChatRequest = {
      id: chat.id
    }
    console.log(request)
    return this.http.post<Chat>(this.apiUrl + '/local/join', request)
  }
}

interface JoinLocalChatRequest {
  id: number | string
}
