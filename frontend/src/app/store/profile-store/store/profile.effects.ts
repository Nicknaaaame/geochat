import {Injectable} from "@angular/core";
import {Actions, createEffect, ofType} from "@ngrx/effects";
import {AuthService} from "../../auth-store/service/auth.service";
import {ProfileService} from "../service/profile.service";
import {
  loadProfile,
  loadProfileFailed,
  loadProfileSuccess,
  updateProfile, updateProfileFailed, updateProfileLocation,
  updateProfileSuccess
} from "./profile.actions";
import {catchError, map, switchMap, take, tap} from "rxjs/operators";
import {of} from "rxjs";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Store} from "@ngrx/store";
import {GeolocationService} from "@ng-web-apis/geolocation";
import {Location} from "../../location-store/service/location.model";
import {logout} from "../../auth-store/store/auth.actions";

@Injectable()
export class ProfileEffects {
  loadProfile$ = createEffect(() => this.actions$.pipe(
    ofType(loadProfile),
    switchMap(() =>
      this.profileService.getProfile()
        .pipe(
          map(profile => {
            return loadProfileSuccess({profile})
          }),
          catchError(err => of(loadProfileFailed({serverError: err.exception})))
        )
    )
  ))

  loadProfileSuccess$ = createEffect(() => this.actions$.pipe(
    ofType(loadProfileSuccess),
    tap((action) => {
        this.geolocation.pipe(take(1)).subscribe(value => {
          let location: Location = {
            id: action.profile.location ? action.profile.location.id : null,
            latitude: value.coords.latitude,
            longitude: value.coords.longitude
          }
          this.store.dispatch(updateProfileLocation({location}))
          if (!action.profile.location)
            window.location.reload()
        })
      }
    )
  ), {dispatch: false})

  updateProfileLocation$ = createEffect(() => this.actions$.pipe(
    ofType(updateProfileLocation),
    switchMap((action) => {
        return this.profileService.updateProfileLocation(action.location)
          .pipe(
            map(profile => {
              return updateProfileSuccess({profile, popup: action.popup})
            }),
            catchError(err => of(updateProfileFailed({serverError: err.exception})))
          )
      }
    )
  ))

  updateProfile$ = createEffect(() => this.actions$.pipe(
    ofType(updateProfile),
    switchMap((action) => {
        console.log(action)
        return this.profileService.updateProfile(action.profile)
          .pipe(
            map(profile => {
              return updateProfileSuccess({profile, popup: action.popup})
            }),
            catchError(err => of(updateProfileFailed({serverError: err.exception})))
          )
      }
    )
  ))

  updateProfileSuccess$ = createEffect(() => this.actions$.pipe(
    ofType(updateProfileSuccess),
    tap((action) => {
        if (action.popup)
          this.matSnackBar.open(action.popup, "", {duration: 3000})
      }
    )
  ), {dispatch: false})

  updateProfileFailed$ = createEffect(() => this.actions$.pipe(
    ofType(updateProfileFailed),
    tap((action) => {
        this.matSnackBar.open(action.serverError, "", {duration: 3000})
      }
    )
  ), {dispatch: false})

  constructor(
    private actions$: Actions,
    private profileService: ProfileService,
    private matSnackBar: MatSnackBar,
    private store: Store,
    private geolocation: GeolocationService
  ) {
  }
}
