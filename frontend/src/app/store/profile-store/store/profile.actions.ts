import {createAction, props} from "@ngrx/store";
import {Profile} from "../service/profile.model";
import {UpdateProfileRequest} from "../service/update-profile.request";
import {Location} from "../../location-store/service/location.model";

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
  props<{profile: UpdateProfileRequest, popup?: string}>()
)

export const updateProfileLocation = createAction(
  '[Profile] update profile location',
  props<{location: Location, popup?: string}>()
)

export const updateProfileSuccess = createAction(
  '[Profile] update profile success',
  props<{profile: Profile, popup?: string}>()
)

export const updateProfileFailed = createAction(
  '[Profile] update profile failed',
  props<{serverError: string, popup?: string}>()
)
