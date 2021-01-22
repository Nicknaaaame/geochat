import {Component, Input, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {User} from "../../../../store/user-store/service/user.model";
import {MatDialog} from "@angular/material/dialog";
import {Message} from "../../../../store/message-store/service/message.model";

@Component({
  selector: 'app-user-row',
  templateUrl: './user-row.component.html',
  styles: [
  ]
})
export class UserRowComponent implements OnInit {
  @Input()
  user!: User
  @ViewChild('writeMessageDialog')
  writeMessageDialog!: TemplateRef<any>
  message = {} as Message

  constructor(private matDialog: MatDialog) { }

  ngOnInit(): void {
  }

  onClickWriteMessage(){
    this.matDialog.open(this.writeMessageDialog).afterClosed().subscribe(value => {
      if (value=='send')
        this.sendMessage()
    })
  }

  sendMessage(){
    console.log("sending")
  }
}
