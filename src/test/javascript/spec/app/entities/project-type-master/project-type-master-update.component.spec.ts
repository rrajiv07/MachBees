import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { ProjectTypeMasterUpdateComponent } from 'app/entities/project-type-master/project-type-master-update.component';
import { ProjectTypeMasterService } from 'app/entities/project-type-master/project-type-master.service';
import { ProjectTypeMaster } from 'app/shared/model/project-type-master.model';

describe('Component Tests', () => {
  describe('ProjectTypeMaster Management Update Component', () => {
    let comp: ProjectTypeMasterUpdateComponent;
    let fixture: ComponentFixture<ProjectTypeMasterUpdateComponent>;
    let service: ProjectTypeMasterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectTypeMasterUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ProjectTypeMasterUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProjectTypeMasterUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProjectTypeMasterService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProjectTypeMaster(123);
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
        const entity = new ProjectTypeMaster();
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
