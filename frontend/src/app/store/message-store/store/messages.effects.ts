import {Injectable} from "@angular/core";
import {Actions, createEffect, ofType} from "@ngrx/effects";
import {MessageService} from "../service/message.service";
import {Store} from "@ngrx/store";
import {addMessage, loadLocalMessages, loadMessagesSuccess, loadPrivateMessages} from "./messages.actions";
import {map, switchMap} from "rxjs/operators";

@Injectable()
export class MessagesEffects {
  loadPrivateMessages$ = createEffect(() => this.actions$.pipe(
    ofType(loadPrivateMessages),
    switchMap((value) =>
      this.messageService.getPrivateMessages(value.chatId)
        .pipe(
          map(messages => {
            // this.messageService.onPrivateMessage(value.chatId).subscribe(message => {
            //   this.store.dispatch(addMessage({message}))
            // })
            return loadMessagesSuccess({messages})
          }),
          // catchError(err => of(loadProfileFailed({serverError: err.exception})))
        )
    )
  ))

  loadLocalMessages$ = createEffect(() => this.actions$.pipe(
    ofType(loadLocalMessages),
    switchMap((value) =>
      this.messageService.getLocalMessages(value.chatId)
        .pipe(
          map(messages => {
            // this.messageService.onLocalMessage(value.chatId).subscribe(message => {
            //   this.store.dispatch(addMessage({message}))
            // })
            return loadMessagesSuccess({messages})
          }),
          // catchError(err => of(loadProfileFailed({serverError: err.exception})))
        )
    )
  ))

  constructor(
    private actions$: Actions,
    private messageService: MessageService,
    private store: Store,
  ) {
  }
}
