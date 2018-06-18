import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { Visa } from './visa.model';
import { VisaService } from './visa.service';

@Injectable()
export class VisaPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
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
                        if (visa.dateEmission) {
                            visa.dateEmission = {
                                year: visa.dateEmission.getFullYear(),
                                month: visa.dateEmission.getMonth() + 1,
                                day: visa.dateEmission.getDate()
                            };
                        }
                        if (visa.dateExpiration) {
                            visa.dateExpiration = {
                                year: visa.dateExpiration.getFullYear(),
                                month: visa.dateExpiration.getMonth() + 1,
                                day: visa.dateExpiration.getDate()
                            };
                        }
                        if (visa.dateCreation) {
                            visa.dateCreation = {
                                year: visa.dateCreation.getFullYear(),
                                month: visa.dateCreation.getMonth() + 1,
                                day: visa.dateCreation.getDate()
                            };
                        }
                        if (visa.dateModification) {
                            visa.dateModification = {
                                year: visa.dateModification.getFullYear(),
                                month: visa.dateModification.getMonth() + 1,
                                day: visa.dateModification.getDate()
                            };
                        }
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
