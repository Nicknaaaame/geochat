import {Component, OnInit, TemplateRef} from '@angular/core';
import {NotificationService} from "../store/notification-service/notification.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-toasts',
  template: `
    <ngb-toast
      *ngFor="let toast of toastService.toasts"
      [delay]="4000"
      [header]="toast.chat.name"
      [autohide]="autohide"
      (mouseenter)="autohide = false"
      (mouseleave)="autohide = true"
      (click)="onClickToast(toast.chat.id)"
    >
      <div style="min-width: 200px; max-width: 200px">
        <div *ngIf="autohide">
          <img mat-card-avatar src="{{toast.chat.picture}}" alt="pic">
          <p style="text-overflow: ellipsis; overflow: hidden;">{{toast.message.text}}</p>
        </div>
        <div *ngIf="!autohide">
          <button mat-button style="width: 100%" (click)="onClickToast(toast.chat.id)">open</button>
        </div>
      </div>

    </ngb-toast>
  `,
  host: {'[class.ngb-toasts]': 'true'}
})
export class ToastComponent {
  autohide = true

  constructor(public toastService: NotificationService, private router: Router) {
  }

  onClickToast(id: any) {
    this.router.navigate(['user-chats', {'chatId': id}])
  }
}
