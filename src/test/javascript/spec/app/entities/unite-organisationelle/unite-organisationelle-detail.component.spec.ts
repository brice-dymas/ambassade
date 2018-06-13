/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { AmbassadeTestModule } from '../../../test.module';
import { UniteOrganisationelleDetailComponent } from '../../../../../../main/webapp/app/entities/unite-organisationelle/unite-organisationelle-detail.component';
import { UniteOrganisationelleService } from '../../../../../../main/webapp/app/entities/unite-organisationelle/unite-organisationelle.service';
import { UniteOrganisationelle } from '../../../../../../main/webapp/app/entities/unite-organisationelle/unite-organisationelle.model';

describe('Component Tests', () => {

    describe('UniteOrganisationelle Management Detail Component', () => {
        let comp: UniteOrganisationelleDetailComponent;
        let fixture: ComponentFixture<UniteOrganisationelleDetailComponent>;
        let service: UniteOrganisationelleService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AmbassadeTestModule],
                declarations: [UniteOrganisationelleDetailComponent],
                providers: [
                    UniteOrganisationelleService
                ]
            })
            .overrideTemplate(UniteOrganisationelleDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UniteOrganisationelleDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UniteOrganisationelleService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new UniteOrganisationelle(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.uniteOrganisationelle).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
