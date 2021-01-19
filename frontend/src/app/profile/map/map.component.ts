import {Component, OnInit, ViewChild} from '@angular/core';
import {GeolocationService} from "@ng-web-apis/geolocation";

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styles: []
})
export class MapComponent implements OnInit {

  constructor(public geolocation$: GeolocationService) {
  }

  ngOnInit(): void {
  }

}
