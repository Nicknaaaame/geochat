import { Component, OnInit } from '@angular/core';
import {UserService} from "../../store/user-store/service/user.service";
import {User} from "../../store/user-store/service/user.model";

@Component({
  selector: 'app-find-users-ui',
  templateUrl: './find-users-ui.component.html',
  styles: [
  ]
})
export class FindUsersUiComponent implements OnInit {
  usersAround$ = this.userService.getUsersAround()

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

}
