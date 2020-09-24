import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { ProjectBudgetDtlUpdateComponent } from 'app/entities/project-budget-dtl/project-budget-dtl-update.component';
import { ProjectBudgetDtlService } from 'app/entities/project-budget-dtl/project-budget-dtl.service';
import { ProjectBudgetDtl } from 'app/shared/model/project-budget-dtl.model';

describe('Component Tests', () => {
  describe('ProjectBudgetDtl Management Update Component', () => {
    let comp: ProjectBudgetDtlUpdateComponent;
    let fixture: ComponentFixture<ProjectBudgetDtlUpdateComponent>;
    let service: ProjectBudgetDtlService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectBudgetDtlUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ProjectBudgetDtlUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProjectBudgetDtlUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProjectBudgetDtlService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProjectBudgetDtl(123);
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
        const entity = new ProjectBudgetDtl();
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
