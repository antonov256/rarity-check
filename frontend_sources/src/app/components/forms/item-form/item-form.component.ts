import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-item-form',
  templateUrl: './item-form.component.html',
  styleUrls: ['./item-form.component.scss']
})
export class ItemFormComponent implements OnInit {

  @Input() isEdit: boolean = false;

  form: FormGroup = new FormGroup({
    title: new FormControl('', [Validators.required]),
    description: new FormControl('', [Validators.required]),
    category: new FormControl('', []),
    images: new FormControl([])
  });

  imageURLs: string[] = [];
  favoriteImageIndex: number = 0;

  constructor() { }

  ngOnInit(): void {
  }

  selectImage(event: any){
    const files = Array.from((event.target as HTMLInputElement).files);

    const updatedImagesValue = [...this.form.get('images').value, ...files];

    this.form.patchValue({
      images: updatedImagesValue
    });
    this.form.get('images').updateValueAndValidity();

    const reader = new FileReader();
    reader.onload = () => {
      this.imageURLs.push(reader.result as string);
    }

    files.forEach(file => {
      reader.readAsDataURL(file);
    });
  }

  changeFavIndex(event: number){
    this.favoriteImageIndex = event;
  }

  removeImage(event: number){
    if(this.favoriteImageIndex == event){
      this.favoriteImageIndex = 0;
    }

    const filteredImagesArray = Array.from(this.form.get('images').value).filter((e, index) => index != event);

    this.form.patchValue({
      images: filteredImagesArray
    });

    this.imageURLs = this.imageURLs.filter((e, index) => index != event);
  }
}
