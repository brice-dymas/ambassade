/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { AmbassadeTestModule } from '../../../test.module';
import { MonnaieDetailComponent } from '../../../../../../main/webapp/app/entities/monnaie/monnaie-detail.component';
import { MonnaieService } from '../../../../../../main/webapp/app/entities/monnaie/monnaie.service';
import { Monnaie } from '../../../../../../main/webapp/app/entities/monnaie/monnaie.model';

describe('Component Tests', () => {

    describe('Monnaie Management Detail Component', () => {
        let comp: MonnaieDetailComponent;
        let fixture: ComponentFixture<MonnaieDetailComponent>;
        let service: MonnaieService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AmbassadeTestModule],
                declarations: [MonnaieDetailComponent],
                providers: [
                    MonnaieService
                ]
            })
            .overrideTemplate(MonnaieDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MonnaieDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MonnaieService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Monnaie(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.monnaie).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
