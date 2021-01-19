import {createFeatureSelector, createSelector} from "@ngrx/store"
import {AUTH_FEATURE_NAME, AuthState} from "./auth.reducer"

const getFeature = createFeatureSelector<AuthState>(AUTH_FEATURE_NAME)

export const getAuthLoading = createSelector(
  getFeature,
  state => state.loading
)

export const getAuthLoaded = createSelector(
  getFeature,
  state => state.loaded
)

export const getAuthServerError = createSelector(
  getFeature,
  state => state.serverError
)

export const getAuthData = createSelector(
  getFeature,
  state => state.authData
)

export const getAccessToken = createSelector(
  getAuthData,
  authData => authData && authData.accessToken
)

export const isAuth = createSelector(
  getAccessToken,
  accessToken => !!accessToken
)
