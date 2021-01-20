import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../../../store/user-store/service/user.model";

@Component({
  selector: 'app-user-row-ui',
  templateUrl: './user-row-ui.component.html',
  styles: [
  ]
})
export class UserRowUiComponent implements OnInit {
  @Input()
  user!: User

  constructor() { }

  ngOnInit(): void {
  }

}
