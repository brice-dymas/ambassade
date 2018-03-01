/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AmbassadeTestModule } from '../../../test.module';
import { DonneesActeComponent } from '../../../../../../main/webapp/app/entities/donnees-acte/donnees-acte.component';
import { DonneesActeService } from '../../../../../../main/webapp/app/entities/donnees-acte/donnees-acte.service';
import { DonneesActe } from '../../../../../../main/webapp/app/entities/donnees-acte/donnees-acte.model';

describe('Component Tests', () => {

    describe('DonneesActe Management Component', () => {
        let comp: DonneesActeComponent;
        let fixture: ComponentFixture<DonneesActeComponent>;
        let service: DonneesActeService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AmbassadeTestModule],
                declarations: [DonneesActeComponent],
                providers: [
                    DonneesActeService
                ]
            })
            .overrideTemplate(DonneesActeComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DonneesActeComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DonneesActeService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new DonneesActe(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.donneesActes[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
