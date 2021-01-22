import {Component, Input, OnInit} from '@angular/core';

declare var ol: any;

@Component({
  selector: 'app-map-ui',
  templateUrl: './map-ui.component.html',
  styles: []
})
export class MapUiComponent implements OnInit {
  private map: any;

  constructor() {
  }

  @Input()
  geolocation!: Position
  @Input()
  latitude!: number
  @Input()
  longitude!: number

  ngOnInit() {
    if(!this.latitude)
      this.latitude = this.geolocation.coords.latitude
    if(!this.longitude)
      this.longitude = this.geolocation.coords.longitude
    this.map = new ol.Map({
      target: 'map',
      layers: [
        new ol.layer.Tile({
          source: new ol.source.OSM()
        })
      ],
      view: new ol.View({
        center: ol.proj.fromLonLat([this.longitude, this.latitude]),
        zoom: 14
      })
    });
    this.addPoint(this.latitude, this.longitude);
    // this.setCenter()
  }

  setCenter() {
    var view = this.map.getView();
    view.setCenter(ol.proj.fromLonLat([this.longitude, this.latitude]));
    view.addMarker(ol.proj.fromLonLat([this.longitude, this.latitude]));
    view.setZoom(12);
  }

  addPoint(lat: number, lng: number) {
    var vectorLayer = new ol.layer.Vector({
      source: new ol.source.Vector({
        features: [new ol.Feature({
          geometry: new ol.geom.Point(ol.proj.transform([lng, lat], 'EPSG:4326', 'EPSG:3857')),
        })]
      }),
      // style: new ol.style.Style({
      //   image: new ol.style.Icon({
      //     anchor: [0.5, 0.5],
      //     anchorXUnits: "fraction",
      //     anchorYUnits: "fraction",
      //     src: "assets/img/my-icon.png"
      //   })
      // })
    });
    this.map.addLayer(vectorLayer);
  }
}
