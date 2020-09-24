import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { UserLanguageDetailsDetailComponent } from 'app/entities/user-language-details/user-language-details-detail.component';
import { UserLanguageDetails } from 'app/shared/model/user-language-details.model';

describe('Component Tests', () => {
  describe('UserLanguageDetails Management Detail Component', () => {
    let comp: UserLanguageDetailsDetailComponent;
    let fixture: ComponentFixture<UserLanguageDetailsDetailComponent>;
    const route = ({ data: of({ userLanguageDetails: new UserLanguageDetails(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [UserLanguageDetailsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(UserLanguageDetailsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UserLanguageDetailsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load userLanguageDetails on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.userLanguageDetails).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
