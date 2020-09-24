import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { ProjectCategoryMasterDetailComponent } from 'app/entities/project-category-master/project-category-master-detail.component';
import { ProjectCategoryMaster } from 'app/shared/model/project-category-master.model';

describe('Component Tests', () => {
  describe('ProjectCategoryMaster Management Detail Component', () => {
    let comp: ProjectCategoryMasterDetailComponent;
    let fixture: ComponentFixture<ProjectCategoryMasterDetailComponent>;
    const route = ({ data: of({ projectCategoryMaster: new ProjectCategoryMaster(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectCategoryMasterDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ProjectCategoryMasterDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProjectCategoryMasterDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load projectCategoryMaster on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.projectCategoryMaster).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
