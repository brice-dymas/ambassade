import {Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import * as xepOnline from 'css-to-pdf/js/xepOnline.jqPlugin.js';
import {Passeport, PasseportService} from '../passeport';

@Component({
    selector: 'jhi-print-passeport',
    templateUrl: './print-passeport.component.html',
    styleUrls: [
        'paper.min.css'
    ]
})
export class PrintPasseportComponent implements OnInit {

    passeports: Passeport[];

    routeData: any;
    dataContent: any;
    property: any;
    dataHeader: any;

    constructor(private router: Router, private passeportService: PasseportService ) {
    }

    ngOnInit() {
        this.passeports = this.router.getNavigatedData();
        console.log('passeports = ', this.passeports);
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
