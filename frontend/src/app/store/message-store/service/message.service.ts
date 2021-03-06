import {Injectable} from '@angular/core';
import {Message} from "./message.model";
import {environment} from "../../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {SocketClientService} from "../../websocket/socket-client.service";

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  apiUrl = environment.baseUrl + '/api/message'

  constructor(private http: HttpClient, private socketClient: SocketClientService) {
  }

  saveMessage(message: { chatId: number | string, text: string }) {
    return this.http.post<Message>(this.apiUrl, message)
  }

  getMessages(chatId: number | string) {
    return this.http.get<Message[]>(this.apiUrl + '/chat/' + chatId)
  }

  onMessage(chatId: number | string) {
    return this.socketClient.onMessage<Message>(`/topic/message/chat/${chatId}`)
  }
}
