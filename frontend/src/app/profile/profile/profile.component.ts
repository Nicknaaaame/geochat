import {Component, OnInit} from '@angular/core';
import {Store} from "@ngrx/store";
import {getProfile} from "../../store/profile-store/store/profile.selectors";
import {Profile} from "../../store/profile-store/service/profile.model";
import {updateProfile} from "../../store/profile-store/store/profile.actions";

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

  newImage: any

  constructor(private store: Store) {
    this.profile$.subscribe(value => {
      this.editProfile = JSON.parse(JSON.stringify(value))
    })
  }

  ngOnInit(): void {
  }

  onClickSubmit() {
    this.store.dispatch(updateProfile({
      profile: {
        name: this.editProfile.name,
        picture: this.newImage,
      }, popup: "Profile was updated success"
    }))
  }
}
