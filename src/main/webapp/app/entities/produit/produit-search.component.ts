import { Component, OnInit } from '@angular/core';

import { JhiEventManager } from 'ng-jhipster';

import { Produit } from './produit.model';

@Component({
  selector: 'jhi-produit-search',
  templateUrl: './produit-search.component.html',
  styles: []
})
export class ProduitSearchComponent implements OnInit {

  formModel: Produit;

  constructor(private eventManager: JhiEventManager) { }

  ngOnInit() {
      this.formModel = new Produit();
  }

  search() {
    this.eventManager.broadcast({ name: 'produitListModification', content: this.formModel});
}

}
