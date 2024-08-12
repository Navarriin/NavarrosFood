import { Component } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import {
  FormGroup,
  Validators,
  ReactiveFormsModule,
  FormControl,
  NonNullableFormBuilder,
} from '@angular/forms';
import {
  RequestLogin,
  RequestRegister,
} from '../../interfaces/requests.interface';

@Component({
  selector: 'app-forms',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './forms.component.html',
  styleUrl: './forms.component.scss',
})
export class FormsComponent {
  protected login!: boolean;

  protected loginForm: FormGroup<{
    username: FormControl<string>;
    password: FormControl<string>;
  }>;
  protected registerForm: FormGroup<{
    name: FormControl<string>;
    username: FormControl<string>;
    password: FormControl<string>;
    role: FormControl<string>;
  }>;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private authService: AuthService,
    private formBuilder: NonNullableFormBuilder
  ) {
    // Checando se existe param 'login' e setando true ou false para variavel
    this.route.params.subscribe((params) => {
      this.login = params['login'] === 'login';
    });

    this.loginForm = this.formBuilder.group({
      username: [
        '',
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(30),
        ],
      ],
      password: [
        '',
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(20),
        ],
      ],
    });

    this.registerForm = this.formBuilder.group({
      name: [
        '',
        [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(20),
        ],
      ],
      username: [
        '',
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(20),
        ],
      ],
      password: [
        '',
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(20),
        ],
      ],
      role: ['', Validators.required],
    });
  }

  loginSubmit(): void {
    if (this.loginForm.valid) {
      const formValue = this.loginForm.value as RequestLogin;

      this.authService.login(formValue).subscribe({
        next: () => this.router.navigate(['menu']),
        error: (err) => console.log(err),
      });
    } else {
      this.loginForm.markAllAsTouched();
    }
  }

  registerSubmit(): void {
    if (this.registerForm.valid) {
      const formValue = this.registerForm.value as RequestRegister;

      this.authService.register(formValue).subscribe({
        next: () => this.router.navigate(['menu']),
        error: (err) => console.log(err),
      });
    } else {
      this.registerForm.markAllAsTouched();
    }
  }
}
