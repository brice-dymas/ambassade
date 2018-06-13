import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { UniteOrganisationelle } from './unite-organisationelle.model';
import { UniteOrganisationellePopupService } from './unite-organisationelle-popup.service';
import { UniteOrganisationelleService } from './unite-organisationelle.service';

@Component({
    selector: 'jhi-unite-organisationelle-delete-dialog',
    templateUrl: './unite-organisationelle-delete-dialog.component.html'
})
export class UniteOrganisationelleDeleteDialogComponent {

    uniteOrganisationelle: UniteOrganisationelle;

    constructor(
        private uniteOrganisationelleService: UniteOrganisationelleService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.uniteOrganisationelleService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'uniteOrganisationelleListModification',
                content: 'Deleted an uniteOrganisationelle'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-unite-organisationelle-delete-popup',
    template: ''
})
export class UniteOrganisationelleDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private uniteOrganisationellePopupService: UniteOrganisationellePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.uniteOrganisationellePopupService
                .open(UniteOrganisationelleDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
