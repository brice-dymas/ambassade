/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AmbassadeTestModule } from '../../../test.module';
import { TypeEntreeComponent } from '../../../../../../main/webapp/app/entities/type-entree/type-entree.component';
import { TypeEntreeService } from '../../../../../../main/webapp/app/entities/type-entree/type-entree.service';
import { TypeEntree } from '../../../../../../main/webapp/app/entities/type-entree/type-entree.model';

describe('Component Tests', () => {

    describe('TypeEntree Management Component', () => {
        let comp: TypeEntreeComponent;
        let fixture: ComponentFixture<TypeEntreeComponent>;
        let service: TypeEntreeService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AmbassadeTestModule],
                declarations: [TypeEntreeComponent],
                providers: [
                    TypeEntreeService
                ]
            })
            .overrideTemplate(TypeEntreeComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TypeEntreeComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TypeEntreeService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new TypeEntree(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.typeEntrees[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
