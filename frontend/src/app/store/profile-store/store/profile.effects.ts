import {Injectable} from "@angular/core";
import {Actions, createEffect, ofType} from "@ngrx/effects";
import {AuthService} from "../../auth-store/service/auth.service";
import {ProfileService} from "../service/profile.service";
import {
  loadProfile,
  loadProfileFailed,
  loadProfileSuccess,
  updateProfile, updateProfileFailed,
  updateProfileSuccess
} from "./profile.actions";
import {catchError, map, switchMap, take, tap} from "rxjs/operators";
import {of} from "rxjs";
import {MatSnackBar} from "@angular/material/snack-bar";

@Injectable()
export class ProfileEffects {
  loadProfile$ = createEffect(() => this.actions$.pipe(
    ofType(loadProfile),
    switchMap(() =>
      this.profileService.getProfile()
        .pipe(
          map(profile => loadProfileSuccess({profile})),
          catchError(err => of(loadProfileFailed({serverError: err.exception})))
        )
    )
  ))

  updateProfile$ = createEffect(() => this.actions$.pipe(
    ofType(updateProfile),
    switchMap((action) =>
      this.profileService.updateProfile(action.profile)
        .pipe(
          map(profile => updateProfileSuccess()),
          catchError(err => of(updateProfileFailed({serverError: err.exception})))
        )
    )
  ))

  updateProfileSuccess$ = createEffect(() => this.actions$.pipe(
    ofType(updateProfileSuccess),
    tap((action) => {
        this.matSnackBar.open("Updated success", "CLOSE", {duration: 3000})
      }
    )
  ), {dispatch: false})

  updateProfileFailed$ = createEffect(() => this.actions$.pipe(
    ofType(updateProfileFailed),
    tap((action) => {
        this.matSnackBar.open(action.serverError, "CLOSE", {duration: 3000})
      }
    )
  ), {dispatch: false})

  constructor(
    private actions$: Actions,
    private profileService: ProfileService,
    private matSnackBar: MatSnackBar
  ) {
  }
}
