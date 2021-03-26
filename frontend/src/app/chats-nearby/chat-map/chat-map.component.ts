import {
  AfterViewInit,
  Component,
  EventEmitter,
  Input, NgZone,
  OnChanges,
  OnInit,
  Output, SimpleChanges,
  TemplateRef,
  ViewChild
} from '@angular/core';
import {Chat} from "../../store/chat-store/service/chat.model";
import {ChatService} from "../../store/chat-store/service/chat.service";
import {ChatRequest} from "../../store/chat-store/service/chat.request";
import {MatDialog} from "@angular/material/dialog";
import * as $ from "jquery";
import {Route, Router} from "@angular/router";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";

declare var ymaps: any

@Component({
  selector: 'app-chat-map',
  templateUrl: './chat-map.component.html',
  styles: []
})
export class ChatMapComponent implements OnInit, AfterViewInit {
  @ViewChild('createChatDialog')
  createChatDialog!: TemplateRef<any>
  @Input()
  latitude!: number
  @Input()
  longitude!: number
  @Input()
  chats!: Chat[]
  saveChatRequest = {} as ChatRequest
  private myMap: any
  clusterer: any

  constructor(private dialog: MatDialog, private chatService: ChatService, private router: Router,
              private zone: NgZone) {
  }

  ngAfterViewInit(): void {
  }

  ngOnInit(): void {
    ymaps.ready().done(() => this.initMap())
  }

  initMap() {
    let delta = 20 / 111
    let lat1 = this.latitude - delta, lat2 = this.latitude + delta,
      lon1 = this.longitude - delta, lon2 = this.longitude + delta
    this.myMap = new ymaps.Map('myMap', {
      // center: [this.geolocation.coords.latitude, this.geolocation.coords.longitude],
      center: [this.latitude, this.longitude],
      zoom: 15,
      controls: [],
    }, {
      restrictMapArea: [
        [lat1, lon1],
        [lat2, lon2]
      ]
    })
    this.clusterer = new ymaps.Clusterer()
    let myGeoObjects: any = []
    // myGeoObjects.push(new ymaps.Placemark(this.myMap.getCenter()))

    this.chats.forEach(chat => myGeoObjects.push(this.createPlacemark(chat)))
    this.clusterer.add(myGeoObjects);
    this.myMap.geoObjects.add(this.clusterer);
    let button = new ymaps.control.Button("Add chat")
    button.events.add('click', () => this.zone.run(() => this.onClickAddChat()))
    this.myMap.controls.add(button, {float: "right"})

  }

  onClickOpenChat(chat: Chat) {
    // this.router.navigate(['chats-nearby/' + chat.id])
    this.router.navigate(['chats-nearby', {'chatId': chat.id}])
  }

  onClickAddChat() {
    this.dialog.open(this.createChatDialog).afterClosed().subscribe(value => {
      if (value == 'save') {
        this.chatService.createChat(this.saveChatRequest).subscribe(chat => {
          this.clusterer.add(this.createPlacemark(chat))
          this.chats.push(chat)
        })
      }
    })
  }

  onEmitImage(image: File) {
    this.saveChatRequest.picture = image
  }

  createPlacemark(chat: Chat) {
    let iconLayout = ymaps.templateLayoutFactory.createClass(`
        <img src="{{properties.pic}}" alt="pic"
        style="position: absolute; left: -16px; top: -16px; width: 32px; height: 32px; border-radius: 16px">
        `)
    let placemark = new ymaps.Placemark(
      [chat.location.latitude, chat.location.longitude],
      {
        name: chat.name,
        desc: chat.description,
        pic: chat.picture,
      },
      {
        // balloonContentLayout: balloonContentLayout,
        balloonPanelMaxMapArea: 0,
        iconLayout: iconLayout,
        iconShape: {
          type: 'Circle',
          coordinates: [0, 0],
          radius: 16
        }
      }
    );
    placemark.events.add('click', () => {
      this.zone.run(() => {
        this.onClickOpenChat(chat)
      })
    })
    return placemark
  }
}

/*let balloonContentLayout = ymaps.templateLayoutFactory.createClass(
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
      $('#open-btn').on('click', () => {
        openChat(chat)
      });
    },
    clear: function () {
      // $('#open-btn').off('click', this.onOpenChatBtnClick());
      balloonContentLayout.superclass.clear.call(this);
    },
  }
)*/
