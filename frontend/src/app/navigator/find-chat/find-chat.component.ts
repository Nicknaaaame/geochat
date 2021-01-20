import {Component, Input, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {ChatService} from "../../store/chat-store/service/chat.service";
import {SaveChatRequest} from "../../store/chat-store/service/save-chat.request";
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

  saveChatRequest: SaveChatRequest = {} as SaveChatRequest

  chatsAround$ = this.chatService.getChatsAround()

  constructor(private dialog: MatDialog, private chatService: ChatService) {
    this.saveChatRequest.name = ''
    this.saveChatRequest.users = []
  }

  ngOnInit(): void {
  }

  onClickCreateChat() {
    this.dialog.open(this.createChatDialog).afterClosed().subscribe(value => {
      this.saveChat()
    })
  }

  saveChat() {
    console.log("saving")
    this.chatService.saveChat(this.saveChatRequest).subscribe(value => console.log(value))
  }
}
