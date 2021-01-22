import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfileComponent } from './profile/profile.component';
import {RouterModule} from "@angular/router";
import {AuthRoutes} from "../auth/auth.routes";
import {ProfileRoutes} from "./profile.routes";
import {MaterialModule} from "../material/material.module";
import {FormsModule} from "@angular/forms";
import { MapComponent } from './map/map.component';
import { MapUiComponent } from './map/map-ui/map-ui.component';



@NgModule({
    declarations: [ProfileComponent, MapComponent, MapUiComponent],
  exports: [
    MapComponent,
    MapUiComponent
  ],
    imports: [
        CommonModule,
        MaterialModule,
        FormsModule,
        RouterModule.forChild(ProfileRoutes),
    ]
})
export class ProfileModule { }
