/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { AmbassadeTestModule } from '../../../test.module';
import { TypeServiceDetailComponent } from '../../../../../../main/webapp/app/entities/type-service/type-service-detail.component';
import { TypeServiceService } from '../../../../../../main/webapp/app/entities/type-service/type-service.service';
import { TypeService } from '../../../../../../main/webapp/app/entities/type-service/type-service.model';

describe('Component Tests', () => {

    describe('TypeService Management Detail Component', () => {
        let comp: TypeServiceDetailComponent;
        let fixture: ComponentFixture<TypeServiceDetailComponent>;
        let service: TypeServiceService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AmbassadeTestModule],
                declarations: [TypeServiceDetailComponent],
                providers: [
                    TypeServiceService
                ]
            })
            .overrideTemplate(TypeServiceDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TypeServiceDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TypeServiceService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new TypeService(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.typeService).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
