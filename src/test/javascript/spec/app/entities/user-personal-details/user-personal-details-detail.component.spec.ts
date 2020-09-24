import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { UserPersonalDetailsDetailComponent } from 'app/entities/user-personal-details/user-personal-details-detail.component';
import { UserPersonalDetails } from 'app/shared/model/user-personal-details.model';

describe('Component Tests', () => {
  describe('UserPersonalDetails Management Detail Component', () => {
    let comp: UserPersonalDetailsDetailComponent;
    let fixture: ComponentFixture<UserPersonalDetailsDetailComponent>;
    const route = ({ data: of({ userPersonalDetails: new UserPersonalDetails(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [UserPersonalDetailsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(UserPersonalDetailsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UserPersonalDetailsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load userPersonalDetails on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.userPersonalDetails).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
