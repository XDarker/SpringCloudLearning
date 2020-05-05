import {Component} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'aaa';

  authenticated = false;

  constructor(private http: HttpClient) {
  }

  credentials = {
    username: 'XSJ',
    password: '123'
  };

  login() {
    this.http.post('login', this.credentials).subscribe(() => {
      this.authenticated = true;
    },() =>{
      alert("login fail")
    })
  }
}
