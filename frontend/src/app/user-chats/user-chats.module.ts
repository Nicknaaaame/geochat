import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UserChatsComponent} from './user-chats.component';
import {RouterModule, Routes} from "@angular/router";
import {AuthGuard} from "../auth/auth.guard";
import { ChatListComponent } from './chat-list/chat-list.component';
import {ChatsNearbyModule} from "../chats-nearby/chats-nearby.module";
import { ChatListUnitComponent } from './chat-list/chat-list-unit/chat-list-unit.component';
import {MatCardModule} from "@angular/material/card";

const routes: Routes = [
  {
    path: 'user-chats', component: UserChatsComponent, canActivate: [AuthGuard],
  },
]

@NgModule({
  declarations: [UserChatsComponent, ChatListComponent, ChatListUnitComponent],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    ChatsNearbyModule,
    MatCardModule,
  ]
})
export class UserChatsModule {
}
