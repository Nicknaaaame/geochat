import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../../../store/user-store/service/user.model";

@Component({
  selector: 'app-user-row',
  templateUrl: './user-row.component.html',
  styles: [
  ]
})
export class UserRowComponent implements OnInit {
  @Input()
  user!: User

  constructor() { }

  ngOnInit(): void {
  }

}
