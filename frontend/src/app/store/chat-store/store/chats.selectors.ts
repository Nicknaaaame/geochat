import {createFeatureSelector, createSelector} from "@ngrx/store";
import {CHATS_FEATURE_NAME, ChatsState} from "./chats.reducer";
import {selectAll} from "./chats.reducer"

const getFeature = createFeatureSelector<ChatsState>(CHATS_FEATURE_NAME)

export const getAllChats = createSelector(
  getFeature,
  selectAll
)
