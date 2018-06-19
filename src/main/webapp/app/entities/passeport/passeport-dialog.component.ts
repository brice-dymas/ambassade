import {Component, OnInit, OnDestroy} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpResponse, HttpErrorResponse} from '@angular/common/http';

import {Observable} from 'rxjs/Observable';
import {Subscription} from 'rxjs/Subscription';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {JhiEventManager, JhiAlertService, JhiDataUtils} from 'ng-jhipster';

import {Passeport} from './passeport.model';
import {PasseportPopupService} from './passeport-popup.service';
import {PasseportService} from './passeport.service';
import {TypeService, TypeServiceService} from '../type-service';

@Component({
    selector: 'jhi-passeport-dialog',
    templateUrl: './passeport-dialog.component.html'
})
export class PasseportDialogComponent implements OnInit {

    passeport: Passeport;
    isSaving: boolean;

    typeservices: TypeService[];
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(private dataUtils: JhiDataUtils,
                private jhiAlertService: JhiAlertService,
                private passeportService: PasseportService,
                private typeServiceService: TypeServiceService,
                private eventManager: JhiEventManager,
                private route: ActivatedRoute,
                private router: Router) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.typeServiceService.queryForPasseport()
            .subscribe((res: HttpResponse<TypeService[]>) => {
                this.typeservices = res.body;
            }, (res: HttpErrorResponse) => this.onError(res.message));

        this.route.params.subscribe((params) => {
            if (params['id']) {
                this.load(params['id']);
            } else {
                this.passeport = new Passeport();
            }
        });
        this.registerChangeInPasseports();
    }

    load(id) {
        this.passeportService.find(id)
            .subscribe((passeportResponse: HttpResponse<Passeport>) => {
                this.passeport = passeportResponse.body;
            });
    }

    previousState() {
        window.history.back();
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clear() {
        // this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.passeport.id !== undefined) {
            this.subscribeToSaveResponse(
                this.passeportService.update(this.passeport));
        } else {
            this.subscribeToSaveResponse(
                this.passeportService.create(this.passeport));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Passeport>>) {
        result.subscribe((res: HttpResponse<Passeport>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Passeport) {
        this.eventManager.broadcast({name: 'passeportListModification', content: 'OK'});
        this.isSaving = false;
        this.router.navigate(['passeport']);
        // this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackTypeServiceById(index: number, item: TypeService) {
        return item.id;
    }

    registerChangeInPasseports() {
        this.eventSubscriber = this.eventManager.subscribe(
            'passeportListModification',
            (response) => this.load(this.passeport.id)
        );
    }
}

@Component({
    selector: 'jhi-passeport-popup',
    template: ''
})
export class PasseportPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(private route: ActivatedRoute,
                private passeportPopupService: PasseportPopupService) {
    }

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if (params['id']) {
                this.passeportPopupService
                    .open(PasseportDialogComponent as Component, params['id']);
            } else {
                this.passeportPopupService
                    .open(PasseportDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
