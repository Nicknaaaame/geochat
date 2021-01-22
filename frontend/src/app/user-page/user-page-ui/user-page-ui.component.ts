import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../store/user-store/service/user.model";

@Component({
  selector: 'app-user-page-ui',
  templateUrl: './user-page-ui.component.html',
  styles: [
  ]
})
export class UserPageUiComponent implements OnInit {
  @Input()
  user!: User

  constructor() { }

  ngOnInit(): void {
  }

}
