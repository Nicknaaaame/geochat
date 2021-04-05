import {Injectable} from '@angular/core';
import {environment} from "../../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {ChatRequest} from "./chat.request";
import {Chat} from "./chat.model";

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private apiUrl = environment.baseUrl + "/api/chat"

  constructor(private http: HttpClient) {
  }

  createChat(request: ChatRequest) {
    let formData = new FormData()
    if (request.picture)
      formData.append('picture', request.picture)
    formData.append('name', request.name)
    formData.append('description', request.description)
    return this.http.post<Chat>(this.apiUrl, formData)
  }

  updateChat(chatId: number | string, request: ChatRequest) {
    let formData = new FormData()
    if (request.picture)
      formData.append('picture', request.picture)
    formData.append('name', request.name)
    formData.append('description', request.description)
    return this.http.put<Chat>(this.apiUrl + '/' + chatId, formData)
  }

  getChatsNearby() {
    return this.http.get<Chat[]>(this.apiUrl + '/nearby')
  }

  getUserChats() {
    return this.http.get<Chat[]>(this.apiUrl + '/user')
  }

  getChat(chatId: number | string | null) {
    return this.http.get<Chat>(this.apiUrl + '/' + chatId)
  }

  joinChat(chatId: number | string) {
    return this.http.post<void>(this.apiUrl + '/join', {chatId: chatId})
  }

  leaveChat(id: number | string) {
    return this.http.post<void>(this.apiUrl + '/leave', {chatId: id})
  }

  deleteChat(id: number | string) {
    return this.http.delete<void>(this.apiUrl + '/' + id)
  }

  enableNotification(chatId: number | string) {
    return this.http.post<void>(this.apiUrl + '/notification/enable', {chatId: chatId})
  }

  disableNotification(chatId: number | string) {
    return this.http.post<void>(this.apiUrl + '/notification/disable', {chatId: chatId})
  }
}
