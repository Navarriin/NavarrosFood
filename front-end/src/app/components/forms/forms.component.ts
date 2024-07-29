import { LoginService } from '../../services/login.service';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import {
  RequestLogin,
  RequestRegister,
} from '../../interfaces/requests.interface';

interface userDataInt {
  name: string;
  token: string;
}


@Component({
  selector: 'app-forms',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './forms.component.html',
  styleUrl: './forms.component.scss'
})
export class FormsComponent implements OnInit {
  login!: boolean;

  // Form Login
  loginForm = new FormGroup({
    username: new FormControl<string>(''),
    password: new FormControl<string>(''),
  } as { [key in keyof RequestLogin]: FormControl<string> });

  // Form Register
  registerForm = new FormGroup({
    name: new FormControl<string>(''),
    username: new FormControl<string>(''),
    password: new FormControl<string>(''),
    role: new FormControl<string>(''),
  } as { [key in keyof RequestRegister]: FormControl<string> });

  constructor(
    private route: ActivatedRoute,
    private loginService: LoginService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.login = params['login'] === 'login';
    });
  }

  loginSubmit(): void {
    const formValue = this.loginForm.value as RequestLogin;
    this.loginService.login(formValue).subscribe((value) => {
      this.saveStorage(value);
    });
  }

  registerSubmit(): void {
    const formValue = this.registerForm.value as RequestRegister;
    this.loginService.register(formValue).subscribe((value) => {
      this.saveStorage(value);
    });
  }

  private saveStorage(data: userDataInt): void {
    const userData: userDataInt = {
      name: data.name,
      token: data.token,
    };
    localStorage.setItem('userData', JSON.stringify(userData));
  }
}
