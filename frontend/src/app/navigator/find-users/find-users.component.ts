import { Component, OnInit } from '@angular/core';
import {UserService} from "../../store/user-store/service/user.service";
import {User} from "../../store/user-store/service/user.model";

@Component({
  selector: 'app-find-users',
  templateUrl: './find-users.component.html',
  styles: [
  ]
})
export class FindUsersComponent implements OnInit {
  usersAround$ = this.userService.getUsersAround()

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

}
