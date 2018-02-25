/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AmbassadeTestModule } from '../../../test.module';
import { MontantComponent } from '../../../../../../main/webapp/app/entities/montant/montant.component';
import { MontantService } from '../../../../../../main/webapp/app/entities/montant/montant.service';
import { Montant } from '../../../../../../main/webapp/app/entities/montant/montant.model';

describe('Component Tests', () => {

    describe('Montant Management Component', () => {
        let comp: MontantComponent;
        let fixture: ComponentFixture<MontantComponent>;
        let service: MontantService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AmbassadeTestModule],
                declarations: [MontantComponent],
                providers: [
                    MontantService
                ]
            })
            .overrideTemplate(MontantComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MontantComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MontantService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Montant(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.montants[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
