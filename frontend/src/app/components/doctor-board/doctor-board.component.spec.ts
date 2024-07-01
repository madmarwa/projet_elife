import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorBoardComponent } from './doctor-board.component';

describe('DoctorBoardComponent', () => {
  let component: DoctorBoardComponent;
  let fixture: ComponentFixture<DoctorBoardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DoctorBoardComponent]
    });
    fixture = TestBed.createComponent(DoctorBoardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
