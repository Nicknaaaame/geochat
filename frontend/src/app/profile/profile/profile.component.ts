import {Component, OnInit} from '@angular/core';
import {Store} from "@ngrx/store";
import {getProfile} from "../../store/profile-store/store/profile.selectors";
import {Profile} from "../../store/profile-store/service/profile.model";
import {updateProfile} from "../../store/profile-store/store/profile.actions";
import {Message} from "../../store/message-store/service/message.model";
import {Blacklist} from "../../store/blacklist-store/service/blacklist.model";
import {BlacklistService} from "../../store/blacklist-store/service/blacklist.service";
import {User} from "../../store/user-store/service/user.model";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styles: [`
    .profile {
      display: flex;
      justify-content: center;
      align-items: center;
    }
  `]
})
export class ProfileComponent implements OnInit {

  profile$ = this.store.select(getProfile)

  editProfile: Profile = {} as Profile

  blacklist: User[] = []

  constructor(private store: Store, private blacklistService: BlacklistService) {
    this.profile$.subscribe(value => {
      this.editProfile = JSON.parse(JSON.stringify(value))
    })
    blacklistService.getBlackList().subscribe(value => {
      this.blacklist = value
    })
  }

  ngOnInit(): void {
  }

  onClickSubmit() {
    this.store.dispatch(updateProfile({profile: this.editProfile, popup: "Profile was updated success"}))
  }

  onClickUnblockUser(user: User) {
    this.blacklistService.unblockUser(user.id).subscribe(value => {
      this.blacklist.splice(this.blacklist.indexOf(user, 0), 1)
    })
  }
}
