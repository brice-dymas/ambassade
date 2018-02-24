/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AmbassadeTestModule } from '../../../test.module';
import { LivreComponent } from '../../../../../../main/webapp/app/entities/livre/livre.component';
import { LivreService } from '../../../../../../main/webapp/app/entities/livre/livre.service';
import { Livre } from '../../../../../../main/webapp/app/entities/livre/livre.model';

describe('Component Tests', () => {

    describe('Livre Management Component', () => {
        let comp: LivreComponent;
        let fixture: ComponentFixture<LivreComponent>;
        let service: LivreService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AmbassadeTestModule],
                declarations: [LivreComponent],
                providers: [
                    LivreService
                ]
            })
            .overrideTemplate(LivreComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(LivreComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LivreService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Livre(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.livres[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
