import {SignupComponent} from "./signup/signup.component";
import {CallbackComponent} from "./callback/callback.component";
import {Routes} from "@angular/router";
import {GuestGuard} from "./guest.guard";

export const AuthRoutes: Routes = [
  {path: 'signup', component: SignupComponent, canActivate: [GuestGuard]},
  {path: 'oauth2/callback/:providerId', component: CallbackComponent},
];
