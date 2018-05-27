/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AmbassadeTestModule } from '../../../test.module';
import { PaiementComponent } from '../../../../../../main/webapp/app/entities/paiement/paiement.component';
import { PaiementService } from '../../../../../../main/webapp/app/entities/paiement/paiement.service';
import { Paiement } from '../../../../../../main/webapp/app/entities/paiement/paiement.model';

describe('Component Tests', () => {

    describe('Paiement Management Component', () => {
        let comp: PaiementComponent;
        let fixture: ComponentFixture<PaiementComponent>;
        let service: PaiementService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AmbassadeTestModule],
                declarations: [PaiementComponent],
                providers: [
                    PaiementService
                ]
            })
            .overrideTemplate(PaiementComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(PaiementComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PaiementService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Paiement(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.paiements[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
