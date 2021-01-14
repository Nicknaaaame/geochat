import {createReducer, on} from "@ngrx/store";
import {login, loginFailed, loginSuccess, logoutSuccess} from "./auth.actions";

export const AUTH_FEATURE_NAME = 'auth';

export interface AuthData {
  accessToken: string
}

export interface AuthState {
  loading: boolean
  loaded: boolean
  serverError: string
  authData: AuthData
}

const initialState: AuthState = {
  loaded: false,
  loading: false,
  serverError: '',
  authData: {} as AuthData
}

export const authReducer = createReducer(
  initialState,
  on(login, state => ({
    ...state,
    loading: true,
  })),
  on(loginSuccess, (state, {authData}) => ({
    ...state,
    authData: authData,
    loaded: true,
    loading: false,
    serverError: ''
  })),
  on(loginFailed, (state, {serverError}) => ({
    ...state,
    loaded: true,
    loading: false,
    serverError: serverError
  })),
  on(logoutSuccess, () => ({
    ...initialState
  }))
)
