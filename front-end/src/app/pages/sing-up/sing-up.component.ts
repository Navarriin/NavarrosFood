import { Component } from '@angular/core';
import { FormsComponent } from "../../components/forms/forms.component";

@Component({
  selector: 'app-sing-up',
  standalone: true,
  imports: [FormsComponent],
  templateUrl: './sing-up.component.html',
  styleUrl: './sing-up.component.scss',
})
export class SingUpComponent {}
