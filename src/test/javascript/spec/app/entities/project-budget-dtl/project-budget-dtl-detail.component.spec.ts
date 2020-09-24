import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { ProjectBudgetDtlDetailComponent } from 'app/entities/project-budget-dtl/project-budget-dtl-detail.component';
import { ProjectBudgetDtl } from 'app/shared/model/project-budget-dtl.model';

describe('Component Tests', () => {
  describe('ProjectBudgetDtl Management Detail Component', () => {
    let comp: ProjectBudgetDtlDetailComponent;
    let fixture: ComponentFixture<ProjectBudgetDtlDetailComponent>;
    const route = ({ data: of({ projectBudgetDtl: new ProjectBudgetDtl(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectBudgetDtlDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ProjectBudgetDtlDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProjectBudgetDtlDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load projectBudgetDtl on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.projectBudgetDtl).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
