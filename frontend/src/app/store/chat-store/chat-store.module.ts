import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HttpClientModule} from "@angular/common/http";
import {StoreModule} from "@ngrx/store";
import {EffectsModule} from "@ngrx/effects";
import {CHATS_FEATURE_NAME, chatsReducer} from "./store/chats.reducer";
import {ChatsEffects} from "./store/chats.effects";

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    HttpClientModule,
    StoreModule.forFeature(CHATS_FEATURE_NAME, chatsReducer),
    EffectsModule.forFeature([ChatsEffects])
  ]
})
export class ChatStoreModule {
}
