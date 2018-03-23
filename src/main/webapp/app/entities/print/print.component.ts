import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { ExcelService } from '../../excel.services';
import { TableExport } from 'tableexport';
import * as jsPDF from 'jspdf';

@Component({
    selector: 'jhi-print',
    templateUrl: './print.component.html'
})
export class PrintComponent implements OnInit {

    routeData: any;
    dataContent: any;
    content: any;
    dataHeader: any;
    te: TableExport;
    exportData: any;

    constructor(
        private router: Router,
        private excelService: ExcelService
    ) {
        this.excelService = excelService;
    }

    ngOnInit() {
        const datas = this.router.getNavigatedData();
        this.buildData(datas);
        datas.forEach(function(data) {
            console.log('data  = ', data);
        });
        console.log('dataContent = ', this.dataContent);
        console.log('dataHeader = ', this.dataHeader);
    }

    trackId(index: number, item: any) {
        return item.id;
    }

    exportToExcel(event) {
        this.exportData = this.te.getExportData()['default-table']['xlsx'];
        this.te.export2file(this.exportData.data, this.exportData.mimeType, this.exportData.filename, this.exportData.fileExtension);
    }

    downloadPDF() {
        const doc = new jsPDF();
        const specialElementHandlers = {
            '#editor': function(element, renderer) {
                return true;
            }
        };
        const content = this.content.nativeElement;

        doc.fromHTML(content.innerHTML, 15, 15, {
            'width': 190,
        });
        doc.save('monTest2.pdf');
    }

    buildData(objs) {
        const obj = objs[0];
        this.dataHeader = Object.getOwnPropertyNames (obj);
        this.dataContent = objs;

    }
}
