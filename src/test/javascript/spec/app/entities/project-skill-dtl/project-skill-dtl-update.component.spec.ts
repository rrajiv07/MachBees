import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { ProjectSkillDtlUpdateComponent } from 'app/entities/project-skill-dtl/project-skill-dtl-update.component';
import { ProjectSkillDtlService } from 'app/entities/project-skill-dtl/project-skill-dtl.service';
import { ProjectSkillDtl } from 'app/shared/model/project-skill-dtl.model';

describe('Component Tests', () => {
  describe('ProjectSkillDtl Management Update Component', () => {
    let comp: ProjectSkillDtlUpdateComponent;
    let fixture: ComponentFixture<ProjectSkillDtlUpdateComponent>;
    let service: ProjectSkillDtlService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectSkillDtlUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ProjectSkillDtlUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProjectSkillDtlUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProjectSkillDtlService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProjectSkillDtl(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProjectSkillDtl();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
