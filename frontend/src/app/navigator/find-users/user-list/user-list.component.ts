import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../../store/user-store/service/user.model";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styles: [
  ]
})
export class UserListComponent implements OnInit {
  @Input()
  users!: User[]

  constructor() { }

  ngOnInit(): void {
  }

}
