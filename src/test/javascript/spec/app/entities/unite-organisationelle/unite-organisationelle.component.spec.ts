/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AmbassadeTestModule } from '../../../test.module';
import { UniteOrganisationelleComponent } from '../../../../../../main/webapp/app/entities/unite-organisationelle/unite-organisationelle.component';
import { UniteOrganisationelleService } from '../../../../../../main/webapp/app/entities/unite-organisationelle/unite-organisationelle.service';
import { UniteOrganisationelle } from '../../../../../../main/webapp/app/entities/unite-organisationelle/unite-organisationelle.model';

describe('Component Tests', () => {

    describe('UniteOrganisationelle Management Component', () => {
        let comp: UniteOrganisationelleComponent;
        let fixture: ComponentFixture<UniteOrganisationelleComponent>;
        let service: UniteOrganisationelleService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AmbassadeTestModule],
                declarations: [UniteOrganisationelleComponent],
                providers: [
                    UniteOrganisationelleService
                ]
            })
            .overrideTemplate(UniteOrganisationelleComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UniteOrganisationelleComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UniteOrganisationelleService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new UniteOrganisationelle(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.uniteOrganisationelles[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
