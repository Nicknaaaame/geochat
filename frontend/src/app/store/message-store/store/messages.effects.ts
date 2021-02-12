import {Injectable} from "@angular/core";
import {Actions, createEffect, ofType} from "@ngrx/effects";
import {MessageService} from "../service/message.service";
import {Store} from "@ngrx/store";
import {
  addLocalMessage,
  addMessageSuccess, addPrivateMessage,
  loadLocalMessages,
  loadMessagesSuccess,
  loadPrivateMessages
} from "./messages.actions";
import {map, switchMap} from "rxjs/operators";

@Injectable()
export class MessagesEffects {
  loadPrivateMessages$ = createEffect(() => this.actions$.pipe(
    ofType(loadPrivateMessages),
    switchMap((value) =>
      this.messageService.getPrivateMessages(value.chatId)
        .pipe(
          map(messages => {
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
            return loadMessagesSuccess({messages})
          }),
          // catchError(err => of(loadProfileFailed({serverError: err.exception})))
        )
    )
  ))

  /*addLocalMessage = createEffect(() => this.actions$.pipe(
    ofType(addLocalMessage),
    switchMap((value) =>
      this.messageService.saveLocalMessage(value.message)
        .pipe(
          map(message => {
            return addMessageSuccess({message})
          }),
          // catchError(err => of(loadProfileFailed({serverError: err.exception})))
        )
    )
  ))

  addPrivateMessage = createEffect(() => this.actions$.pipe(
    ofType(addPrivateMessage),
    switchMap((value) =>
      this.messageService.savePrivateMessage(value.message)
        .pipe(
          map(message => {
            return addMessageSuccess({message})
          }),
          // catchError(err => of(loadProfileFailed({serverError: err.exception})))
        )
    )
  ))*/

  constructor(
    private actions$: Actions,
    private messageService: MessageService,
    private store: Store,
  ) {
  }
}
