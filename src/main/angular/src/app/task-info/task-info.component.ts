import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { Configuration } from '../model/configuration';
import { ConfigurationService } from '../service/configuration.service';

@Component({
  selector: 'app-task-info',
  templateUrl: './task-info.component.html',
  styleUrls: ['./task-info.component.scss']
})
export class TaskInfoComponent implements OnInit {

  config: Configuration;

  constructor(private configurationService: ConfigurationService) {
    console.log ('task init');
    this.config = this.configurationService.configuration;
  }

  ngOnInit() {
  }

}
