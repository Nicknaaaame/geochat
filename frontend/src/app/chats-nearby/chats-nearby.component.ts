import {Component, OnInit} from '@angular/core';
import {GeolocationService} from "@ng-web-apis/geolocation";
import {ChatService} from "../store/chat-store/service/chat.service";
import {Chat} from "../store/chat-store/service/chat.model";
import {ActivatedRoute, Route, Router} from "@angular/router";
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs/operators";

@Component({
  selector: 'app-chats-nearby',
  templateUrl: './chats-nearby.component.html',
  styles: []
})
export class ChatsNearbyComponent implements OnInit {
  chats$ = this.chatService.getChatsNearby()
  chosenChat$!: Observable<Chat>

  chatId!: string | null

  constructor(public geolocation$: GeolocationService, public chatService: ChatService,
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
