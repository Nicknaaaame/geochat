import {createAction, props} from "@ngrx/store";
import {LocalChat} from "../service/local-chat.model";
import {PrivateChat} from "../service/private-chat.model";

export const loadChats = createAction(
  '[Chats] load chats'
)

export const loadChatsSuccess = createAction(
  '[Chats] load chats success',
  props<{ chats: (LocalChat | PrivateChat)[] }>()
)

export const loadChatsFailed = createAction(
  '[Chats] load chats failed'
)

export const addChat = createAction(
  '[Chats] add chat',
  props<{ chat: LocalChat | PrivateChat }>()
)
