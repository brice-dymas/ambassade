import { Component, OnInit } from '@angular/core';

import { JhiEventManager } from 'ng-jhipster';

import { Monnaie } from './monnaie.model';

@Component({
  selector: 'jhi-monnaie-search',
  templateUrl: './monnaie-search.component.html',
  styles: []
})
export class MonnaieSearchComponent implements OnInit {

  formModel: Monnaie;

  constructor(private eventManager: JhiEventManager) { }

  ngOnInit() {
      this.formModel = new Monnaie();
  }

  search() {
    this.eventManager.broadcast({ name: 'monnaieListModification', content: this.formModel});
}

}
