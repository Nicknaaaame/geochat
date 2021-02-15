import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../store/user-store/service/user.model";
import {BlacklistService} from "../../store/blacklist-store/service/blacklist.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-user-page-ui',
  templateUrl: './user-page-ui.component.html',
  styles: []
})
export class UserPageUiComponent implements OnInit {
  @Input()
  user!: User
  isInBlackList!: boolean
  isBlocked$!: Observable<boolean>

  constructor(private blacklistService: BlacklistService) {
  }

  ngOnInit(): void {
    this.blacklistService.isUserInBlackList(this.user.id).subscribe(value => this.isInBlackList = value)
    this.isBlocked$ = this.blacklistService.isUserBlocked(this.user.id)
  }

  onClickBlockUser() {
    this.blacklistService.blockUser(this.user.id).subscribe()
    this.isInBlackList = true
  }

  onClickUnblockUser() {
    this.blacklistService.unblockUser(this.user.id).subscribe()
    this.isInBlackList = false
  }
}
