import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { ProjectCategoryMasterUpdateComponent } from 'app/entities/project-category-master/project-category-master-update.component';
import { ProjectCategoryMasterService } from 'app/entities/project-category-master/project-category-master.service';
import { ProjectCategoryMaster } from 'app/shared/model/project-category-master.model';

describe('Component Tests', () => {
  describe('ProjectCategoryMaster Management Update Component', () => {
    let comp: ProjectCategoryMasterUpdateComponent;
    let fixture: ComponentFixture<ProjectCategoryMasterUpdateComponent>;
    let service: ProjectCategoryMasterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectCategoryMasterUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ProjectCategoryMasterUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProjectCategoryMasterUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProjectCategoryMasterService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProjectCategoryMaster(123);
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
        const entity = new ProjectCategoryMaster();
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
