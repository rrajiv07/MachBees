import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MachBeesTestModule } from '../../../test.module';
import { SkillCategoryMasterComponent } from 'app/entities/skill-category-master/skill-category-master.component';
import { SkillCategoryMasterService } from 'app/entities/skill-category-master/skill-category-master.service';
import { SkillCategoryMaster } from 'app/shared/model/skill-category-master.model';

describe('Component Tests', () => {
  describe('SkillCategoryMaster Management Component', () => {
    let comp: SkillCategoryMasterComponent;
    let fixture: ComponentFixture<SkillCategoryMasterComponent>;
    let service: SkillCategoryMasterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [SkillCategoryMasterComponent],
      })
        .overrideTemplate(SkillCategoryMasterComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SkillCategoryMasterComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SkillCategoryMasterService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SkillCategoryMaster(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.skillCategoryMasters && comp.skillCategoryMasters[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
