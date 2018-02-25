/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AmbassadeTestModule } from '../../../test.module';
import { VisaComponent } from '../../../../../../main/webapp/app/entities/visa/visa.component';
import { VisaService } from '../../../../../../main/webapp/app/entities/visa/visa.service';
import { Visa } from '../../../../../../main/webapp/app/entities/visa/visa.model';

describe('Component Tests', () => {

    describe('Visa Management Component', () => {
        let comp: VisaComponent;
        let fixture: ComponentFixture<VisaComponent>;
        let service: VisaService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AmbassadeTestModule],
                declarations: [VisaComponent],
                providers: [
                    VisaService
                ]
            })
            .overrideTemplate(VisaComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(VisaComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VisaService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Visa(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.visas[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
