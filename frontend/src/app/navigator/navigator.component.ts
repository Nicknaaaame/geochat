import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-navigator',
  templateUrl: './navigator.component.html',
  styles: []
})
export class NavigatorComponent implements OnInit {
  tabs = [
    {"route": '/chats/find-chats', 'label': 'Find chats'},
    {"route": '/chats/find-users', 'label': 'Find people'},
    {"route": '/chats/user-chats', 'label': 'User chats'}
  ]

  constructor() {
  }

  ngOnInit(): void {
  }

}
