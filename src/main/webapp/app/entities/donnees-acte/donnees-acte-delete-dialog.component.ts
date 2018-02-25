import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { DonneesActe } from './donnees-acte.model';
import { DonneesActePopupService } from './donnees-acte-popup.service';
import { DonneesActeService } from './donnees-acte.service';

@Component({
    selector: 'jhi-donnees-acte-delete-dialog',
    templateUrl: './donnees-acte-delete-dialog.component.html'
})
export class DonneesActeDeleteDialogComponent {

    donneesActe: DonneesActe;

    constructor(
        private donneesActeService: DonneesActeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.donneesActeService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'donneesActeListModification',
                content: 'Deleted an donneesActe'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-donnees-acte-delete-popup',
    template: ''
})
export class DonneesActeDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private donneesActePopupService: DonneesActePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.donneesActePopupService
                .open(DonneesActeDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
