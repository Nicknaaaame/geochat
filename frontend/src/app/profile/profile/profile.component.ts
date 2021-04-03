import {Component, OnInit} from '@angular/core';
import {Store} from "@ngrx/store";
import {getProfile} from "../../store/profile-store/store/profile.selectors";
import {Profile} from "../../store/profile-store/service/profile.model";
import {updateProfile} from "../../store/profile-store/store/profile.actions";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styles: []
})
export class ProfileComponent implements OnInit {

  profile$ = this.store.select(getProfile)

  form!: FormGroup
  profile!: Profile

  constructor(private store: Store, private fb: FormBuilder) {
    this.profile$.subscribe(value => {
      this.profile = JSON.parse(JSON.stringify(value))
      this.form = this.fb.group({
        name: [this.profile.name, [Validators.required, Validators.minLength(3), Validators.maxLength(52)]],
        picture: [null]
      })
    })
  }

  ngOnInit(): void {
  }

  onClickSubmit() {
    this.store.dispatch(updateProfile({
      profile: {
        name: this.form.controls['name'].value,
        picture: this.form.controls['picture'].value,
      }, popup: "Profile was updated success"
    }))
  }
}
