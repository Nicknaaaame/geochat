import { Component, OnInit } from '@angular/core';
import {ChatService} from "../store/chat-store/service/chat.service";
import {Observable, throwError} from "rxjs";
import {Chat} from "../store/chat-store/service/chat.model";
import {GeolocationService} from "@ng-web-apis/geolocation";
import {ActivatedRoute, Router} from "@angular/router";
import {catchError} from "rxjs/operators";

@Component({
  selector: 'app-user-chats',
  templateUrl: './user-chats.component.html',
  styles: [
  ]
})
export class UserChatsComponent implements OnInit {
  chats$ = this.chatService.getUserChats()

  chosenChat$!: Observable<Chat>
  chatId!: string | null

  constructor(public chatService: ChatService,
              private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(map => {
      this.chatId = map.get('chatId')
      if (this.chatId)
        this.chosenChat$ = this.chatService.getChat(this.chatId).pipe(catchError(err => {
          this.router.navigate(['chats-nearby'])
          return throwError(err);
        }))
    })
  }

}
