import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { UserMasterDetailComponent } from 'app/entities/user-master/user-master-detail.component';
import { UserMaster } from 'app/shared/model/user-master.model';

describe('Component Tests', () => {
  describe('UserMaster Management Detail Component', () => {
    let comp: UserMasterDetailComponent;
    let fixture: ComponentFixture<UserMasterDetailComponent>;
    const route = ({ data: of({ userMaster: new UserMaster(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [UserMasterDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(UserMasterDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UserMasterDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load userMaster on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.userMaster).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
