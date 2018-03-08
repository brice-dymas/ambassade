import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { Rapatriement } from './rapatriement.model';
import { RapatriementService } from './rapatriement.service';

@Injectable()
export class RapatriementPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private rapatriementService: RapatriementService

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
                this.rapatriementService.find(id)
                    .subscribe((rapatriementResponse: HttpResponse<Rapatriement>) => {
                        const rapatriement: Rapatriement = rapatriementResponse.body;
                        if (rapatriement.dateNaissance) {
                            rapatriement.dateNaissance = {
                                year: rapatriement.dateNaissance.getFullYear(),
                                month: rapatriement.dateNaissance.getMonth() + 1,
                                day: rapatriement.dateNaissance.getDate()
                            };
                        }
                        if (rapatriement.dateRapatriement) {
                            rapatriement.dateRapatriement = {
                                year: rapatriement.dateRapatriement.getFullYear(),
                                month: rapatriement.dateRapatriement.getMonth() + 1,
                                day: rapatriement.dateRapatriement.getDate()
                            };
                        }
                        this.ngbModalRef = this.rapatriementModalRef(component, rapatriement);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.rapatriementModalRef(component, new Rapatriement());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    rapatriementModalRef(component: Component, rapatriement: Rapatriement): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.rapatriement = rapatriement;
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
