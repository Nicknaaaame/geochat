import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ChatsNearbyComponent} from './chats-nearby.component';
import {RouterModule, Routes} from "@angular/router";
import {AuthGuard} from "../auth/auth.guard";
import {ChatMapComponent} from './chat-map/chat-map.component';
import {MaterialModule} from "../material/material.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ChosenChatComponent} from './chosen-chat/chosen-chat.component';
import {NbCardModule, NbChatModule} from "@nebular/theme";
import {NgbNavModule} from "@ng-bootstrap/ng-bootstrap";
import { LoadImageComponent } from './load-image/load-image.component';

const routes: Routes = [
  {
    path: 'chats-nearby',  component: ChatsNearbyComponent, canActivate: [AuthGuard],
  },
]

@NgModule({
    declarations: [ChatsNearbyComponent, ChatMapComponent, ChosenChatComponent, LoadImageComponent],
  exports: [
    LoadImageComponent,
    ChosenChatComponent
  ],
    imports: [
        CommonModule,
        RouterModule.forChild(routes),
        MaterialModule,
        FormsModule,
        NbChatModule,
        NbCardModule,
        NgbNavModule,
        ReactiveFormsModule,
    ]
})
export class ChatsNearbyModule {
}
