import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    HttpClientModule,
    // StoreModule.forFeature(AUTH_FEATURE_NAME, authReducer),
    // EffectsModule.forFeature([AuthEffects])
  ]
})
export class BlacklistModule {

}
