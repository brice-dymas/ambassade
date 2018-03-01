import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { Visa } from './visa.model';
import { VisaService } from './visa.service';

@Injectable()
export class VisaPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private visaService: VisaService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.visaService.find(id)
                    .subscribe((visaResponse: HttpResponse<Visa>) => {
                        const visa: Visa = visaResponse.body;
                        visa.dateEmission = this.datePipe
                            .transform(visa.dateEmission, 'yyyy-MM-ddTHH:mm:ss');
                        visa.dateExpiration = this.datePipe
                            .transform(visa.dateExpiration, 'yyyy-MM-ddTHH:mm:ss');
                        this.ngbModalRef = this.visaModalRef(component, visa);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.visaModalRef(component, new Visa());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    visaModalRef(component: Component, visa: Visa): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.visa = visa;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
