import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-find-chat-ui',
  templateUrl: './find-chat-ui.component.html',
  styles: []
})
export class FindChatUiComponent implements OnInit {
  @ViewChild('createChatDialog')
  createChatDialog!: TemplateRef<any>;

  constructor(private dialog: MatDialog) {
  }

  ngOnInit(): void {
  }

  onClickCreateChat() {
    this.dialog.open(this.createChatDialog)
  }

  onClickSaveChat() {

  }
}
