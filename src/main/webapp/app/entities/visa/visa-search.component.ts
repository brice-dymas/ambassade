import { Component, OnInit } from '@angular/core';

import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

// import { Visa } from './Visa.model';
import { VisaDtoModel} from './visa-dto.model';
import { TypeServiceService, TypeService } from '../type-service';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

@Component({
    selector: 'jhi-visa-search',
    templateUrl: './visa-search.component.html',
    styles: []
})
export class VisaSearchComponent implements OnInit {

    // formModel: Visa;
    formModel: VisaDtoModel;
    typeservices: TypeService[];

    constructor(private eventManager: JhiEventManager,
        private jhiAlertService: JhiAlertService,
        private typeServiceService: TypeServiceService) { }

    ngOnInit() {
        // this.formModel = new Visa();
        this.formModel = new VisaDtoModel();
        this.typeServiceService.query()
            .subscribe((res: HttpResponse<TypeService[]>) => { this.typeservices = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    search() {
        const copy: Object = Object.assign({}, this.formModel.typeService);
        this.formModel.typeService = copy['id'];
        this.eventManager.broadcast({ name: 'visaListModification', content: this.formModel});
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackTypeServiceById(index: number, item: TypeService) {
        return item.id;
    }

}
