import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {NavigatorComponent} from './navigator.component';
import {RouterModule, Routes} from "@angular/router";
import {AuthGuard} from "../auth/auth.guard";
import {MaterialModule} from "../material/material.module";
import {FindChatComponent} from './find-chat/find-chat.component';
import {FormsModule} from "@angular/forms";
import {ChatListComponent} from './chat-list/chat-list.component';
import {ChatRowComponent} from './chat-list/chat-row/chat-row.component';
import {FindUsersComponent} from './find-users/find-users.component';
import {UserListComponent} from './find-users/user-list/user-list.component';
import {UserRowComponent} from './find-users/user-list/user-row/user-row.component';
import {FindChatChosenComponent} from './find-chat/find-chat-chosen/find-chat-chosen.component';
import {UserChatsComponent} from './user-chats/user-chats.component';
import {NbChatModule, NbLayoutModule, NbTabsetModule, NbUserModule} from "@nebular/theme";
import {UserChatChosenComponent} from './user-chats/user-chat-chosen/user-chat-chosen.component';
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";

const routes: Routes = [
  {
    path: 'chats', component: NavigatorComponent, canActivate: [AuthGuard], children: [
      {
        path: 'find-chats', component: FindChatComponent
      },
      {
        path: 'find-users', component: FindUsersComponent
      },
      {
        path: 'user-chats', component: UserChatsComponent
      }
    ]
  }
]

@NgModule({
  declarations: [
    NavigatorComponent,
    FindChatComponent,
    ChatListComponent,
    ChatRowComponent,
    FindUsersComponent,
    UserListComponent,
    UserRowComponent,
    FindChatChosenComponent,
    UserChatsComponent,
    UserChatChosenComponent,
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    MaterialModule,
    FormsModule,
    NbLayoutModule,
    NbTabsetModule,
    NbChatModule,
    NbUserModule,
    NgbModule
  ]
})
export class ChatModule {
}
