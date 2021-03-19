import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ChatsNearbyComponent } from './chats-nearby.component';
import {RouterModule, Routes} from "@angular/router";
import {NavigatorComponent} from "../navigator/navigator.component";
import {AuthGuard} from "../auth/auth.guard";
import {FindChatComponent} from "../navigator/find-chat/find-chat.component";
import {FindUsersComponent} from "../navigator/find-users/find-users.component";
import {UserChatsComponent} from "../navigator/user-chats/user-chats.component";
import { ChatMapComponent } from './chat-map/chat-map.component';
import {MaterialModule} from "../material/material.module";
import {FormsModule} from "@angular/forms";
import { ChosenChatComponent } from './chosen-chat/chosen-chat.component';
import {NbCardModule, NbChatModule} from "@nebular/theme";

const routes: Routes = [
  {
    path: 'chats-nearby', component: ChatsNearbyComponent, canActivate: [AuthGuard] }
]

@NgModule({
  declarations: [ChatsNearbyComponent, ChatMapComponent, ChosenChatComponent],
    imports: [
        CommonModule,
        RouterModule.forChild(routes),
        MaterialModule,
        FormsModule,
        NbChatModule,
        NbCardModule,
    ]
})
export class ChatsNearbyModule { }
