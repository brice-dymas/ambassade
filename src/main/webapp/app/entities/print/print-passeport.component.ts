import {Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import * as xepOnline from 'css-to-pdf/js/xepOnline.jqPlugin.js';
import {Passeport} from '../passeport';

@Component({
    selector: 'jhi-print-passeport',
    templateUrl: './print-passeport.component.html',
    styleUrls: [
        'paper.min.css'
    ]
})
export class PrintPasseportComponent implements OnInit {

    passeports: Passeport[];

    constructor(private router: Router) {
    }

    ngOnInit() {
        this.passeports = this.router.getNavigatedData();
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
