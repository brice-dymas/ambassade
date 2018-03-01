import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { DonneesActe } from './donnees-acte.model';
import { DonneesActeService } from './donnees-acte.service';

@Injectable()
export class DonneesActePopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private donneesActeService: DonneesActeService

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
                this.donneesActeService.find(id)
                    .subscribe((donneesActeResponse: HttpResponse<DonneesActe>) => {
                        const donneesActe: DonneesActe = donneesActeResponse.body;
                        donneesActe.dateDuJourChiffre = this.datePipe
                            .transform(donneesActe.dateDuJourChiffre, 'yyyy-MM-ddTHH:mm:ss');
                        donneesActe.dateNaissanceChiffre = this.datePipe
                            .transform(donneesActe.dateNaissanceChiffre, 'yyyy-MM-ddTHH:mm:ss');
                        this.ngbModalRef = this.donneesActeModalRef(component, donneesActe);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.donneesActeModalRef(component, new DonneesActe());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    donneesActeModalRef(component: Component, donneesActe: DonneesActe): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.donneesActe = donneesActe;
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
