import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Passeport } from './passeport.model';
import { PasseportPopupService } from './passeport-popup.service';
import { PasseportService } from './passeport.service';

@Component({
    selector: 'jhi-passeport-delete-dialog',
    templateUrl: './passeport-delete-dialog.component.html'
})
export class PasseportDeleteDialogComponent {

    passeport: Passeport;

    constructor(
        private passeportService: PasseportService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.passeportService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'passeportListModification',
                content: 'Deleted an passeport'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-passeport-delete-popup',
    template: ''
})
export class PasseportDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private passeportPopupService: PasseportPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.passeportPopupService
                .open(PasseportDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
