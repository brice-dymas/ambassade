/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AmbassadeTestModule } from '../../../test.module';
import { PasseportComponent } from '../../../../../../main/webapp/app/entities/passeport/passeport.component';
import { PasseportService } from '../../../../../../main/webapp/app/entities/passeport/passeport.service';
import { Passeport } from '../../../../../../main/webapp/app/entities/passeport/passeport.model';

describe('Component Tests', () => {

    describe('Passeport Management Component', () => {
        let comp: PasseportComponent;
        let fixture: ComponentFixture<PasseportComponent>;
        let service: PasseportService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AmbassadeTestModule],
                declarations: [PasseportComponent],
                providers: [
                    PasseportService
                ]
            })
            .overrideTemplate(PasseportComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(PasseportComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PasseportService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Passeport(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.passeports[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
