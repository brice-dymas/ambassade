import { Component, OnInit, AfterViewChecked, ViewChild, ElementRef } from '@angular/core';
import {Router} from '@angular/router';
import { ExcelService } from '../../excel.services';
import { TableExport } from 'tableexport';
import * as jsPDF from 'jspdf';

@Component({
    selector: 'jhi-print',
    templateUrl: './print.component.html'
})
export class PrintComponent implements OnInit, AfterViewChecked {

    routeData: any;
    dataContent: any;
    property: any;
    // content: any;
    dataHeader: any;
    te: TableExport;
    exportData: any;

    @ViewChild('content') content: ElementRef;

    constructor(
        private router: Router,
        private excelService: ExcelService
    ) {
        this.excelService = excelService;
    }

    ngOnInit() {
        const datas = this.router.getNavigatedData();
        this.buildData(datas);
    }

    ngAfterViewChecked(): void {
        this.te = new TableExport(document.querySelector('#default-table'), {
            formats: ['xlsx'],
            exportButtons: false,
        });
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
        doc.save('download.pdf');
    }

    buildData(objs) {
        console.log('objs = ', objs);
        // const obj = objs[0];
        this.dataHeader = objs.dataHeader;
        this.dataContent = objs.dataContent;
        this.property = objs.property;

    }
}
