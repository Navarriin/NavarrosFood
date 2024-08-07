import { Injectable, Signal, signal } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class StatesService {
  private authState = signal<boolean>(false);
  private nameSubject = signal<string>('');
  private selectedCardId = signal<number | null>(null);

  constructor() {
    this.loadStateFromLocalStorage();
  }

  getAuthState(): Signal<boolean> {
    return this.authState.asReadonly();
  }

  setAuthState(state: boolean): void {
    this.authState.set(state);
  }

  getNameSubject(): Signal<string> {
    return this.nameSubject.asReadonly();
  }

  setNameSubject(name: string): void {
    this.nameSubject.set(name);
  }

  getSelectedCardId(): Signal<number | null> {
    return this.selectedCardId.asReadonly();
  }

  setSelectedCardId(num: number): void {
    this.selectedCardId.set(num);
  }

  private loadStateFromLocalStorage(): void {
    const userData: string | null = localStorage.getItem('userData');

    if (userData) {
      const parsedData: { name: string; token: string } = JSON.parse(userData);

      this.setAuthState(true);
      this.setNameSubject(parsedData.name);
    }
  }
}
