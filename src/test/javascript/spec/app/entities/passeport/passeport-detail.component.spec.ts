/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { AmbassadeTestModule } from '../../../test.module';
import { PasseportDetailComponent } from '../../../../../../main/webapp/app/entities/passeport/passeport-detail.component';
import { PasseportService } from '../../../../../../main/webapp/app/entities/passeport/passeport.service';
import { Passeport } from '../../../../../../main/webapp/app/entities/passeport/passeport.model';

describe('Component Tests', () => {

    describe('Passeport Management Detail Component', () => {
        let comp: PasseportDetailComponent;
        let fixture: ComponentFixture<PasseportDetailComponent>;
        let service: PasseportService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AmbassadeTestModule],
                declarations: [PasseportDetailComponent],
                providers: [
                    PasseportService
                ]
            })
            .overrideTemplate(PasseportDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(PasseportDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PasseportService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Passeport(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.passeport).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
