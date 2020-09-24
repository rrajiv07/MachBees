import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { MachBeesTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { ProjectRoleMasterDeleteDialogComponent } from 'app/entities/project-role-master/project-role-master-delete-dialog.component';
import { ProjectRoleMasterService } from 'app/entities/project-role-master/project-role-master.service';

describe('Component Tests', () => {
  describe('ProjectRoleMaster Management Delete Component', () => {
    let comp: ProjectRoleMasterDeleteDialogComponent;
    let fixture: ComponentFixture<ProjectRoleMasterDeleteDialogComponent>;
    let service: ProjectRoleMasterService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectRoleMasterDeleteDialogComponent],
      })
        .overrideTemplate(ProjectRoleMasterDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProjectRoleMasterDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProjectRoleMasterService);
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
