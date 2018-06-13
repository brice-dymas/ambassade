/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { AmbassadeTestModule } from '../../../test.module';
import { UniteOrganisationelleDialogComponent } from '../../../../../../main/webapp/app/entities/unite-organisationelle/unite-organisationelle-dialog.component';
import { UniteOrganisationelleService } from '../../../../../../main/webapp/app/entities/unite-organisationelle/unite-organisationelle.service';
import { UniteOrganisationelle } from '../../../../../../main/webapp/app/entities/unite-organisationelle/unite-organisationelle.model';

describe('Component Tests', () => {

    describe('UniteOrganisationelle Management Dialog Component', () => {
        let comp: UniteOrganisationelleDialogComponent;
        let fixture: ComponentFixture<UniteOrganisationelleDialogComponent>;
        let service: UniteOrganisationelleService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AmbassadeTestModule],
                declarations: [UniteOrganisationelleDialogComponent],
                providers: [
                    UniteOrganisationelleService
                ]
            })
            .overrideTemplate(UniteOrganisationelleDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UniteOrganisationelleDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UniteOrganisationelleService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new UniteOrganisationelle(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.uniteOrganisationelle = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'uniteOrganisationelleListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new UniteOrganisationelle();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.uniteOrganisationelle = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'uniteOrganisationelleListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
