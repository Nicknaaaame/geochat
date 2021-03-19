import {Component, Input, OnInit} from '@angular/core';

declare var ol: any
declare var ymaps: any

@Component({
  selector: 'app-map-ui',
  templateUrl: './map-ui.component.html',
  styles: []
})
export class MapUiComponent implements OnInit {
  private myMap: any

  constructor() {
  }

  @Input()
  geolocation!: Position
  @Input()
  latitude!: number
  @Input()
  longitude!: number

  ngOnInit() {
    if (!this.latitude)
      this.latitude = this.geolocation.coords.latitude
    if (!this.longitude)
      this.longitude = this.geolocation.coords.longitude

    ymaps.ready().then(() => {
      this.myMap = new ymaps.Map('myMap', {
        center: [this.latitude, this.longitude],
        zoom: 15,
      })
      let myPlacemark = new ymaps.Placemark(this.myMap.getCenter())
      this.myMap.geoObjects.add(myPlacemark)
    })
  }
}
