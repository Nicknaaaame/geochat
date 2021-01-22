import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UserPageComponent} from "./user-page.component";
import {RouterModule, Routes} from "@angular/router";
import {NavigatorComponent} from "../navigator/navigator.component";
import {AuthGuard} from "../auth/auth.guard";
import {UserPageUiComponent} from './user-page-ui/user-page-ui.component';
import {MaterialModule} from "../material/material.module";
import {FormsModule} from "@angular/forms";
import {ProfileModule} from "../profile/profile.module";

const routes: Routes = [
  {path: 'user/:userId', component: UserPageComponent, canActivate: [AuthGuard]}
]

@NgModule({
  declarations: [
    UserPageComponent,
    UserPageUiComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    FormsModule,
    ProfileModule,
    RouterModule.forChild(routes)
  ]
})
export class UserPageModule {
}
