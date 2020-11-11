import { TokenService } from './services/token.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  title = 'tas-web';

  public loadToken: boolean;
  public errorMessage: string;

  constructor(private tokenService: TokenService) {
    this.loadToken = true;
    this.errorMessage = '';

    this.tokenService
      .getAccessToken()
      .subscribe(
        (result) => {
          sessionStorage.setItem('token', result['token']);
        },
        (error) => {
          this.errorMessage = error.message;
        }
      )
      .add(() => {
        this.loadToken = false;
        this.errorMessage = '';
      });
  }
}
