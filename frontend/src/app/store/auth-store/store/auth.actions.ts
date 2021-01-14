import {createAction, props} from "@ngrx/store";
import {AuthData} from "./auth.reducer";

export const login = createAction(
  '[Auth] login',
  props<{ code: string, state: string, providerId: string }>()
)

export const loginSuccess = createAction(
  '[Auth] login success',
  props<{ authData: AuthData }>()
)

export const loginFailed = createAction(
  '[Auth] login failed',
  props<{ serverError: string }>()
)

export const initAuth = createAction(
  '[Auth] init auth'
)

export const logout = createAction(
  '[Auth] logout'
)

export const logoutSuccess = createAction(
  '[Auth] logout success'
)
