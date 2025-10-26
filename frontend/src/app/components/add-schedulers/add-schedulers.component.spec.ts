import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSchedulersComponent } from './add-schedulers.component';

describe('AddSchedulersComponent', () => {
  let component: AddSchedulersComponent;
  let fixture: ComponentFixture<AddSchedulersComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddSchedulersComponent]
    });
    fixture = TestBed.createComponent(AddSchedulersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
