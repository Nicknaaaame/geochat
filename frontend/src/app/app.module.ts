import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MaterialModule} from "./material/material.module";
import {HeaderModule} from "./header/header.module";
import {AuthModule} from "./auth/auth.module";
import {EffectsModule} from '@ngrx/effects';
import {StoreRouterConnectingModule} from '@ngrx/router-store';
import {StoreModule} from "@ngrx/store";
import {AuthStoreModule} from "./store/auth-store/auth-store.module";
import {environment} from "../environments/environment";
import {StoreDevtoolsModule} from '@ngrx/store-devtools';
import {AuthInterceptor} from "./interceptors/auth.interceptor";
import {HTTP_INTERCEPTORS} from "@angular/common/http";
import {ProfileStoreModule} from "./store/profile-store/profile-store.module";
import {ProfileModule} from "./profile/profile.module";
import {AuthGuard} from "./auth/auth.guard";
import {GuestGuard} from "./auth/guest.guard";
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {NbLayoutModule, NbThemeModule} from '@nebular/theme';
import {NbEvaIconsModule} from '@nebular/eva-icons';
import {MessagesStoreModule} from "./store/message-store/messages-store.module";
import {ChatStoreModule} from "./store/chat-store/chat-store.module";
import {ChatsNearbyModule} from "./chats-nearby/chats-nearby.module";
import {MAT_SNACK_BAR_DEFAULT_OPTIONS} from "@angular/material/snack-bar";
import {UserChatsModule} from "./user-chats/user-chats.module";

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    HeaderModule,
    AuthModule,
    AuthStoreModule,
    ProfileStoreModule,
    MessagesStoreModule,
    ChatStoreModule,
    ProfileModule,
    ChatsNearbyModule,
    UserChatsModule,
    StoreModule.forRoot({}, {}),
    EffectsModule.forRoot([]),
    StoreDevtoolsModule.instrument({maxAge: 25, logOnly: environment.production}),
    StoreRouterConnectingModule.forRoot(),
    NgbModule,
    NbThemeModule.forRoot({name: 'default'}),
    NbLayoutModule,
    NbEvaIconsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS, // Injection Token
      useClass: AuthInterceptor, // класс интерсептора SPI
      multi: true // мы внедряем массив
    },
    {provide: MAT_SNACK_BAR_DEFAULT_OPTIONS, useValue: {duration: 3500}},
    AuthGuard,
    GuestGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
