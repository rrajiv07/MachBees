import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { SkillCategoryMasterDetailComponent } from 'app/entities/skill-category-master/skill-category-master-detail.component';
import { SkillCategoryMaster } from 'app/shared/model/skill-category-master.model';

describe('Component Tests', () => {
  describe('SkillCategoryMaster Management Detail Component', () => {
    let comp: SkillCategoryMasterDetailComponent;
    let fixture: ComponentFixture<SkillCategoryMasterDetailComponent>;
    const route = ({ data: of({ skillCategoryMaster: new SkillCategoryMaster(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [SkillCategoryMasterDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SkillCategoryMasterDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SkillCategoryMasterDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load skillCategoryMaster on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.skillCategoryMaster).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
