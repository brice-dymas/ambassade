/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { AmbassadeTestModule } from '../../../test.module';
import { DonneesActeDetailComponent } from '../../../../../../main/webapp/app/entities/donnees-acte/donnees-acte-detail.component';
import { DonneesActeService } from '../../../../../../main/webapp/app/entities/donnees-acte/donnees-acte.service';
import { DonneesActe } from '../../../../../../main/webapp/app/entities/donnees-acte/donnees-acte.model';

describe('Component Tests', () => {

    describe('DonneesActe Management Detail Component', () => {
        let comp: DonneesActeDetailComponent;
        let fixture: ComponentFixture<DonneesActeDetailComponent>;
        let service: DonneesActeService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AmbassadeTestModule],
                declarations: [DonneesActeDetailComponent],
                providers: [
                    DonneesActeService
                ]
            })
            .overrideTemplate(DonneesActeDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DonneesActeDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DonneesActeService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new DonneesActe(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.donneesActe).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
