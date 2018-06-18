import {Directive, Input, Output, EventEmitter, ElementRef, OnChanges } from '@angular/core';
import { Chart } from 'frappe-charts';

declare var Chart: any; // just to avoid error marked by editor. Optional declaration

@Directive({
  selector: '[jhiFrappe]'
})
export class FrappeDirective implements OnChanges {

    @Input() title: string;
    @Input() data: any;
    @Input() type: string;
    @Input() height: number;

    @Output() frappe: EventEmitter<any> = new EventEmitter();

    constructor(
      private el: ElementRef
    ) {
      this.type = this.type || 'bar';
      this.height = this.height || 250;
    }

    ngOnChanges() {
      const chart = new Chart({
        parent: this.el.nativeElement,
        title: this.title,
        region_fill: 1,
        data: this.data,
        type: this.type, // or 'line', 'scatter', 'pie', 'percentage'
        height: this.height
      });
      this.frappe.emit(chart);
    }

}
