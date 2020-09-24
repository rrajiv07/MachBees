import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MachBeesTestModule } from '../../../test.module';
import { ProjectBudgetDtlComponent } from 'app/entities/project-budget-dtl/project-budget-dtl.component';
import { ProjectBudgetDtlService } from 'app/entities/project-budget-dtl/project-budget-dtl.service';
import { ProjectBudgetDtl } from 'app/shared/model/project-budget-dtl.model';

describe('Component Tests', () => {
  describe('ProjectBudgetDtl Management Component', () => {
    let comp: ProjectBudgetDtlComponent;
    let fixture: ComponentFixture<ProjectBudgetDtlComponent>;
    let service: ProjectBudgetDtlService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectBudgetDtlComponent],
      })
        .overrideTemplate(ProjectBudgetDtlComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProjectBudgetDtlComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProjectBudgetDtlService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ProjectBudgetDtl(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.projectBudgetDtls && comp.projectBudgetDtls[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
