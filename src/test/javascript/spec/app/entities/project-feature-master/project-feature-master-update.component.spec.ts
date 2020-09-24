import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { ProjectFeatureMasterUpdateComponent } from 'app/entities/project-feature-master/project-feature-master-update.component';
import { ProjectFeatureMasterService } from 'app/entities/project-feature-master/project-feature-master.service';
import { ProjectFeatureMaster } from 'app/shared/model/project-feature-master.model';

describe('Component Tests', () => {
  describe('ProjectFeatureMaster Management Update Component', () => {
    let comp: ProjectFeatureMasterUpdateComponent;
    let fixture: ComponentFixture<ProjectFeatureMasterUpdateComponent>;
    let service: ProjectFeatureMasterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectFeatureMasterUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ProjectFeatureMasterUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProjectFeatureMasterUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProjectFeatureMasterService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProjectFeatureMaster(123);
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
        const entity = new ProjectFeatureMaster();
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
