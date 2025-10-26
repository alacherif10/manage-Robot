import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SchedulersComponent } from './schedulers.component';

describe('SchedulersComponent', () => {
  let component: SchedulersComponent;
  let fixture: ComponentFixture<SchedulersComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SchedulersComponent]
    });
    fixture = TestBed.createComponent(SchedulersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
