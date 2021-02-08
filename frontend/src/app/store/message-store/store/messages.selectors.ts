import {createFeatureSelector, createSelector} from "@ngrx/store";
import {MESSAGES_FEATURE_NAME, MessagesState} from "./messages.reducer";
import {selectAll} from './messages.reducer'
import {Message} from "../service/message.model";

const getFeature = createFeatureSelector<MessagesState>(MESSAGES_FEATURE_NAME)

export const getLocalMessages = (id: number | string) => createSelector(
  getAllMessages,
  messages => messages.filter(value => value.localChat.id == id)
)

export const getPrivateMessages = (id: number | string) => createSelector(
  getAllMessages,
  messages => messages.filter(value => value.privateChat.id == id)
)

export const getAllMessages = createSelector(
  getFeature,
  selectAll
)
