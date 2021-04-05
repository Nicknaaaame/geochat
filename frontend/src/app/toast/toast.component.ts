import {Component, OnInit, TemplateRef} from '@angular/core';
import {NotificationService} from "../store/notification-service/notification.service";

@Component({
  selector: 'app-toasts',
  template: `
    <ngb-toast
      *ngFor="let toast of toastService.toasts"
      [delay]="4000"
      [header]="toast.chat.name"
      (hidden)="toastService.removeToast(toast)"
    >
      <img mat-card-avatar src="{{toast.chat.picture}}" alt="pic">
      <span>{{toast.message.text}}</span>
    </ngb-toast>
  `,
  host: {'[class.ngb-toasts]': 'true'}
})
export class ToastComponent {
  constructor(public toastService: NotificationService) {
  }
}
