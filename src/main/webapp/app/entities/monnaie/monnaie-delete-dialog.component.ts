import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Monnaie } from './monnaie.model';
import { MonnaiePopupService } from './monnaie-popup.service';
import { MonnaieService } from './monnaie.service';

@Component({
    selector: 'jhi-monnaie-delete-dialog',
    templateUrl: './monnaie-delete-dialog.component.html'
})
export class MonnaieDeleteDialogComponent {

    monnaie: Monnaie;

    constructor(
        private monnaieService: MonnaieService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.monnaieService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'monnaieListModification',
                content: 'Deleted an monnaie'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-monnaie-delete-popup',
    template: ''
})
export class MonnaieDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private monnaiePopupService: MonnaiePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.monnaiePopupService
                .open(MonnaieDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
