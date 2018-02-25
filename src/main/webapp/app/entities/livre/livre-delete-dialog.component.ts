import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Livre } from './livre.model';
import { LivrePopupService } from './livre-popup.service';
import { LivreService } from './livre.service';

@Component({
    selector: 'jhi-livre-delete-dialog',
    templateUrl: './livre-delete-dialog.component.html'
})
export class LivreDeleteDialogComponent {

    livre: Livre;

    constructor(
        private livreService: LivreService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.livreService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'livreListModification',
                content: 'Deleted an livre'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-livre-delete-popup',
    template: ''
})
export class LivreDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private livrePopupService: LivrePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.livrePopupService
                .open(LivreDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
