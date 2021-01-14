import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AuthRoutes} from "./auth.routes";
import {RouterModule} from "@angular/router";
import {LoginComponent} from "./login/login.component";
import {SignupComponent} from "./signup/signup.component";
import {MaterialModule} from "../material/material.module";
import { CallbackComponent } from './callback/callback.component';


@NgModule({
  declarations: [
    LoginComponent,
    SignupComponent,
    CallbackComponent,
  ],
  exports: [
    SignupComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    RouterModule.forChild(AuthRoutes),
  ]
})
export class AuthModule {
}
