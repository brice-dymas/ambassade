import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { Paiement } from './paiement.model';
import { PaiementService } from './paiement.service';

@Injectable()
export class PaiementPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private paiementService: PaiementService

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
                this.paiementService.find(id)
                    .subscribe((paiementResponse: HttpResponse<Paiement>) => {
                        const paiement: Paiement = paiementResponse.body;
                        if (paiement.datePaiement) {
                            paiement.datePaiement = {
                                year: paiement.datePaiement.getFullYear(),
                                month: paiement.datePaiement.getMonth() + 1,
                                day: paiement.datePaiement.getDate()
                            };
                        }
                        if (paiement.dateCreation) {
                            paiement.dateCreation = {
                                year: paiement.dateCreation.getFullYear(),
                                month: paiement.dateCreation.getMonth() + 1,
                                day: paiement.dateCreation.getDate()
                            };
                        }
                        if (paiement.dateModification) {
                            paiement.dateModification = {
                                year: paiement.dateModification.getFullYear(),
                                month: paiement.dateModification.getMonth() + 1,
                                day: paiement.dateModification.getDate()
                            };
                        }
                        this.ngbModalRef = this.paiementModalRef(component, paiement);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.paiementModalRef(component, new Paiement());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    paiementModalRef(component: Component, paiement: Paiement): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.paiement = paiement;
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
