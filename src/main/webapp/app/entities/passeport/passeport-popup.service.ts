import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { Passeport } from './passeport.model';
import { PasseportService } from './passeport.service';

@Injectable()
export class PasseportPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private passeportService: PasseportService

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
                this.passeportService.find(id)
                    .subscribe((passeportResponse: HttpResponse<Passeport>) => {
                        const passeport: Passeport = passeportResponse.body;
                        passeport.soumisLe = this.datePipe
                            .transform(passeport.soumisLe, 'yyyy-MM-ddTHH:mm:ss');
                        passeport.delivreLe = this.datePipe
                            .transform(passeport.delivreLe, 'yyyy-MM-ddTHH:mm:ss');
                        passeport.dateEmission = this.datePipe
                            .transform(passeport.dateEmission, 'yyyy-MM-ddTHH:mm:ss');
                        passeport.dateExpiration = this.datePipe
                            .transform(passeport.dateExpiration, 'yyyy-MM-ddTHH:mm:ss');
                        this.ngbModalRef = this.passeportModalRef(component, passeport);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.passeportModalRef(component, new Passeport());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    passeportModalRef(component: Component, passeport: Passeport): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.passeport = passeport;
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
