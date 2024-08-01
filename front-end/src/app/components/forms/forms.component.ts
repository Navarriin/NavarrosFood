import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';

interface userDataInt {
  name: string;
  token: string;
}

@Component({
  selector: 'app-forms',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './forms.component.html',
  styleUrl: './forms.component.scss',
})
export class FormsComponent {
  protected login!: boolean;

  protected loginForm: FormGroup;
  protected registerForm: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private authService: AuthService
  ) {
    // Checando se existe param 'login' e setando true ou false para variavel
    this.route.params.subscribe((params) => {
      this.login = params['login'] === 'login';
    });

    this.loginForm = this.formBuilder.group({
      username: [
        null,
        [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(30),
        ],
      ],
      password: [
        null,
        [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(20),
        ],
      ],
    });

    this.registerForm = this.formBuilder.group({
      name: [
        null,
        [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(20),
        ],
      ],
      username: [
        null,
        [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(20),
        ],
      ],
      password: [
        null,
        [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(20),
        ],
      ],
      role: [null, Validators.required],
    });
  }

  loginSubmit(): void {
    this.authService.login(this.loginForm.value).subscribe({
      next: () => this.router.navigate(['menu']),
      error: (err) => console.log(err),
    });
  }

  registerSubmit(): void {
    this.authService.register(this.registerForm.value).subscribe({
      next: () => this.router.navigate(['menu']),
      error: (err) => console.log(err),
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
