import {Injectable} from "@angular/core"
import {Actions, createEffect, ofType} from "@ngrx/effects"
import {catchError, distinctUntilChanged, map, skip, switchMap, tap} from "rxjs/operators"
import {AuthService} from "../service/auth.service"
import {initAuth, login, loginFailed, loginSuccess, logout, logoutSuccess} from "./auth.actions"
import {of} from "rxjs"
import {AuthData} from "./auth.reducer"
import {loadProfile} from "../../profile-store/store/profile.actions"
import {Router} from "@angular/router"
import {select, Store} from "@ngrx/store"
import {isAuth} from "./auth.selectors"

@Injectable()
export class AuthEffects {
  login$ = createEffect(() => this.actions$.pipe(
    ofType(login),
    switchMap(action =>
      this.authService.fetchToken(action.code, action.state, action.providerId)
        .pipe(
          map(authData => {
            this.router.navigateByUrl('/')
            return loginSuccess({authData})
          }),
          catchError(err => of(loginFailed({serverError: err.exception})))
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
      const authDataString = localStorage.getItem('authData')
      if (!authDataString) {
        return loginFailed({serverError: ''})
      }
      const authData: AuthData = JSON.parse(authDataString)
      return loginSuccess({authData})
    })
  ))

  logout$ = createEffect(() => this.actions$.pipe(
    ofType(logout),
    map(() => {
      localStorage.removeItem('authData')
      return logoutSuccess()
    })
  ))

  logoutSuccess$ = createEffect(() => this.actions$.pipe(
    ofType(logoutSuccess),
    tap(x => {
      this.router.navigateByUrl("/signup")
    })
  ), {dispatch: false})

  constructor(
    private actions$: Actions,
    private authService: AuthService,
    private store$: Store,
    private router: Router
  ) {
  }
}
