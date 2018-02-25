/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { AmbassadeTestModule } from '../../../test.module';
import { MontantDetailComponent } from '../../../../../../main/webapp/app/entities/montant/montant-detail.component';
import { MontantService } from '../../../../../../main/webapp/app/entities/montant/montant.service';
import { Montant } from '../../../../../../main/webapp/app/entities/montant/montant.model';

describe('Component Tests', () => {

    describe('Montant Management Detail Component', () => {
        let comp: MontantDetailComponent;
        let fixture: ComponentFixture<MontantDetailComponent>;
        let service: MontantService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AmbassadeTestModule],
                declarations: [MontantDetailComponent],
                providers: [
                    MontantService
                ]
            })
            .overrideTemplate(MontantDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MontantDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MontantService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Montant(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.montant).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
