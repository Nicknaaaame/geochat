import {Injectable} from '@angular/core';
import {Message} from "./message.model";
import {environment} from "../../../../environments/environment";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  apiUrl = environment.baseUrl + '/api/message'

  constructor(private http: HttpClient) {
  }

  sendMessage(message: Message) {
    return this.http.post<Message>(this.apiUrl, message)
  }
}
