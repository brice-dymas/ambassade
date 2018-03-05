import { Component, OnInit } from '@angular/core';

import { JhiEventManager } from 'ng-jhipster';

import { Montant } from './montant.model';

@Component({
  selector: 'jhi-montant-search',
  templateUrl: './montant-search.component.html',
  styles: []
})
export class MontantSearchComponent implements OnInit {

  formModel: Montant;

  constructor(private eventManager: JhiEventManager) { }

  ngOnInit() {
      this.formModel = new Montant();
  }

  search() {
    this.eventManager.broadcast({ name: 'montantListModification', content: this.formModel});
}

}
