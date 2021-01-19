import {Component, OnInit} from '@angular/core';
import {Store} from "@ngrx/store";
import {login} from "../../store/auth-store/store/auth.actions";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-callback',
  templateUrl: './callback.component.html',
  styles: []
})
export class CallbackComponent implements OnInit {

  constructor(private store: Store, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(value => {
      this.route.queryParams.subscribe(p => {
        console.log("login")
        this.store.dispatch(login({code: p.code, state: p.state, providerId: value.get('providerId') as string}))
      })
    })
  }

}
