import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { UserCompanyDetailsDetailComponent } from 'app/entities/user-company-details/user-company-details-detail.component';
import { UserCompanyDetails } from 'app/shared/model/user-company-details.model';

describe('Component Tests', () => {
  describe('UserCompanyDetails Management Detail Component', () => {
    let comp: UserCompanyDetailsDetailComponent;
    let fixture: ComponentFixture<UserCompanyDetailsDetailComponent>;
    const route = ({ data: of({ userCompanyDetails: new UserCompanyDetails(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [UserCompanyDetailsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(UserCompanyDetailsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UserCompanyDetailsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load userCompanyDetails on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.userCompanyDetails).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
