/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AmbassadeTestModule } from '../../../test.module';
import { MonnaieComponent } from '../../../../../../main/webapp/app/entities/monnaie/monnaie.component';
import { MonnaieService } from '../../../../../../main/webapp/app/entities/monnaie/monnaie.service';
import { Monnaie } from '../../../../../../main/webapp/app/entities/monnaie/monnaie.model';

describe('Component Tests', () => {

    describe('Monnaie Management Component', () => {
        let comp: MonnaieComponent;
        let fixture: ComponentFixture<MonnaieComponent>;
        let service: MonnaieService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AmbassadeTestModule],
                declarations: [MonnaieComponent],
                providers: [
                    MonnaieService
                ]
            })
            .overrideTemplate(MonnaieComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MonnaieComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MonnaieService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Monnaie(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.monnaies[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
