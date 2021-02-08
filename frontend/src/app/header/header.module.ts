import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header.component';
import {MaterialModule} from "../material/material.module";
import {RouterModule} from "@angular/router";
import {NbLayoutModule} from "@nebular/theme";



@NgModule({
  declarations: [HeaderComponent],
  exports: [
    HeaderComponent
  ],
    imports: [
        CommonModule,
        MaterialModule,
        RouterModule,
        NbLayoutModule
    ]
})
export class HeaderModule { }
