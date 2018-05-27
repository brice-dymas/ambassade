import {Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import * as xepOnline from 'css-to-pdf/js/xepOnline.jqPlugin.js';
import {Passeport, PasseportService} from '../passeport';

@Component({
    selector: 'jhi-print-detail-passeport',
    templateUrl: './print-detail-passeport.component.html',
    styleUrls: [
        'paper.css',
        'gutenberg.css',
        'modern.css'
        // 'modern.min.css'
    ]
})
export class PrintDetailPasseportComponent implements OnInit {

    passeport: Passeport;

    routeData: any;
    dataContent: any;
    property: any;
    dataHeader: any;

    constructor(private router: Router, private passeportService: PasseportService ) {
    }

    ngOnInit() {
        this.passeport = this.router.getNavigatedData();
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
