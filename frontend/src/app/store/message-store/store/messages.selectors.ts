import {createFeatureSelector, createSelector} from "@ngrx/store";
import {MESSAGES_FEATURE_NAME, MessagesState} from "./messages.reducer";
import {selectAll} from './messages.reducer'
import {Message} from "../service/message.model";

const getFeature = createFeatureSelector<MessagesState>(MESSAGES_FEATURE_NAME)

export const messagesSort = (a: Message, b: Message) => {
  let aDate = new Date(a.creationDate)
  let bDate = new Date(b.creationDate)
  return  aDate.getTime() - bDate.getTime()
}

export const getLocalMessages = (id: number | string) => createSelector(
  getAllMessages,
  messages => messages.filter(value => value.localChat?.id == id).sort(messagesSort)
)

export const getPrivateMessages = (id: number | string) => createSelector(
  getAllMessages,
  messages => messages.filter(value => value.privateChat?.id == id).sort(messagesSort)
)

export const getAllMessages = createSelector(
  getFeature,
  selectAll
)
