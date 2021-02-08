import {Component, OnInit} from '@angular/core';
import {Store} from "@ngrx/store";
import {isAuth} from "../store/auth-store/store/auth.selectors";
import {logout, logoutSuccess} from "../store/auth-store/store/auth.actions";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styles: []
})
export class HeaderComponent implements OnInit {

  isAuth$ = this.store.select(isAuth)

  constructor(private store: Store) {
  }

  ngOnInit(): void {
  }

  onClickLogout() {
    this.store.dispatch(logout())
  }
}
