import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { ProjectRoleDtlUpdateComponent } from 'app/entities/project-role-dtl/project-role-dtl-update.component';
import { ProjectRoleDtlService } from 'app/entities/project-role-dtl/project-role-dtl.service';
import { ProjectRoleDtl } from 'app/shared/model/project-role-dtl.model';

describe('Component Tests', () => {
  describe('ProjectRoleDtl Management Update Component', () => {
    let comp: ProjectRoleDtlUpdateComponent;
    let fixture: ComponentFixture<ProjectRoleDtlUpdateComponent>;
    let service: ProjectRoleDtlService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectRoleDtlUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ProjectRoleDtlUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProjectRoleDtlUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProjectRoleDtlService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProjectRoleDtl(123);
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
        const entity = new ProjectRoleDtl();
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
