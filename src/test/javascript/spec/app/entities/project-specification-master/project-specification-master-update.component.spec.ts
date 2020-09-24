import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { ProjectSpecificationMasterUpdateComponent } from 'app/entities/project-specification-master/project-specification-master-update.component';
import { ProjectSpecificationMasterService } from 'app/entities/project-specification-master/project-specification-master.service';
import { ProjectSpecificationMaster } from 'app/shared/model/project-specification-master.model';

describe('Component Tests', () => {
  describe('ProjectSpecificationMaster Management Update Component', () => {
    let comp: ProjectSpecificationMasterUpdateComponent;
    let fixture: ComponentFixture<ProjectSpecificationMasterUpdateComponent>;
    let service: ProjectSpecificationMasterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectSpecificationMasterUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ProjectSpecificationMasterUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProjectSpecificationMasterUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProjectSpecificationMasterService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProjectSpecificationMaster(123);
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
        const entity = new ProjectSpecificationMaster();
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
