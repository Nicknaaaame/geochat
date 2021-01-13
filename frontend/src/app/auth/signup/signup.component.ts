import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styles: [
      `
      :host {
        display: flex;
        justify-content: center;
        margin: 100px 0;
      }

      button {
        display: flex;
        justify-content: center;
      }
    `
  ]
})
export class SignupComponent implements OnInit {

  constructor() {
  }

  ngOnInit(): void {
  }

}
