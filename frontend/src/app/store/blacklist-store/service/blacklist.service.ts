import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../../environments/environment";
import {User} from "../../user-store/service/user.model";

@Injectable({
  providedIn: 'root'
})
export class BlacklistService {
  private apiUrl = environment.baseUrl + "/api/blacklist"

  constructor(private http: HttpClient) {
  }

  blockUser(userId: number | string, chatId: number | string) {
    return this.http.post(this.apiUrl + "/block", {userId, chatId})
  }

  unblockUser(userId: number | string, chatId: number | string) {
    return this.http.post(this.apiUrl + "/unblock", {userId, chatId})
  }

  getBlackList(chatId: number | string) {
    return this.http.get<User[]>(this.apiUrl + '/chat/' + chatId)
  }
}
