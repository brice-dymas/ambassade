import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Visa } from './visa.model';
import { VisaPopupService } from './visa-popup.service';
import { VisaService } from './visa.service';

@Component({
    selector: 'jhi-visa-delete-dialog',
    templateUrl: './visa-delete-dialog.component.html'
})
export class VisaDeleteDialogComponent {

    visa: Visa;

    constructor(
        private visaService: VisaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.visaService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'visaListModification',
                content: 'Deleted an visa'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-visa-delete-popup',
    template: ''
})
export class VisaDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private visaPopupService: VisaPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.visaPopupService
                .open(VisaDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
