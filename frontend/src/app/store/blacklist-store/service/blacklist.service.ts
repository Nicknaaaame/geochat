import {Injectable} from "@angular/core";
import {environment} from "../../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Blacklist} from "./blacklist.model";
import {Observable} from "rxjs";
import {catchError, map, mergeMap, switchMap, take, withLatestFrom} from "rxjs/operators";
import {entryPointKeyFor} from "@angular/compiler-cli/src/ngtsc/routing";
import {Store} from "@ngrx/store";
import {getProfile} from "../../profile-store/store/profile.selectors";
import {flatMap} from "rxjs/internal/operators";
import {profileReducer} from "../../profile-store/store/profile.reducer";
import {User} from "../../user-store/service/user.model";

@Injectable({
  providedIn: 'root'
})
export class BlacklistService {
  private apiUrl = environment.baseUrl + "/api/blacklist"

  constructor(private http: HttpClient, private store: Store) {
  }

  blockUser(userId: number | string, chatId: number | string) {
    return this.http.post(this.apiUrl + "/block", {userId, chatId})
  }

  unblockUser(userId: number | string, chatId: number | string) {
    return this.http.post(this.apiUrl + "/unblock", {userId, chatId})
  }

  getBlackList(chatId: number | string) {
    return this.http.get<User[]>(this.apiUrl + '/chat/' + chatId)
  }

  //remove below
  blockUser_(userId: number | string) {
    return this.http.post(this.apiUrl + "/block/" + userId, {})
  }

  unblockUser_(userId: number | string) {
    return this.http.post(this.apiUrl + "/unblock/" + userId, {})
  }

  getBlackList_() {
    return this.http.get<User[]>(this.apiUrl)
  }

  getBlockedList() {
    return this.http.get<User[]>(this.apiUrl + "/blocked")
  }

  isUserBlocked(userId: number | string): Observable<boolean> {
    /*return this.getBlockedList().pipe(take(1),
      withLatestFrom(this.store.select(getProfile)),
      map(([value, profile]) => {
          return !!value.find(entry => entry.blocked.id == profile.id)
        }
      ))*/
    return this.http.get<boolean>(this.apiUrl + `/is-blocked/${userId}`)
  }

  isUserInBlackList(userId: number | string): Observable<boolean> {
    /*return this.getBlackList().pipe(take(1), map(value => {
      return !!value.find(entry => entry.blocked.id == userId)
    }))*/
    return this.http.get<boolean>(this.apiUrl + `/is-in-blacklist/${userId}`)
  }

  canWrite(chatId: number | string): Observable<boolean> {
    return this.http.get<boolean>(this.apiUrl + `/can-write/${chatId}`)
  }
}
