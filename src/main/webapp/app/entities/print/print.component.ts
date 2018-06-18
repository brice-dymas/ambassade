import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import * as xepOnline from 'css-to-pdf/js/xepOnline.jqPlugin.js';
import {Visa} from '../visa/visa.model';

@Component({
    selector: 'jhi-print',
    templateUrl: './print.component.html',
    styleUrls: [
        'paper.min.css'
    ]
})
export class PrintComponent implements OnInit {

    visas: Visa[];

    constructor( private router: Router ) {
    }

    ngOnInit() {
        this.visas = this.router.getNavigatedData();
    }

    trackId(index: number, item: any) {
        return item.id;
    }

    downloadPDF() {
        return xepOnline.Formatter.Format('content', {pageWidth: '297mm', pageHeight: '210mm'});
    }
    previousState() {
        window.history.back();
    }
}
