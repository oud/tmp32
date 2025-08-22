import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { ITaux } from '../taux.model';
import { sampleWithFullData, sampleWithNewData, sampleWithPartialData, sampleWithRequiredData } from '../taux.test-samples';

import { TauxService } from './taux.service';

const requireRestSample: ITaux = {
  ...sampleWithRequiredData,
};

describe('Taux Service', () => {
  let service: TauxService;
  let httpMock: HttpTestingController;
  let expectedResult: ITaux | ITaux[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(TauxService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a Taux', () => {
      const taux = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(taux).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Taux', () => {
      const taux = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(taux).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Taux', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Taux', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Taux', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addTauxToCollectionIfMissing', () => {
      it('should add a Taux to an empty array', () => {
        const taux: ITaux = sampleWithRequiredData;
        expectedResult = service.addTauxToCollectionIfMissing([], taux);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(taux);
      });

      it('should not add a Taux to an array that contains it', () => {
        const taux: ITaux = sampleWithRequiredData;
        const tauxCollection: ITaux[] = [
          {
            ...taux,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addTauxToCollectionIfMissing(tauxCollection, taux);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Taux to an array that doesn't contain it", () => {
        const taux: ITaux = sampleWithRequiredData;
        const tauxCollection: ITaux[] = [sampleWithPartialData];
        expectedResult = service.addTauxToCollectionIfMissing(tauxCollection, taux);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(taux);
      });

      it('should add only unique Taux to an array', () => {
        const tauxArray: ITaux[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const tauxCollection: ITaux[] = [sampleWithRequiredData];
        expectedResult = service.addTauxToCollectionIfMissing(tauxCollection, ...tauxArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const taux: ITaux = sampleWithRequiredData;
        const taux2: ITaux = sampleWithPartialData;
        expectedResult = service.addTauxToCollectionIfMissing([], taux, taux2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(taux);
        expect(expectedResult).toContain(taux2);
      });

      it('should accept null and undefined values', () => {
        const taux: ITaux = sampleWithRequiredData;
        expectedResult = service.addTauxToCollectionIfMissing([], null, taux, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(taux);
      });

      it('should return initial array if no Taux is added', () => {
        const tauxCollection: ITaux[] = [sampleWithRequiredData];
        expectedResult = service.addTauxToCollectionIfMissing(tauxCollection, undefined, null);
        expect(expectedResult).toEqual(tauxCollection);
      });
    });

    describe('compareTaux', () => {
      it('should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareTaux(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('should return false if one entity is null', () => {
        const entity1 = { id: 25175 };
        const entity2 = null;

        const compareResult1 = service.compareTaux(entity1, entity2);
        const compareResult2 = service.compareTaux(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('should return false if primaryKey differs', () => {
        const entity1 = { id: 25175 };
        const entity2 = { id: 14513 };

        const compareResult1 = service.compareTaux(entity1, entity2);
        const compareResult2 = service.compareTaux(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('should return false if primaryKey matches', () => {
        const entity1 = { id: 25175 };
        const entity2 = { id: 25175 };

        const compareResult1 = service.compareTaux(entity1, entity2);
        const compareResult2 = service.compareTaux(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
