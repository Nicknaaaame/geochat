import {ProfileComponent} from "./profile/profile.component";
import {Routes} from "@angular/router";
import {AuthGuard} from "../auth/auth.guard";

export const ProfileRoutes: Routes = [
  {path: 'profile', component: ProfileComponent, canActivate: [AuthGuard]}
]

