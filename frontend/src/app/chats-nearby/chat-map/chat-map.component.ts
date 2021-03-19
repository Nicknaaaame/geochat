import {Component, EventEmitter, Input, OnInit, Output, TemplateRef, ViewChild} from '@angular/core';
import {Chat} from "../../store/chat-store/service/chat.model";
import {ChatService} from "../../store/chat-store/service/chat.service";
import {SaveChatRequest} from "../../store/chat-store/service/save-chat.request";
import {MatDialog} from "@angular/material/dialog";
import * as $ from "jquery";

declare var ymaps: any

@Component({
  selector: 'app-chat-map',
  templateUrl: './chat-map.component.html',
  styles: []
})
export class ChatMapComponent implements OnInit {
  @ViewChild('createChatDialog')
  createChatDialog!: TemplateRef<any>
  @Input()
  geolocation!: Position
  @Input()
  chats!: Chat[]
  @Output()
  onChatPicked = new EventEmitter<Chat>()
  saveChatRequest = {} as SaveChatRequest
  private myMap: any

  constructor(private dialog: MatDialog, private chatService: ChatService) {
  }

  ngOnInit(): void {
    ymaps.ready().then(() => {
      this.myMap = new ymaps.Map('myMap', {
        center: [this.geolocation.coords.latitude, this.geolocation.coords.longitude],
        zoom: 15,
        controls: []
      })

      let createChatButton = new ymaps.control.Button("Add chat");

      createChatButton.events.add('click', () => this.onClickAddChat())

      this.myMap.controls.add(createChatButton, {float: 'right'})

      const openChat = (chat: Chat) => {
        this.openChat(chat)
      }

      this.chats.forEach(chat => {
        let balloonContentLayout = ymaps.templateLayoutFactory.createClass(
          `
            <div>
              <h3>{{properties.name}}</h3>
              <h4>{{properties.desc}}</h4>
              <button id="open-btn">Open</button>
            </div>
          `,
          {
            build: function () {
              balloonContentLayout.superclass.build.call(this);
              $('#open-btn').on('click', this.onOpenBtnClick);
            },
            clear: function () {
              $('#open-btn').off('click', this.onOpenBtnClick);
              balloonContentLayout.superclass.clear.call(this);
            },
            onOpenBtnClick: function () {
              openChat(chat)
            },
          }
        )

        let iconLayout = ymaps.templateLayoutFactory.createClass(
            `
            <img src="{{properties.pic}}" alt="pic"
            style="position: absolute; left: -16px; top: -16px; width: 32px; height: 32px; border-radius: 16px">
          `)

        this.myMap.geoObjects.add(new ymaps.Placemark(
          [chat.location.latitude, chat.location.longitude],
          {
            name: chat.name,
            desc: chat.description,
            pic: chat.picture,
          },
          {
            balloonContentLayout: balloonContentLayout,
            balloonPanelMaxMapArea: 0,
            iconLayout: iconLayout,
            iconShape: {
              type: 'Circle',
              coordinates: [0, 0],
              radius: 16
            }
          }
        ))
      })
    })
  }

  openChat(chat: Chat) {
    this.onChatPicked.emit(chat)
  }

  onClickAddChat() {
    this.dialog.open(this.createChatDialog).afterClosed().subscribe(value => {
      if (value == 'save') {
        this.chatService.createChat(this.saveChatRequest).subscribe(chat => console.log(chat))
      }
    })
  }
}
