import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { ProjectHdrDetailComponent } from 'app/entities/project-hdr/project-hdr-detail.component';
import { ProjectHdr } from 'app/shared/model/project-hdr.model';

describe('Component Tests', () => {
  describe('ProjectHdr Management Detail Component', () => {
    let comp: ProjectHdrDetailComponent;
    let fixture: ComponentFixture<ProjectHdrDetailComponent>;
    const route = ({ data: of({ projectHdr: new ProjectHdr(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectHdrDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ProjectHdrDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProjectHdrDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load projectHdr on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.projectHdr).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
