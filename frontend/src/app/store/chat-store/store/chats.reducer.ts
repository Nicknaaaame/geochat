import {createEntityAdapter, EntityAdapter, EntityState} from "@ngrx/entity";
import {LocalChat} from "../service/local-chat.model";
import {PrivateChat} from "../service/private-chat.model";
import {createReducer, on} from "@ngrx/store";
import {addChat, loadChatsSuccess} from "./chats.actions";

export const CHATS_FEATURE_NAME = 'chats'

export interface ChatsState extends EntityState<LocalChat | PrivateChat> {
  // loading: boolean
  loaded: boolean
  // serverError: string
}

export const adapter: EntityAdapter<LocalChat | PrivateChat> = createEntityAdapter<LocalChat | PrivateChat>()

export const initialState: ChatsState = adapter.getInitialState({
  loaded: false,
})

export const chatsReducer = createReducer(
  initialState,
  on(loadChatsSuccess, (state, action) => {
    return adapter.setAll(action.chats, {...state, loaded: true})
  }),
  on(addChat, (state, action) => {
    return adapter.addOne(action.chat, {...state, loaded: true})
  })
)

export const {selectAll, selectIds} = adapter.getSelectors()
