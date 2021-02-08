import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {HttpClientModule} from "@angular/common/http";
import {StoreModule} from "@ngrx/store";
import {EffectsModule} from "@ngrx/effects";
import {MESSAGES_FEATURE_NAME, messagesReducer} from "./store/messages.reducer";
import {MessagesEffects} from "./store/messages.effects";

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    HttpClientModule,
    StoreModule.forFeature(MESSAGES_FEATURE_NAME, messagesReducer),
    EffectsModule.forFeature([MessagesEffects])
  ]
})
export class MessagesStoreModule {
}
