import { Directive } from '@angular/core';
import { Observable, ReplaySubject } from 'rxjs';

@Directive({})
export abstract class UploadComponent {
  coverImage: string = '';
  validImageTypes = ['image/gif', 'image/jpeg', 'image/png'];
  protected dragging = false;

  onFileSelected(event: Event | DragEvent) {
    let files;
    let target;
    if ('dataTransfer' in event) {
      files = event.dataTransfer?.files;
    } else {
      files = (event?.target as HTMLInputElement).files;
      target = event.target as HTMLInputElement;
    }
    if (!this.isValidImageType((files as FileList)[0]['type'])) {
      if (target) {
        target.value = '';
      }
      return;
    }
    this.convertFile((files as FileList)[0]).subscribe(base64 => {
      this.coverImage = base64;
    });
  }

  isValidImageType(fileType: string): boolean {
    return this.validImageTypes.includes(fileType);
  }

  convertFile(file: File): Observable<string> {
    const result = new ReplaySubject<string>(1);
    const reader = new FileReader();
    reader.readAsBinaryString(file);
    reader.onload = event => result.next(btoa(event?.target?.result?.toString() ?? ''));
    return result;
  }

  handleDragEnter() {
    this.dragging = true;
  }

  handleDragLeave() {
    this.dragging = false;
  }

  handleDrop(e: Event) {
    e.preventDefault();
    this.dragging = false;
    this.onFileSelected(e);
  }
}
