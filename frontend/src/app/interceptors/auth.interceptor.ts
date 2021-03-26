import {Injectable} from '@angular/core'
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpErrorResponse
} from '@angular/common/http'
import {EMPTY, Observable, throwError} from 'rxjs'
import {select, Store} from "@ngrx/store"
import {getAccessToken} from "../store/auth-store/store/auth.selectors"
import {catchError, first} from "rxjs/operators"
import {flatMap} from "rxjs/internal/operators"
import {logout} from "../store/auth-store/store/auth.actions";
import {MatSnackBar} from "@angular/material/snack-bar";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(
    private store$: Store,
    private matSnackBar: MatSnackBar
  ) {
  }

  intercept(
    request: HttpRequest<unknown>,
    next: HttpHandler
  ): Observable<HttpEvent<unknown>> {
    return this.store$.pipe(
      select(getAccessToken),
      first(),
      flatMap(token => {
        const authRequest = token ? request.clone({
          setHeaders: {
            Authorization: token
          }
        }) : request
        return next.handle(authRequest).pipe(
          catchError((err: HttpErrorResponse) => {
            if (err.status === 401) {
              if (err.error.error == 'Unauthenticated') {
                console.log('Redirect on login page OR sign out')
                this.store$.dispatch(logout())
              }
              return EMPTY
            }
            let errorMessage = '';
            if (err.error instanceof ErrorEvent) {
              // client-side error
              errorMessage = `Error: ${err.error.message}`;
            } else {
              // server-side error
              errorMessage = `${err.error.message}`;
            }
            console.log(errorMessage)
            this.matSnackBar.open(errorMessage)
            return throwError(err)
          })
        )
      })
    )
  }
}
