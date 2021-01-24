import {Component, OnInit} from '@angular/core';
import {Store} from "@ngrx/store";
import {getProfile} from "../../store/profile-store/store/profile.selectors";
import {Profile} from "../../store/profile-store/service/profile.model";
import {updateProfile} from "../../store/profile-store/store/profile.actions";
import {Message} from "../../store/message-store/service/message.model";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styles: [
      `
      .profile {
        display: flex;
        justify-content: center;
        align-items: center;
      }
    `
  ]
})
export class ProfileComponent implements OnInit {

  profile$ = this.store.select(getProfile)

  editProfile: Profile = {} as Profile

  arr: Array<Message | string>

  constructor(private store: Store) {
    this.profile$.subscribe(value => {
      this.editProfile = JSON.parse(JSON.stringify(value))
    })
    this.arr = new Array<Message | string>()
    this.arr.push("123")
    let message: Message = {
      id: 123, text: "teeext"
    }
    this.arr.push(message)
    console.log(this.arr)
  }

  ngOnInit(): void {
  }

  onClickSubmit() {
    this.store.dispatch(updateProfile({profile: this.editProfile}))
  }
}
