import { Component, OnInit, Output, EventEmitter, Input } from "@angular/core";

@Component({
  selector: "app-image-box",
  templateUrl: "./image-box.component.html",
  styleUrls: ["./image-box.component.scss"]
})
export class ImageBoxComponent implements OnInit {

  @Output() toggle: any = new EventEmitter();
  @Output() remove: any = new EventEmitter();
  @Input() favorite = false;
  @Input() image = "https://material.angular.io/assets/img/examples/shiba2.jpg";
  @Input() id: number;

  constructor() {}

  ngOnInit(): void {}

  emitToggle() {
    this.toggle.emit(this.id);
  }

  emitRemove() {
    this.remove.emit(this.id);
  }
}
