import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { MachBeesTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { ProjectCategoryMasterDeleteDialogComponent } from 'app/entities/project-category-master/project-category-master-delete-dialog.component';
import { ProjectCategoryMasterService } from 'app/entities/project-category-master/project-category-master.service';

describe('Component Tests', () => {
  describe('ProjectCategoryMaster Management Delete Component', () => {
    let comp: ProjectCategoryMasterDeleteDialogComponent;
    let fixture: ComponentFixture<ProjectCategoryMasterDeleteDialogComponent>;
    let service: ProjectCategoryMasterService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectCategoryMasterDeleteDialogComponent],
      })
        .overrideTemplate(ProjectCategoryMasterDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProjectCategoryMasterDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProjectCategoryMasterService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
