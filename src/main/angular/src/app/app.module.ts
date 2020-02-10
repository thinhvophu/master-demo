import { RouterModule, Routes } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER } from '@angular/core';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { TaskInfoComponent } from './task-info/task-info.component';
import { UserInfoComponent } from './user-info/user-info.component';
import { ConfigurationService } from './service/configuration.service';

const routes: Routes = [
  {path: 'user', component: UserInfoComponent},
  {path: 'task', component: TaskInfoComponent},
  {path: '', redirectTo: '/user',  pathMatch: 'full'}
];

@NgModule({
  declarations: [
    AppComponent,
    TaskInfoComponent,
    UserInfoComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    BrowserAnimationsModule,
    MatSidenavModule,
    MatMenuModule,
    MatButtonModule,
    RouterModule.forRoot(routes)
  ],
  providers: [
    ConfigurationService,
    {
      provide: APP_INITIALIZER,
      useFactory: (configService: ConfigurationService) => () => configService.loadConfig(),
      deps: [ConfigurationService],
      multi: true
    },
    {
      provide: LocationStrategy,
      useClass: HashLocationStrategy
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
