import {Component, OnInit} from '@angular/core';
import {User} from "../store/user-store/service/user.model";
import {UserService} from "../store/user-store/service/user.service";
import {ActivatedRoute} from "@angular/router";
import {Observable} from "rxjs";

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styles: []
})
export class UserPageComponent implements OnInit {
  userId!: number
  user$!: Observable<User>

  constructor(private userService: UserService, private route: ActivatedRoute) {
    route.paramMap.subscribe(value => {
      this.user$ = userService.getUser(String(value.get("userId")))
    })
  }

  ngOnInit(): void {
  }

}
