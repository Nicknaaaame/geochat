import {Component, Input, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {ChatService} from "../../store/chat-store/service/chat.service";
import {SaveLocalChatRequest} from "../../store/chat-store/service/save-local-chat.request";
import {catchError, tap} from "rxjs/operators";
import {pipe, throwError} from "rxjs";
import {LocalChat} from "../../store/chat-store/service/local-chat.model";
import {PrivateChat} from "../../store/chat-store/service/private-chat.model";
import {Store} from "@ngrx/store";

@Component({
  selector: 'app-find-chat',
  templateUrl: './find-chat.component.html',
  styles: []
})
export class FindChatComponent implements OnInit {
  @ViewChild('createChatDialog')
  createChatDialog!: TemplateRef<any>

  chosenChat!: LocalChat

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
    this.chatService.createLocalChat(this.saveChatRequest).subscribe(value => console.log(value))
  }

  onChatPicked(chat: LocalChat | PrivateChat) {
    this.chosenChat = chat as LocalChat
  }
}
