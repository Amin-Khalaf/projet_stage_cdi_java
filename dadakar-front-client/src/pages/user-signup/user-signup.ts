import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Camera } from '@ionic-native/camera';

import { ImgService } from '../../services/image.service';
import { Image } from '../../models/image.model';

@Component({
  selector: 'page-user-signup',
  templateUrl: 'user-signup.html',
})
export class UserSignupPage {

    img: string = '';
    hasImg: boolean = false;
    imgToSend: Image;

    constructor(private camera: Camera, private imgService: ImgService) {}

    signup(values: any) {
        console.log("signup");
    }

    takePhoto(form: NgForm) {
        this.camera.getPicture({destinationType: 2}).then((value) => {
            this.hasImg = true;
            this.img = 'data:image/jpeg;base64,' + value;
            this.imgToSend = {
                image: value,
                name: 'testImageFromIonic.jpeg',
                type: "image/jpeg"
            };
            console.log('before');
            this.imgService.add(this.imgToSend);
            console.log('after');
            //form.controls['photo'].setValue(imageData);
        });
    }

    takePicture(form: NgForm) {
        this.camera.getPicture({sourceType: 0, destinationType: 2}).then((imageData) => {
            console.log(imageData)
            //form.controls['img'].setValue('data:image/jpeg;base64,' + imageData);
            //form.controls['photo'].setValue(imageData);
        });
    }

}
