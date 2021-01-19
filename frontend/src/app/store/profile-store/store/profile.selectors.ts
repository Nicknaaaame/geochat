import {createFeatureSelector, createSelector} from "@ngrx/store";
import {PROFILE_FEATURE_NAME, ProfileState} from "./profile.reducer";
import {Profile} from "../service/profile.model";

const getFeature = createFeatureSelector<ProfileState>(PROFILE_FEATURE_NAME)

export const getProfileLoading = createSelector(
  getFeature,
  state => state.loading
)

export const getProfileLoaded = createSelector(
  getFeature,
  state => state.loaded
)

export const getProfileServerError = createSelector(
  getFeature,
  state => state.serverError
)

export const getProfile = createSelector(
  getFeature,
  state => state.profile
)
