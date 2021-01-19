import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ChatComponent} from './chat.component';
import {RouterModule, Routes} from "@angular/router";
import {AuthGuard} from "../auth/auth.guard";
import { ChatUiComponent } from './chat-ui/chat-ui.component';
import {MaterialModule} from "../material/material.module";

const routes: Routes = [
  {path: 'chats', component: ChatComponent, canActivate: [AuthGuard]}
]

@NgModule({
  declarations: [ChatComponent, ChatUiComponent],
    imports: [
        CommonModule,
        RouterModule.forChild(routes),
        MaterialModule
    ]
})
export class ChatModule {
}
