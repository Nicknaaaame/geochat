import {Injectable} from "@angular/core";
import {Actions, createEffect, ofType} from "@ngrx/effects";
import {MessageService} from "../../message-store/service/message.service";
import {Store} from "@ngrx/store";
import {ChatService} from "../service/chat.service";
import {loadChats, loadChatsSuccess} from "./chats.actions";
import {map, switchMap, tap} from "rxjs/operators";

@Injectable()
export class ChatsEffects {
  loadChats$ = createEffect(() => this.actions$.pipe(
    ofType(loadChats),
    switchMap(() =>
      this.chatService.getUserChats()
        .pipe(
          map(chats => {
            chats.forEach(value => {
              if (ChatService.isLocalChat(value))
                this.messageService.onLocalMessage(value.id)
              else
                this.messageService.onPrivateMessage(value.id)
            })
            return  loadChatsSuccess({chats})
          })
        )
    )
  ))

  /*loadChatSuccess$ = createEffect(() => this.actions$.pipe(
    ofType(loadChatsSuccess),
    tap((action) => {
        action.chats.forEach(value => {
          console.log(value.id)
          if (ChatService.isLocalChat(value))
            this.messageService.onLocalMessage(value.id)
          else
            this.messageService.onPrivateMessage(value.id)
        })
      }
    )
  ))*/

  constructor(
    private actions$: Actions,
    private chatService: ChatService,
    private messageService: MessageService,
    private store: Store,
  ) {
  }
}
