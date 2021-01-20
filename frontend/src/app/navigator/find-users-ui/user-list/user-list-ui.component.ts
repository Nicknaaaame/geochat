import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../../store/user-store/service/user.model";

@Component({
  selector: 'app-user-list-ui',
  templateUrl: './user-list-ui.component.html',
  styles: [
  ]
})
export class UserListUiComponent implements OnInit {
  @Input()
  users!: User[]

  constructor() { }

  ngOnInit(): void {
  }

}
