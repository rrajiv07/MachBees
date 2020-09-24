import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { ProjectTypeMasterDetailComponent } from 'app/entities/project-type-master/project-type-master-detail.component';
import { ProjectTypeMaster } from 'app/shared/model/project-type-master.model';

describe('Component Tests', () => {
  describe('ProjectTypeMaster Management Detail Component', () => {
    let comp: ProjectTypeMasterDetailComponent;
    let fixture: ComponentFixture<ProjectTypeMasterDetailComponent>;
    const route = ({ data: of({ projectTypeMaster: new ProjectTypeMaster(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectTypeMasterDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ProjectTypeMasterDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProjectTypeMasterDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load projectTypeMaster on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.projectTypeMaster).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
