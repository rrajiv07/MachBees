import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { MachBeesTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { SkillCategoryMasterDeleteDialogComponent } from 'app/entities/skill-category-master/skill-category-master-delete-dialog.component';
import { SkillCategoryMasterService } from 'app/entities/skill-category-master/skill-category-master.service';

describe('Component Tests', () => {
  describe('SkillCategoryMaster Management Delete Component', () => {
    let comp: SkillCategoryMasterDeleteDialogComponent;
    let fixture: ComponentFixture<SkillCategoryMasterDeleteDialogComponent>;
    let service: SkillCategoryMasterService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [SkillCategoryMasterDeleteDialogComponent],
      })
        .overrideTemplate(SkillCategoryMasterDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SkillCategoryMasterDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SkillCategoryMasterService);
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
