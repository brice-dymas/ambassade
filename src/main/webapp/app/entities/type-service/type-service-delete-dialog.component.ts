import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { TypeService } from './type-service.model';
import { TypeServicePopupService } from './type-service-popup.service';
import { TypeServiceService } from './type-service.service';

@Component({
    selector: 'jhi-type-service-delete-dialog',
    templateUrl: './type-service-delete-dialog.component.html'
})
export class TypeServiceDeleteDialogComponent {

    typeService: TypeService;

    constructor(
        private typeServiceService: TypeServiceService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.typeServiceService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'typeServiceListModification',
                content: 'Deleted an typeService'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-type-service-delete-popup',
    template: ''
})
export class TypeServiceDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private typeServicePopupService: TypeServicePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.typeServicePopupService
                .open(TypeServiceDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
