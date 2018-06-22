import {Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import * as xepOnline from 'css-to-pdf/js/xepOnline.jqPlugin.js';
import { Paiement } from '../paiement';

@Component({
    selector: 'jhi-print-recu-paiement',
    templateUrl: './print-recu-paiement.component.html',
    styleUrls: [
        'paper.min.css'
    ]
})
export class PrintRecuPaiementComponent implements OnInit {

    paiement: Paiement[];

    constructor(private router: Router) {
    }

    ngOnInit() {
        this.paiement = this.router.getNavigatedData();
    }

    downloadPDF() {
        return xepOnline.Formatter.Format('content', {pageWidth: '297mm', pageHeight: '210mm'});
    }
    previousState() {
        window.history.back();
    }

}
