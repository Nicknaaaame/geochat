import {Injectable} from '@angular/core';
import {environment} from "../../../../environments/environment";
import {HttpClient, HttpParams} from "@angular/common/http";
import {LocalChat} from "./local-chat.model";
import {Location} from "../../location-store/service/location.model";
import {SaveLocalChatRequest} from "./save-local-chat.request";
import {PrivateChat} from "./private-chat.model";
import {SaveChatRequest} from "./save-chat.request";
import {Chat} from "./chat.model";

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private apiUrl = environment.baseUrl + "/api/chat"

  constructor(private http: HttpClient) {
  }

  getChatsAround() {
    return this.http.get<LocalChat[]>(this.apiUrl + '/around')
  }

  createChat(request: SaveChatRequest) {
    return this.http.post<Chat>(this.apiUrl, request)
  }

  getChatsNearby() {
    return this.http.get<Chat[]>(this.apiUrl + '/nearby')
  }

  createLocalChat(request: SaveLocalChatRequest) {
    return this.http.post<LocalChat>(this.apiUrl + '/local', request)
  }

  createPrivateChat(request: { userId: number | string, message: string }) {
    return this.http.post<PrivateChat>(this.apiUrl + '/private', request)
  }

  getUserChats() {
    return this.http.get<Array<LocalChat | PrivateChat>>(this.apiUrl + '/user')
  }

  joinLocalChat(chat: LocalChat) {
    let request: JoinLocalChatRequest = {
      id: chat.id
    }
    return this.http.post<LocalChat>(this.apiUrl + '/local/join', request)
  }

  public static isLocalChat(chat: LocalChat | PrivateChat): boolean {
    return 'location' in chat
  }

  public static getTitle(chat: LocalChat | PrivateChat): string {
    if (ChatService.isLocalChat(chat)) {
      return (chat as LocalChat).name
    } else {
      return (chat as PrivateChat).user.name
    }
  }
}

interface JoinLocalChatRequest {
  id: number | string
}
