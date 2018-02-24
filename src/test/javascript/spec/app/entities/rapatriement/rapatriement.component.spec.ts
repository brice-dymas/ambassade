/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AmbassadeTestModule } from '../../../test.module';
import { RapatriementComponent } from '../../../../../../main/webapp/app/entities/rapatriement/rapatriement.component';
import { RapatriementService } from '../../../../../../main/webapp/app/entities/rapatriement/rapatriement.service';
import { Rapatriement } from '../../../../../../main/webapp/app/entities/rapatriement/rapatriement.model';

describe('Component Tests', () => {

    describe('Rapatriement Management Component', () => {
        let comp: RapatriementComponent;
        let fixture: ComponentFixture<RapatriementComponent>;
        let service: RapatriementService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AmbassadeTestModule],
                declarations: [RapatriementComponent],
                providers: [
                    RapatriementService
                ]
            })
            .overrideTemplate(RapatriementComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(RapatriementComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RapatriementService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Rapatriement(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.rapatriements[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
