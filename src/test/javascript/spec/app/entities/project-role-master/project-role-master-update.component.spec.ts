import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { ProjectRoleMasterUpdateComponent } from 'app/entities/project-role-master/project-role-master-update.component';
import { ProjectRoleMasterService } from 'app/entities/project-role-master/project-role-master.service';
import { ProjectRoleMaster } from 'app/shared/model/project-role-master.model';

describe('Component Tests', () => {
  describe('ProjectRoleMaster Management Update Component', () => {
    let comp: ProjectRoleMasterUpdateComponent;
    let fixture: ComponentFixture<ProjectRoleMasterUpdateComponent>;
    let service: ProjectRoleMasterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectRoleMasterUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ProjectRoleMasterUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProjectRoleMasterUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProjectRoleMasterService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProjectRoleMaster(123);
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
        const entity = new ProjectRoleMaster();
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
