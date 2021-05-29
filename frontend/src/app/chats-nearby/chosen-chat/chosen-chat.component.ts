import {Component, Input, NgZone, OnChanges, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {Message} from "../../store/message-store/service/message.model";
import {Store} from "@ngrx/store";
import {MessageService} from "../../store/message-store/service/message.service";
import {getProfile} from "../../store/profile-store/store/profile.selectors";
import {Chat} from "../../store/chat-store/service/chat.model";
import {Observable} from "rxjs";
import {ChatService} from "../../store/chat-store/service/chat.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatDialog} from "@angular/material/dialog";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {User} from "../../store/user-store/service/user.model";
import {BlacklistService} from "../../store/blacklist-store/service/blacklist.service";
import {ChatRequest} from "../../store/chat-store/service/chat.request";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NotificationService} from "../../store/notification-service/notification.service";

@Component({
  selector: 'app-chosen-chat',
  templateUrl: './chosen-chat.component.html',
  styles: []
})
export class ChosenChatComponent implements OnInit {
  @Input()
  chat!: Chat
  messages: NbMessage[] = []
  userId!: number | string
  canWrite = false
  info = {
    users: [] as User[],
    blackList: [] as User[],
    isAdmin: false
  }
  form!: FormGroup

  constructor(private store: Store, private messageService: MessageService, private blacklistService: BlacklistService,
              private chatService: ChatService, private matSnackBar: MatSnackBar, private modalService: NgbModal,
              private fb: FormBuilder, public notificationService: NotificationService) {
    store.select(getProfile).subscribe(value => {
      this.userId = value.id
    })

  }

  ngOnInit(): void {
    if (this.userId == this.chat.admin.id || this.chat.users.find(value => value.id == this.userId))
      this.canWrite = true
    this.messageService.getMessages(this.chat.id).subscribe(value => {
      value.forEach(message => this.messages.push(this.convertMessage(message)))
    })
    this.messageService.onMessage(this.chat.id).subscribe(message => this.messages.push(this.convertMessage(message)))
    this.form = this.fb.group({
      name: [this.chat.name, [Validators.required, Validators.minLength(3), Validators.maxLength(52)]],
      description: [this.chat.description, [Validators.maxLength(2048)]],
      picture: [null]
    })
    console.log(this.chat)
  }

  updateChat() {
    this.chatService.getChat(this.chat.id).subscribe(value => {
      this.chat = value
      this.updateInfo()
    })
  }

  updateInfo() {
    this.blacklistService.getBlackList(this.chat.id).subscribe(value => this.info.blackList = value)
    this.info.users = this.chat.users.filter(value => value.id != this.chat.admin.id)

    this.info.isAdmin = this.userId == this.chat.admin.id
  }

  onSendMessage($event: { message: string; files: File[] }) {
    this.messageService.saveMessage({text: $event.message, chatId: this.chat.id}).subscribe()
  }

  onClickJoinChat() {
    this.chatService.joinChat(this.chat.id).subscribe(response => {
      this.canWrite = true
      this.updateChat()
    }, (err => {
      this.canWrite = false
    }))
  }

  onClickInfoDialog(dialog: any) {
    this.updateChat()
    this.modalService.open(dialog, {centered: true, size: 'lg'})
  }

  onClickEditChatDialog(dialog: TemplateRef<any>) {
    this.modalService.open(dialog, {centered: true, size: 'lg'}).closed.subscribe(value => {
      if (value == 'YES') {
        this.chatService.updateChat(this.chat.id, {
          picture: this.form.controls['picture'].value,
          name: this.form.controls['name'].value,
          description: this.form.controls['description'].value,
        }).subscribe()
        window.location.reload()
      }
    })
  }

  onClickDeleteChatDialog(dialog: TemplateRef<any>) {
    this.modalService.open(dialog, {centered: true, size: 'sm'}).closed.subscribe(value => {
      if (value == 'YES') {
        this.chatService.deleteChat(this.chat.id).subscribe()
        window.location.reload()
      }
    })
  }

  onClickLeaveChatDialog(dialog: TemplateRef<any>) {
    this.modalService.open(dialog, {centered: true, size: 'sm'}).closed.subscribe(value => {
      if (value == 'YES') {
        this.chatService.leaveChat(this.chat.id).subscribe()
        window.location.reload()
      }
    })
  }

  onClickBlockUser(user: User) {
    this.blacklistService.blockUser(user.id, this.chat.id).subscribe(value => {
      this.updateChat()
    })
  }

  onClickUnblockUser(user: User) {
    this.blacklistService.unblockUser(user.id, this.chat.id).subscribe(value => {
      this.updateChat()
    })
  }

  convertMessage(message: Message): NbMessage {
    let text = '', quote = '', type = ''
    switch (message.messageType) {
      case 'TEXT':
        type = 'text'
        text = message.text
        break
      case 'JOINED':
        type = 'quote'
        quote = 'Joined'
        break
      case 'LEFT':
        type = 'quote'
        quote = 'Left'
        break
    }
    return {
      type: type,
      message: text,
      reply: this.userId == message.sender.id,
      sender: message.sender.name,
      date: message.creationDate,
      files: [],
      quote: quote,
      avatar: message.sender.picture
    }
  }

  onClickNotificationBtn(value: boolean) {
    if (value)
      this.notificationService.enableNotification(this.chat)
    else
      this.notificationService.disableNotification(this.chat)
    this.chat.notification = value
  }
}

interface NbMessage {
  type: string,
  message: string,
  reply: boolean,
  sender: string,
  date: Date,
  files: any,
  quote: string,
  avatar: string
  // latitude: number,
  // longitude: number,
}
