/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { AmbassadeTestModule } from '../../../test.module';
import { TypeEntreeDetailComponent } from '../../../../../../main/webapp/app/entities/type-entree/type-entree-detail.component';
import { TypeEntreeService } from '../../../../../../main/webapp/app/entities/type-entree/type-entree.service';
import { TypeEntree } from '../../../../../../main/webapp/app/entities/type-entree/type-entree.model';

describe('Component Tests', () => {

    describe('TypeEntree Management Detail Component', () => {
        let comp: TypeEntreeDetailComponent;
        let fixture: ComponentFixture<TypeEntreeDetailComponent>;
        let service: TypeEntreeService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AmbassadeTestModule],
                declarations: [TypeEntreeDetailComponent],
                providers: [
                    TypeEntreeService
                ]
            })
            .overrideTemplate(TypeEntreeDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TypeEntreeDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TypeEntreeService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new TypeEntree(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.typeEntree).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
