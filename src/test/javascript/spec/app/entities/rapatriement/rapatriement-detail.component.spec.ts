/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { AmbassadeTestModule } from '../../../test.module';
import { RapatriementDetailComponent } from '../../../../../../main/webapp/app/entities/rapatriement/rapatriement-detail.component';
import { RapatriementService } from '../../../../../../main/webapp/app/entities/rapatriement/rapatriement.service';
import { Rapatriement } from '../../../../../../main/webapp/app/entities/rapatriement/rapatriement.model';

describe('Component Tests', () => {

    describe('Rapatriement Management Detail Component', () => {
        let comp: RapatriementDetailComponent;
        let fixture: ComponentFixture<RapatriementDetailComponent>;
        let service: RapatriementService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AmbassadeTestModule],
                declarations: [RapatriementDetailComponent],
                providers: [
                    RapatriementService
                ]
            })
            .overrideTemplate(RapatriementDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(RapatriementDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RapatriementService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Rapatriement(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.rapatriement).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
