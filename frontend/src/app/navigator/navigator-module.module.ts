import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {NavigatorComponent} from './navigator.component';
import {RouterModule, Routes} from "@angular/router";
import {AuthGuard} from "../auth/auth.guard";
import {NavigatorUiComponent} from './navigator-ui/navigator-ui.component';
import {MaterialModule} from "../material/material.module";
import {FindChatUiComponent} from './find-chat-ui/find-chat-ui.component';
import {FormsModule} from "@angular/forms";
import { ChatListUiComponent } from './chat-list-ui/chat-list-ui.component';
import { ChatRowUiComponent } from './chat-list-ui/chat-row-ui/chat-row-ui.component';

const routes: Routes = [
  {path: 'chats', component: NavigatorComponent, canActivate: [AuthGuard]}
]

@NgModule({
  declarations: [
    NavigatorComponent,
    NavigatorUiComponent,
    FindChatUiComponent,
    ChatListUiComponent,
    ChatRowUiComponent,
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
