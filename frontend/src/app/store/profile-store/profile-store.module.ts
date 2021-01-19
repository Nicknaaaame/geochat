import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {HttpClientModule} from "@angular/common/http";
import {StoreModule} from "@ngrx/store";
import {AUTH_FEATURE_NAME, authReducer} from "../auth-store/store/auth.reducer";
import {EffectsModule} from "@ngrx/effects";
import {AuthEffects} from "../auth-store/store/auth.effects";
import {PROFILE_FEATURE_NAME, profileReducer} from "./store/profile.reducer";
import {ProfileEffects} from "./store/profile.effects";

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    HttpClientModule,
    StoreModule.forFeature(PROFILE_FEATURE_NAME, profileReducer),
    EffectsModule.forFeature([ProfileEffects])
  ]
})
export class ProfileStoreModule { }
