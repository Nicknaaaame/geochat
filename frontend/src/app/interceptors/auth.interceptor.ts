import {Injectable} from '@angular/core'
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpErrorResponse
} from '@angular/common/http'
import {EMPTY, Observable} from 'rxjs'
import {select, Store} from "@ngrx/store"
import {getAccessToken} from "../store/auth-store/store/auth.selectors"
import {catchError, first} from "rxjs/operators"
import {flatMap} from "rxjs/internal/operators"
import {logout} from "../store/auth-store/store/auth.actions";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(
    private store$: Store
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
          catchError(err => {
            if (err instanceof HttpErrorResponse) {
              if (err.status === 401) {
                if(err.message=='Unauthorized'){
                  console.log('Redirect on login page OR sign out')
                  this.store$.dispatch(logout())
                }
                return EMPTY
              }
            }
            throw err
          })
        )
      })
    )
  }
}
