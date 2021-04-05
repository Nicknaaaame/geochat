import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ToastComponent} from "./toast.component";
import {NgbToastModule} from "@ng-bootstrap/ng-bootstrap";
import {MatCardModule} from "@angular/material/card";
import {MatButtonModule} from "@angular/material/button";

@NgModule({
  declarations: [
    ToastComponent
  ],
  exports: [
    ToastComponent
  ],
    imports: [
        CommonModule,
        NgbToastModule,
        MatCardModule,
        MatButtonModule,
    ]
})
export class ToastModule {
}
