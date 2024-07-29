import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-error',
  standalone: true,
  imports: [],
  templateUrl: './error.component.html',
  styleUrl: './error.component.scss'
})
export class ErrorComponent implements OnInit {
  constructor(private route: Router) {}
  
  ngOnInit() {
    setTimeout(() => {
      this.route.navigate(['menu']);
    }, 3 * 1000)
  }
}
