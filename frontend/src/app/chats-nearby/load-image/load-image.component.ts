import {Component, EventEmitter, OnInit, Output} from '@angular/core'
import {environment} from "../../../environments/environment";

@Component({
  selector: 'app-load-image',
  templateUrl: './load-image.component.html',
  styles: []
})
export class LoadImageComponent implements OnInit {
  @Output()
  imageEmit = new EventEmitter<File>()
  public selectedFile!: File
  url = '';

  constructor() {
  }

  ngOnInit() {
  }

  onSelectFile(event: any) {
    if (event.target.files && event.target.files[0]) {
      this.selectedFile = event.target.files[event.target.files.length - 1] as File
      this.imageEmit.emit(this.selectedFile)
      var reader = new FileReader();

      reader.readAsDataURL(event.target.files[0]); // read file as data url

      reader.onload = (event) => { // called once readAsDataURL is completed
        // @ts-ignore
        this.url = event.target.result;
      }
    }
  }
}
