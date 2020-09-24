import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { SkillMasterDetailComponent } from 'app/entities/skill-master/skill-master-detail.component';
import { SkillMaster } from 'app/shared/model/skill-master.model';

describe('Component Tests', () => {
  describe('SkillMaster Management Detail Component', () => {
    let comp: SkillMasterDetailComponent;
    let fixture: ComponentFixture<SkillMasterDetailComponent>;
    const route = ({ data: of({ skillMaster: new SkillMaster(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [SkillMasterDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SkillMasterDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SkillMasterDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load skillMaster on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.skillMaster).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
