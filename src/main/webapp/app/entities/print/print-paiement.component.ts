import {Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import * as xepOnline from 'css-to-pdf/js/xepOnline.jqPlugin.js';
import {Paiement, PaiementService} from '../paiement';

@Component({
    selector: 'jhi-print-paiement',
    templateUrl: './print-paiement.component.html',
    styleUrls: [
        'paper.min.css'
    ]
})
export class PrintPaiementComponent implements OnInit {

    paiements: Paiement[];

    routeData: any;
    dataContent: any;
    property: any;
    dataHeader: any;

    constructor(private router: Router, private paiementService: PaiementService ) {
    }

    ngOnInit() {
        this.paiements = this.router.getNavigatedData();
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
