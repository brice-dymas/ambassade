/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { AmbassadeTestModule } from '../../../test.module';
import { LivreDetailComponent } from '../../../../../../main/webapp/app/entities/livre/livre-detail.component';
import { LivreService } from '../../../../../../main/webapp/app/entities/livre/livre.service';
import { Livre } from '../../../../../../main/webapp/app/entities/livre/livre.model';

describe('Component Tests', () => {

    describe('Livre Management Detail Component', () => {
        let comp: LivreDetailComponent;
        let fixture: ComponentFixture<LivreDetailComponent>;
        let service: LivreService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AmbassadeTestModule],
                declarations: [LivreDetailComponent],
                providers: [
                    LivreService
                ]
            })
            .overrideTemplate(LivreDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(LivreDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LivreService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Livre(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.livre).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
