import {createAction, props} from "@ngrx/store";
import {Profile} from "../service/profile.model";

export const loadProfile = createAction(
  '[Profile] load profile'
)

export const loadProfileSuccess = createAction(
  '[Profile] load profile success',
  props<{profile: Profile}>()
)

export const loadProfileFailed = createAction(
  '[Profile] load profile failed',
  props<{serverError: string}>()
)

export const updateProfile = createAction(
  '[Profile] update profile',
  props<{profile: Profile, popup?: string}>()
)

export const updateProfileSuccess = createAction(
  '[Profile] update profile success',
  props<{profile: Profile, popup?: string}>()
)

export const updateProfileFailed = createAction(
  '[Profile] update profile failed',
  props<{serverError: string, popup?: string}>()
)
