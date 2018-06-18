import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { TypeEntree } from './type-entree.model';
import { TypeEntreePopupService } from './type-entree-popup.service';
import { TypeEntreeService } from './type-entree.service';

@Component({
    selector: 'jhi-type-entree-delete-dialog',
    templateUrl: './type-entree-delete-dialog.component.html'
})
export class TypeEntreeDeleteDialogComponent {

    typeEntree: TypeEntree;

    constructor(
        private typeEntreeService: TypeEntreeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.typeEntreeService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'typeEntreeListModification',
                content: 'Deleted an typeEntree'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-type-entree-delete-popup',
    template: ''
})
export class TypeEntreeDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private typeEntreePopupService: TypeEntreePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.typeEntreePopupService
                .open(TypeEntreeDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
