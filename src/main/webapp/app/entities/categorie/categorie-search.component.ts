  import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Categorie } from './categorie.model';
import { CategoriePopupService } from './categorie-popup.service';
import { CategorieService } from './categorie.service';

@Component({
    selector: 'jhi-categorie-search-dialog',
    templateUrl: './categorie-search.component.html'
})
export class CategorieSearchComponent implements OnInit {

    formModel: Categorie;

    constructor( private eventManager: JhiEventManager ) {}

    ngOnInit() {
        this.formModel = new Categorie();
    }

    search() {
        this.eventManager.broadcast({ name: 'categorieListModification', content: this.formModel});
    }
}
