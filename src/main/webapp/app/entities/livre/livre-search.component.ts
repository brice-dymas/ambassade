import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Livre } from './livre.model';
import { LivrePopupService } from './livre-popup.service';
import { LivreService } from './livre.service';

@Component({
    selector: 'jhi-livre-search',
    templateUrl: './livre-search.component.html'
})
export class LivreSearchComponent implements OnInit {

    formModel: Livre;

    constructor(private eventManager: JhiEventManager) {}

    ngOnInit() {
        this.formModel = new Livre();
    }

    search() {
        this.eventManager.broadcast({name: 'livreListModification', content: this.formModel});
    }
}
