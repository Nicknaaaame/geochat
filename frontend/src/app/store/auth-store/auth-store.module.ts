import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {Store, StoreModule} from "@ngrx/store";
import {AUTH_FEATURE_NAME, authReducer} from "./store/auth.reducer";
import {HttpClientModule} from "@angular/common/http";
import {EffectsModule} from "@ngrx/effects";
import {AuthEffects} from "./store/auth.effects";
import {initAuth} from "./store/auth.actions";
import {loadProfile} from "../profile-store/store/profile.actions";
import {isAuth} from "./store/auth.selectors";
import {ChatService} from "../chat-store/service/chat.service";
import {NotificationService} from "../notification-service/notification.service";

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    HttpClientModule,
    StoreModule.forFeature(AUTH_FEATURE_NAME, authReducer),
    EffectsModule.forFeature([AuthEffects])
  ]
})
export class AuthStoreModule {
  constructor(private store: Store, private chatService: ChatService, private notificationService: NotificationService) {
    store.dispatch(initAuth())
    //TODO may be bug in future
    store.select(isAuth).subscribe(value => {
      if (value) {
        store.dispatch(loadProfile())
        notificationService.initSubs()
      }
    })
  }
}
