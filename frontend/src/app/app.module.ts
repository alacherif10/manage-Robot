import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { HomeComponent } from './components/home/home.component';
import { RobotsComponent } from './components/robots/robots.component';
import { AddRobotComponent } from './components/add-robot/add-robot.component';
import { AddZoneComponent } from './components/add-zone/add-zone.component';
import { ZonesComponent } from './components/zones/zones.component';
import { SchedulersComponent } from './components/schedulers/schedulers.component';
import { AddSchedulersComponent } from './components/add-schedulers/add-schedulers.component';
import { AddMissionComponent } from './components/add-mission/add-mission.component';
import { MissionsComponent } from './components/missions/missions.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    SidebarComponent,
    HomeComponent,
    RobotsComponent,
    AddRobotComponent,
    AddZoneComponent,
    ZonesComponent,
    SchedulersComponent,
    AddSchedulersComponent,
    AddMissionComponent,
    MissionsComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
