import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { UniteOrganisationelle } from './unite-organisationelle.model';
import { UniteOrganisationelleService } from './unite-organisationelle.service';

@Injectable()
export class UniteOrganisationellePopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private uniteOrganisationelleService: UniteOrganisationelleService

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
                this.uniteOrganisationelleService.find(id)
                    .subscribe((uniteOrganisationelleResponse: HttpResponse<UniteOrganisationelle>) => {
                        const uniteOrganisationelle: UniteOrganisationelle = uniteOrganisationelleResponse.body;
                        this.ngbModalRef = this.uniteOrganisationelleModalRef(component, uniteOrganisationelle);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.uniteOrganisationelleModalRef(component, new UniteOrganisationelle());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    uniteOrganisationelleModalRef(component: Component, uniteOrganisationelle: UniteOrganisationelle): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.uniteOrganisationelle = uniteOrganisationelle;
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
