import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { ProfileMasterDetailComponent } from 'app/entities/profile-master/profile-master-detail.component';
import { ProfileMaster } from 'app/shared/model/profile-master.model';

describe('Component Tests', () => {
  describe('ProfileMaster Management Detail Component', () => {
    let comp: ProfileMasterDetailComponent;
    let fixture: ComponentFixture<ProfileMasterDetailComponent>;
    const route = ({ data: of({ profileMaster: new ProfileMaster(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProfileMasterDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ProfileMasterDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProfileMasterDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load profileMaster on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.profileMaster).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
