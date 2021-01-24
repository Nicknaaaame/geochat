import {Component, Input, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {ChatService} from "../../store/chat-store/service/chat.service";
import {SaveLocalChatRequest} from "../../store/chat-store/service/save-local-chat.request";
import {catchError, tap} from "rxjs/operators";
import {pipe, throwError} from "rxjs";
import {Chat} from "../../store/chat-store/service/chat.model";

@Component({
  selector: 'app-find-chat',
  templateUrl: './find-chat.component.html',
  styles: []
})
export class FindChatComponent implements OnInit {
  @ViewChild('createChatDialog')
  createChatDialog!: TemplateRef<any>

  chosenChat!: Chat

  saveChatRequest: SaveLocalChatRequest = {} as SaveLocalChatRequest

  chatsAround$ = this.chatService.getChatsAround()

  constructor(private dialog: MatDialog, private chatService: ChatService) {
    this.saveChatRequest.name = ''
    this.saveChatRequest.users = []
  }

  ngOnInit(): void {
  }

  onClickCreateChat() {
    this.dialog.open(this.createChatDialog).afterClosed().subscribe(value => {
      if (value == 'save')
        this.saveChat()
    })
  }

  saveChat() {
    this.chatService.saveLocalChat(this.saveChatRequest).subscribe(value => console.log(value))
  }

  onChatPicked(chat: Chat) {
    this.chosenChat = chat
  }
}
