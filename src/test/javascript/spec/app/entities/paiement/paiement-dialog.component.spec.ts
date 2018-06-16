/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { AmbassadeTestModule } from '../../../test.module';
import { PaiementDialogComponent } from '../../../../../../main/webapp/app/entities/paiement/paiement-dialog.component';
import { PaiementService } from '../../../../../../main/webapp/app/entities/paiement/paiement.service';
import { Paiement } from '../../../../../../main/webapp/app/entities/paiement/paiement.model';
import { VisaService } from '../../../../../../main/webapp/app/entities/visa';
import { PasseportService } from '../../../../../../main/webapp/app/entities/passeport';
import { TypeServiceService } from '../../../../../../main/webapp/app/entities/type-service';
import { UserService } from '../../../../../../main/webapp/app/shared';
import { UniteOrganisationelleService } from '../../../../../../main/webapp/app/entities/unite-organisationelle';

describe('Component Tests', () => {

    describe('Paiement Management Dialog Component', () => {
        let comp: PaiementDialogComponent;
        let fixture: ComponentFixture<PaiementDialogComponent>;
        let service: PaiementService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AmbassadeTestModule],
                declarations: [PaiementDialogComponent],
                providers: [
                    VisaService,
                    PasseportService,
                    TypeServiceService,
                    UserService,
                    UniteOrganisationelleService,
                    PaiementService
                ]
            })
            .overrideTemplate(PaiementDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(PaiementDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PaiementService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Paiement(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.paiement = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'paiementListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Paiement();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.paiement = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'paiementListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
