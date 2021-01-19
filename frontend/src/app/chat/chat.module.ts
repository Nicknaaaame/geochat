import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ChatComponent} from './chat.component';
import {RouterModule, Routes} from "@angular/router";
import {AuthGuard} from "../auth/auth.guard";
import {ChatUiComponent} from './chat-ui/chat-ui.component';
import {MaterialModule} from "../material/material.module";
import {FindChatUiComponent} from './find-chat-ui/find-chat-ui.component';

const routes: Routes = [
  {path: 'chats', component: ChatComponent, canActivate: [AuthGuard]}
]

@NgModule({
  declarations: [
    ChatComponent,
    ChatUiComponent,
    FindChatUiComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    MaterialModule
  ]
})
export class ChatModule {
}
