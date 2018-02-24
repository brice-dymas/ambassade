import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Montant } from './montant.model';
import { MontantPopupService } from './montant-popup.service';
import { MontantService } from './montant.service';

@Component({
    selector: 'jhi-montant-delete-dialog',
    templateUrl: './montant-delete-dialog.component.html'
})
export class MontantDeleteDialogComponent {

    montant: Montant;

    constructor(
        private montantService: MontantService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.montantService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'montantListModification',
                content: 'Deleted an montant'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-montant-delete-popup',
    template: ''
})
export class MontantDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private montantPopupService: MontantPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.montantPopupService
                .open(MontantDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
