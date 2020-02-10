import { Component, OnInit, Input, ChangeDetectionStrategy  } from '@angular/core';
import { Configuration } from '../model/configuration';
import { ConfigurationService } from '../service/configuration.service';

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class UserInfoComponent implements OnInit {

  config: Configuration;

  constructor(private configurationService: ConfigurationService) {
    console.log ('user init');
    this.config = this.configurationService.configuration;
  }

  ngOnInit() {
  }

  get appVersion() {
    return this.config.appVersion;
  }
}
