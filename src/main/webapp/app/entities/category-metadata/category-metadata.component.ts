import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICategoryMetadata } from 'app/shared/model/category-metadata.model';
import { CategoryMetadataService } from './category-metadata.service';
import { CategoryMetadataDeleteDialogComponent } from './category-metadata-delete-dialog.component';

@Component({
  selector: 'jhi-category-metadata',
  templateUrl: './category-metadata.component.html',
})
export class CategoryMetadataComponent implements OnInit, OnDestroy {
  categoryMetadata?: ICategoryMetadata[];
  eventSubscriber?: Subscription;

  constructor(
    protected categoryMetadataService: CategoryMetadataService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.categoryMetadataService.query().subscribe((res: HttpResponse<ICategoryMetadata[]>) => (this.categoryMetadata = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCategoryMetadata();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICategoryMetadata): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCategoryMetadata(): void {
    this.eventSubscriber = this.eventManager.subscribe('categoryMetadataListModification', () => this.loadAll());
  }

  delete(categoryMetadata: ICategoryMetadata): void {
    const modalRef = this.modalService.open(CategoryMetadataDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.categoryMetadata = categoryMetadata;
  }
}
