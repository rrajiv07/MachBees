import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MachBeesTestModule } from '../../../test.module';
import { SkillMasterComponent } from 'app/entities/skill-master/skill-master.component';
import { SkillMasterService } from 'app/entities/skill-master/skill-master.service';
import { SkillMaster } from 'app/shared/model/skill-master.model';

describe('Component Tests', () => {
  describe('SkillMaster Management Component', () => {
    let comp: SkillMasterComponent;
    let fixture: ComponentFixture<SkillMasterComponent>;
    let service: SkillMasterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [SkillMasterComponent],
      })
        .overrideTemplate(SkillMasterComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SkillMasterComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SkillMasterService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SkillMaster(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.skillMasters && comp.skillMasters[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
