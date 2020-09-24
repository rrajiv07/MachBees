import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { SkillCategoryMasterService } from 'app/entities/skill-category-master/skill-category-master.service';
import { ISkillCategoryMaster, SkillCategoryMaster } from 'app/shared/model/skill-category-master.model';

describe('Service Tests', () => {
  describe('SkillCategoryMaster Service', () => {
    let injector: TestBed;
    let service: SkillCategoryMasterService;
    let httpMock: HttpTestingController;
    let elemDefault: ISkillCategoryMaster;
    let expectedResult: ISkillCategoryMaster | ISkillCategoryMaster[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(SkillCategoryMasterService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new SkillCategoryMaster(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a SkillCategoryMaster', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new SkillCategoryMaster()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a SkillCategoryMaster', () => {
        const returnedFromService = Object.assign(
          {
            categoryCode: 'BBBBBB',
            categoryName: 'BBBBBB',
            categoryDescription: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of SkillCategoryMaster', () => {
        const returnedFromService = Object.assign(
          {
            categoryCode: 'BBBBBB',
            categoryName: 'BBBBBB',
            categoryDescription: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a SkillCategoryMaster', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
