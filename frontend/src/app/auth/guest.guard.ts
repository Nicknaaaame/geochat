import {Injectable} from "@angular/core"
import {
  ActivatedRouteSnapshot,
  CanActivate,
  CanLoad,
  Route,
  Router,
  RouterStateSnapshot, UrlSegment,
  UrlTree
} from "@angular/router"
import {Store} from "@ngrx/store"
import {Observable} from "rxjs"
import {isAuth} from "../store/auth-store/store/auth.selectors"
import {first, map} from "rxjs/operators"

@Injectable()
export class GuestGuard implements CanActivate, CanLoad {
  constructor(
    private router: Router,
    private store: Store
  ) {
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.getIsGuest()
  }
  canLoad(
    route: Route,
    segments: UrlSegment[]): Observable<boolean> | Promise<boolean> | boolean {
    return this.getIsGuest()
  }

  private getIsGuest(): Observable<boolean> {
    return this.store.select(isAuth).pipe(
      first(),
      map(isAuth => {
        // console.log("Is Guest guard", !isAuth)
        if (isAuth) {
          this.router.navigate(['/'])
        }
        return !isAuth
      })
    )
  }
}
