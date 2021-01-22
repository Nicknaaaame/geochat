import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {NavigatorComponent} from './navigator.component';
import {RouterModule, Routes} from "@angular/router";
import {AuthGuard} from "../auth/auth.guard";
import {MaterialModule} from "../material/material.module";
import {FindChatComponent} from './find-chat/find-chat.component';
import {FormsModule} from "@angular/forms";
import { ChatListComponent } from './chat-list/chat-list.component';
import { ChatRowComponent } from './chat-list/chat-row/chat-row.component';
import { MessagesComponent } from './messages/messages.component';
import { FindUsersComponent } from './find-users/find-users.component';
import { UserListComponent } from './find-users/user-list/user-list.component';
import { UserRowComponent } from './find-users/user-list/user-row/user-row.component';
import { ChosenChatComponent } from './find-chat/chosen-chat/chosen-chat.component';
import { UserChatsComponent } from './user-chats/user-chats.component';

const routes: Routes = [
  {path: 'chats', component: NavigatorComponent, canActivate: [AuthGuard]}
]

@NgModule({
  declarations: [
    NavigatorComponent,
    FindChatComponent,
    ChatListComponent,
    ChatRowComponent,
    MessagesComponent,
    FindUsersComponent,
    UserListComponent,
    UserRowComponent,
    ChosenChatComponent,
    UserChatsComponent,
  ],
    imports: [
        CommonModule,
        RouterModule.forChild(routes),
        MaterialModule,
        FormsModule
    ]
})
export class ChatModule {
}
