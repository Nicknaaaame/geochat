import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfileComponent } from './profile/profile.component';
import {RouterModule} from "@angular/router";
import {AuthRoutes} from "../auth/auth.routes";
import {ProfileRoutes} from "./profile.routes";
import {MaterialModule} from "../material/material.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { MapComponent } from './map/map.component';
import { MapUiComponent } from './map/map-ui/map-ui.component';
import {ChatsNearbyModule} from "../chats-nearby/chats-nearby.module";



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
        ChatsNearbyModule,
        ReactiveFormsModule,
    ]
})
export class ProfileModule { }
