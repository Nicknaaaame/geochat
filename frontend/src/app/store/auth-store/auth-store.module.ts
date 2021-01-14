import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {Store, StoreModule} from "@ngrx/store";
import {AUTH_FEATURE_NAME, authReducer} from "./store/auth.reducer";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {EffectsModule} from "@ngrx/effects";
import {AuthEffects} from "./store/auth.effects";
import {initAuth} from "./store/auth.actions";


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
  constructor(private store: Store) {
    store.dispatch(initAuth())
  }
}
