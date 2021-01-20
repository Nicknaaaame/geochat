import { Component, OnInit } from '@angular/core';
import {ChatService} from "../../store/chat-store/service/chat.service";

@Component({
  selector: 'navigator-ui',
  templateUrl: './navigator-ui.component.html',
  styles: [
  ]
})
export class NavigatorUiComponent implements OnInit {

  constructor(private chatService: ChatService) { }

  ngOnInit(): void {
  }

}
