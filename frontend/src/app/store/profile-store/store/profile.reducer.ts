import {Profile} from "../service/profile.model";
import {createReducer, on} from "@ngrx/store";
import {
  loadProfile,
  loadProfileFailed,
  loadProfileSuccess,
  updateProfile,
  updateProfileFailed, updateProfileSuccess
} from "./profile.actions";

export const PROFILE_FEATURE_NAME = 'profile';

export interface ProfileState {
  loading: boolean
  loaded: boolean
  serverError: string
  profile: Profile
}

const initialState: ProfileState = {
  loaded: false,
  loading: false,
  serverError: '',
  profile: {} as Profile
}

export const profileReducer = createReducer(
  initialState,
  on(loadProfile, state => ({
      ...state,
      loading: true
    })
  ),
  on(loadProfileSuccess, (state, {profile}) => ({
    ...state,
    loading: false,
    loaded: true,
    serverError: '',
    profile
  })),
  on(loadProfileFailed, (state, {serverError}) => ({
    ...state,
    loading: false,
    loaded: true,
    serverError
  })),
  /*on(updateProfile, (state, {profile})=>({
    ...state,
    loading: false,
    loaded: true,
    serverError: '',
    profile: {
      id: state.profile.id,
      email: state.profile.email,

    }
  })),*/
  on(updateProfileSuccess, (state, {profile}) => ({
    ...state,
    loading: false,
    loaded: true,
    serverError: '',
    profile
  })),
  on(updateProfileFailed, (state, {serverError}) => ({
    ...state,
    loading: false,
    loaded: true,
    serverError
  }))
)
