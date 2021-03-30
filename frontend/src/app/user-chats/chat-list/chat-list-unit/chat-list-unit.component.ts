import {Component, Input, NgZone, OnChanges, OnInit} from '@angular/core';
import {Chat} from "../../../store/chat-store/service/chat.model";
import {Router} from "@angular/router";

declare var ymaps: any

@Component({
  selector: 'app-chat-list-unit',
  templateUrl: './chat-list-unit.component.html',
  styles: []
})
export class ChatListUnitComponent implements OnInit, OnChanges {
  @Input()
  chat!: Chat
  myMap: any
  static mapCount = 0
  currMapCount = ++ChatListUnitComponent.mapCount

  constructor(private router: Router, private ngZone: NgZone) {
  }

  ngOnInit(): void {
  }

  ngOnChanges(): void {
    console.log(this.currMapCount)
    ymaps.ready().done(() => this.initMap())
  }

  initMap() {
    let delta = 2 / 111
    let lat1 = this.chat.location.latitude - delta, lat2 = this.chat.location.latitude + delta,
      lon1 = this.chat.location.longitude - delta, lon2 = this.chat.location.longitude + delta

    this.myMap = new ymaps.Map(`myMap${this.currMapCount}`, {
      center: [this.chat.location.latitude, this.chat.location.longitude],
      zoom: 15,
      controls: [],
    }, {
      restrictMapArea: [
        [lat1, lon1],
        [lat2, lon2]
      ]
    })
    this.myMap.geoObjects.add(this.createPlacemark(this.chat))
  }

  onClickOpenChat(chat: Chat) {
    this.router.navigate(['user-chats', {'chatId': chat.id}])
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
      this.ngZone.run(() => {
        this.onClickOpenChat(chat)
      })
    })
    return placemark
  }
}
