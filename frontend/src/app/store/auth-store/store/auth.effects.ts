import {Injectable} from "@angular/core";
import {Actions, createEffect, ofType} from "@ngrx/effects";
import {catchError, map, switchMap, tap} from "rxjs/operators";
import {AuthService} from "../service/auth.service";
import {initAuth, login, loginFailed, loginSuccess, logout, logoutSuccess} from "./auth.actions";
import {of} from "rxjs";
import {AuthData} from "./auth.reducer";

@Injectable()
export class AuthEffects {
  login$ = createEffect(() => this.actions$.pipe(
    ofType(login),
    switchMap(action =>
      this.authService.fetchToken(action.code, action.state, action.providerId)
        .pipe(
          map(authData => loginSuccess({authData})),
          catchError(err => of(loginFailed({serverError: err.message})))
        )
    )
  ))

  saveAuthDataToLocalStorage$ = createEffect(() => this.actions$.pipe(
    ofType(loginSuccess),
    tap(({authData}) => {
      localStorage.setItem('authData', JSON.stringify(authData))
    })
  ), {dispatch: false})

  extractAuthData$ = createEffect(() => this.actions$.pipe(
    ofType(initAuth),
    map(() => {
      const authDataString = localStorage.getItem('authData');
      if (!authDataString) {
        return logoutSuccess();
      }
      const authData: AuthData = JSON.parse(authDataString);
      return loginSuccess({authData});
    })
  ))

  logout$ = createEffect(() => this.actions$.pipe(
    ofType(logout),
    map(() => {
      localStorage.removeItem('authData')
      return logoutSuccess()
    })
  ))

  constructor(
    private actions$: Actions,
    private authService: AuthService
  ) {
  }
}
