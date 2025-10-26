import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from './components/home/home.component';
import { RobotsComponent } from './components/robots/robots.component';
import { AddRobotComponent } from './components/add-robot/add-robot.component';
import { ZonesComponent } from './components/zones/zones.component';
import { AddZoneComponent } from './components/add-zone/add-zone.component';
import { SchedulersComponent } from './components/schedulers/schedulers.component';
import { AddSchedulersComponent } from './components/add-schedulers/add-schedulers.component';
import { MissionsComponent } from './components/missions/missions.component';
import { AddMissionComponent } from './components/add-mission/add-mission.component';

const routes: Routes = [
   {path: '', redirectTo:'login', pathMatch:'full'},  // ← Fixed: removed / from redirectTo
    {path:'login', component: LoginComponent },
    {path: 'register', component : RegisterComponent},
    {path : 'home', component : HomeComponent},
    {path : 'robots', component: RobotsComponent},
    {path : 'add-robot', component: AddRobotComponent},
    {path : 'zones', component: ZonesComponent},
    {path : 'add-zone', component: AddZoneComponent},
    {path : 'schedulers', component: SchedulersComponent},
    {path : 'add-scheduler', component: AddSchedulersComponent},
    {path : 'add-mission', component: AddMissionComponent},  // ← Fixed: removed /
    {path : 'missions', component: MissionsComponent},       // ← Fixed: removed /
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 

}