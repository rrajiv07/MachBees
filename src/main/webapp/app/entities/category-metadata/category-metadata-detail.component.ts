import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICategoryMetadata } from 'app/shared/model/category-metadata.model';

@Component({
  selector: 'jhi-category-metadata-detail',
  templateUrl: './category-metadata-detail.component.html',
})
export class CategoryMetadataDetailComponent implements OnInit {
  categoryMetadata: ICategoryMetadata | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ categoryMetadata }) => (this.categoryMetadata = categoryMetadata));
  }

  previousState(): void {
    window.history.back();
  }
}
