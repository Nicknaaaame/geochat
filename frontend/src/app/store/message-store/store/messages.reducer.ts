import {Message} from "../service/message.model";
import {createReducer, on} from "@ngrx/store";
import {addMessageSuccess, loadPrivateMessages, loadMessagesSuccess} from "./messages.actions";
import {createEntityAdapter, EntityAdapter, EntityState} from "@ngrx/entity";

export const MESSAGES_FEATURE_NAME = 'messages'

export interface MessagesState extends EntityState<Message> {
  // loading: boolean
  loaded: boolean
  // serverError: string
}

export const adapter: EntityAdapter<Message> = createEntityAdapter<Message>({

})

export const initialState: MessagesState = adapter.getInitialState({
  loaded: false,
})

export const messagesReducer = createReducer(
  initialState,
  on(loadMessagesSuccess, (state, action) => {
    return adapter.addMany(action.messages, {...state, loaded: true})
  }),
  on(addMessageSuccess, (state, action) => {
    return adapter.addOne(action.message, {...state, loaded: true})
  })
)

export const {selectAll, selectIds} = adapter.getSelectors()
