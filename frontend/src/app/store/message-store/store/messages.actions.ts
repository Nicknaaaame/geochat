import {createAction, props} from "@ngrx/store";
import {Message} from "../service/message.model";

export const loadPrivateMessages = createAction(
  '[Messages] load private messages',
  props<{ chatId: number | string }>()
)

export const loadLocalMessages = createAction(
  '[Messages] load local messages',
  props<{ chatId: number | string }>()
)

export const loadMessagesSuccess = createAction(
  '[Messages] load messages success',
  props<{ messages: Message[] }>()
)

export const addMessageSuccess = createAction(
  '[Messages] add message',
  props<{ message: Message }>()
)

export const addPrivateMessage = createAction(
  '[Messages] add private message',
  props<{ message: { chatId: number | string, text: string } }>()
)

export const addLocalMessage = createAction(
  '[Messages] add local message',
  props<{ message: { chatId: number | string, text: string } }>()
)
