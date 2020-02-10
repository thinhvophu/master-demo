import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Configuration } from '../model/configuration'
@Injectable({
  providedIn: 'root'
})
export class ConfigurationService {

  private config: Configuration;

  constructor(private http: HttpClient) { }

  loadConfig(): Promise<any> {
    console.log ('fetching config...');
    return this.http.get('/api/config')
      .toPromise()
      .then((res: any) => this.config = <Configuration>res.data)
      .catch(() => console.log('Failed'));
  }

  get configuration(): Configuration {
    return this.config;
  }
}
