import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { ProjectRoleMasterDetailComponent } from 'app/entities/project-role-master/project-role-master-detail.component';
import { ProjectRoleMaster } from 'app/shared/model/project-role-master.model';

describe('Component Tests', () => {
  describe('ProjectRoleMaster Management Detail Component', () => {
    let comp: ProjectRoleMasterDetailComponent;
    let fixture: ComponentFixture<ProjectRoleMasterDetailComponent>;
    const route = ({ data: of({ projectRoleMaster: new ProjectRoleMaster(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectRoleMasterDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ProjectRoleMasterDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProjectRoleMasterDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load projectRoleMaster on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.projectRoleMaster).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
