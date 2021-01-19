import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {AuthService} from "../../store/auth-store/service/auth.service";
import {Store} from "@ngrx/store";
import {getAuthLoading} from "../../store/auth-store/store/auth.selectors";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styles: [
      `
      :host {
        display: flex;
        justify-content: center;
        margin: 100px 0;
      }

      button {
        display: flex;
        justify-content: center;
      }
    `
  ]
})
export class SignupComponent implements OnInit {
  isAuthLoading$ = this.store.select(getAuthLoading)

  constructor(private authService: AuthService, private store: Store) {

  }

  ngOnInit(): void {
  }

  onClickOpenLoginPage(providerId: string) {
    this.authService.openLoginPage(providerId)
  }
}
