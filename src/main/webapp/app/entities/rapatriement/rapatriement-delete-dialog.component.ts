import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Rapatriement } from './rapatriement.model';
import { RapatriementPopupService } from './rapatriement-popup.service';
import { RapatriementService } from './rapatriement.service';

@Component({
    selector: 'jhi-rapatriement-delete-dialog',
    templateUrl: './rapatriement-delete-dialog.component.html'
})
export class RapatriementDeleteDialogComponent {

    rapatriement: Rapatriement;

    constructor(
        private rapatriementService: RapatriementService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.rapatriementService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'rapatriementListModification',
                content: 'Deleted an rapatriement'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-rapatriement-delete-popup',
    template: ''
})
export class RapatriementDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private rapatriementPopupService: RapatriementPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.rapatriementPopupService
                .open(RapatriementDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
