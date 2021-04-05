import {Injectable, TemplateRef} from '@angular/core';
import {Chat} from "../chat-store/service/chat.model";
import {Subscription} from "rxjs";
import {Message} from "../message-store/service/message.model";
import {MessageService} from "../message-store/service/message.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ChatService} from "../chat-store/service/chat.service";

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  subs: Map<number | string, Subscription>
  toasts: any[] = []

  constructor(private messageService: MessageService, private route: ActivatedRoute, private router: Router,
              private chatService: ChatService) {
    this.subs = new Map<number | string, Subscription>()
  }

  initSubs() {
    this.chatService.getChatsNearby().subscribe(chats => chats.forEach(chat => {
      if (chat.notification)
        this.addChat(chat)
    }))
  }

  enableNotification(chat: Chat) {
    this.addChat(chat)
    this.chatService.enableNotification(chat.id).subscribe()
  }

  disableNotification(chat: Chat) {
    this.removeChat(chat)
    this.chatService.disableNotification(chat.id).subscribe()
  }

  private addChat(chat: Chat) {
    this.subs.set(chat.id, this.messageService.onMessage(chat.id).subscribe(message => this.handleMessage(message, chat)))
  }

  private removeChat(chat: Chat) {
    this.subs.get(chat.id)?.unsubscribe()
    this.subs.delete(chat.id)
    console.log(this.subs)
  }


  showToast(message: Message, chat: Chat) {
    this.toasts.push({message, chat})
  }

  removeToast(toast: any) {
    this.toasts = this.toasts.filter(t => t !== toast)
  }

  private handleMessage(message: Message, chat: Chat) {
    if (this.router.url == `/chats-nearby;chatId=${chat.id}` || this.router.url == `/user-chats;chatId=${chat.id}`) {
      return
    }
    this.showToast(message, chat)
  }
}
