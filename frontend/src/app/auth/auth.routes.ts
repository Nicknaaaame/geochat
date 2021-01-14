import {SignupComponent} from "./signup/signup.component";
import {CallbackComponent} from "./callback/callback.component";

export const AuthRoutes = [
  {path: 'signup', component: SignupComponent},
  {path: 'oauth2/callback/:providerId', component: CallbackComponent}
];
