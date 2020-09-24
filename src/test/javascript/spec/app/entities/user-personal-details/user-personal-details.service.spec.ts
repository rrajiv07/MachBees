import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { UserPersonalDetailsService } from 'app/entities/user-personal-details/user-personal-details.service';
import { IUserPersonalDetails, UserPersonalDetails } from 'app/shared/model/user-personal-details.model';

describe('Service Tests', () => {
  describe('UserPersonalDetails Service', () => {
    let injector: TestBed;
    let service: UserPersonalDetailsService;
    let httpMock: HttpTestingController;
    let elemDefault: IUserPersonalDetails;
    let expectedResult: IUserPersonalDetails | IUserPersonalDetails[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(UserPersonalDetailsService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new UserPersonalDetails(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a UserPersonalDetails', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new UserPersonalDetails()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a UserPersonalDetails', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            surname: 'BBBBBB',
            address: 'BBBBBB',
            country: 'BBBBBB',
            mobile: 'BBBBBB',
            linkedin: 'BBBBBB',
            twitter: 'BBBBBB',
            skype: 'BBBBBB',
            selfPresentation: 'BBBBBB',
            virtualcv: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of UserPersonalDetails', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            surname: 'BBBBBB',
            address: 'BBBBBB',
            country: 'BBBBBB',
            mobile: 'BBBBBB',
            linkedin: 'BBBBBB',
            twitter: 'BBBBBB',
            skype: 'BBBBBB',
            selfPresentation: 'BBBBBB',
            virtualcv: 'BBBBBB',
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

      it('should delete a UserPersonalDetails', () => {
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
