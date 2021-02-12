import {Injectable} from "@angular/core";
import {Actions, createEffect, ofType} from "@ngrx/effects";
import {MessageService} from "../../message-store/service/message.service";
import {Store} from "@ngrx/store";
import {ChatService} from "../service/chat.service";
import {loadChats, loadChatsSuccess} from "./chats.actions";
import {map, switchMap, tap} from "rxjs/operators";
import {addLocalMessage, addMessageSuccess, addPrivateMessage} from "../../message-store/store/messages.actions";

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
                this.messageService.onLocalMessage(value.id).subscribe(message =>
                  this.store.dispatch(addMessageSuccess({message})))
              else
                this.messageService.onPrivateMessage(value.id).subscribe(message =>
                  this.store.dispatch(addMessageSuccess({message})))
            })
            return loadChatsSuccess({chats})
          })
        )
    )
  ))

  constructor(
    private actions$: Actions,
    private chatService: ChatService,
    private messageService: MessageService,
    private store: Store,
  ) {
  }
}
