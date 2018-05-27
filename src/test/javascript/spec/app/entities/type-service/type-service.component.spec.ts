/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AmbassadeTestModule } from '../../../test.module';
import { TypeServiceComponent } from '../../../../../../main/webapp/app/entities/type-service/type-service.component';
import { TypeServiceService } from '../../../../../../main/webapp/app/entities/type-service/type-service.service';
import { TypeService } from '../../../../../../main/webapp/app/entities/type-service/type-service.model';

describe('Component Tests', () => {

    describe('TypeService Management Component', () => {
        let comp: TypeServiceComponent;
        let fixture: ComponentFixture<TypeServiceComponent>;
        let service: TypeServiceService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AmbassadeTestModule],
                declarations: [TypeServiceComponent],
                providers: [
                    TypeServiceService
                ]
            })
            .overrideTemplate(TypeServiceComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TypeServiceComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TypeServiceService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new TypeService(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.typeServices[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
