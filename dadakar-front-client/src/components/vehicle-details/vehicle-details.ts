import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Camera, CameraOptions } from '@ionic-native/camera';
import { NavParams, ViewController } from 'ionic-angular';

import { Vehicle } from '../../models/vehicle.model';

import { ImgService } from '../../services/image.service';

@Component({
    selector: 'vehicle-details',
    templateUrl: 'vehicle-details.html'
})

export class VehicleDetailsComponent {

    carRegistration: string;
    carRegistrationImg: string;
    isNew: boolean;
    photo: string;
    photoImg: string;
    vehicle: Vehicle;

    // validator
    isNotValid: boolean;
    nameNotValid: boolean;
    brandNotValid: boolean;
    modelNotValid: boolean;
    colorNotValid: boolean;
    registrationNumberNotValid: boolean;
    carRegistrationNotValid: boolean;

    constructor(private camera: Camera, private imgService: ImgService, private params: NavParams, private view: ViewController) {
        this.isNew = this.params.get('isNew');
        this.vehicle = this.params.get('vehicle')
        if(!this.isNew) this.getPhotos(this.vehicle);
        let values: any = {
            vehicleBrand: this.vehicle.brand,
            vehicleCarRegistration: this.vehicle.carRegistration,
            vehicleColor: this.vehicle.color,
            vehicleModel: this.vehicle.model,
            vehicleName: this.vehicle.name,
            vehicleRegistrationNumber: this.vehicle.registrationNumber
        }
        if(!this.isNew) this.validate(values);
        else this.isNotValid = true;
    }

    dismiss(cancel: boolean) {
        if(!cancel) {
            let data: any = {
                vehicle: this.vehicle,
                photo: this.photo,
                carRegistration: this.carRegistration
            }
            this.view.dismiss(data);
        } else {
            this.view.dismiss();
        }
    }

    getPhotos(vehicle: Vehicle) {
        if(vehicle.photo) {
            this.imgService.findByFileName(vehicle.photo).subscribe(data => {
                if(data) {
                    this.photo = data;
                    this.photoImg = 'data:image/jpeg;base64,' + data;
                } else {
                    this.photoImg = "/assets/img/vehicule.png";
                }
            });
        }
        if(vehicle.carRegistration) {
            this.imgService.findByFileName(vehicle.carRegistration).subscribe(data => {
                if(data) {
                    this.carRegistration = data;
                    this.carRegistrationImg = 'data:image/jpeg;base64,' + data;
                }
            });
        }
    }

    private getRandomName(): string {
        const char: string = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        var name: string = '';
        for(let i = 0; i < 10; i++) name += char.substr(Math.random() * 62,1);
        return name + ".jpeg";
    }

    takePhoto(form: NgForm, type: string, pictureSourceType: number) {
        const option: CameraOptions = {
            destinationType: 0,
            encodingType: this.camera.EncodingType.JPEG,
            mediaType: this.camera.MediaType.PICTURE,
            quality: 100,
            sourceType: pictureSourceType
        }
        this.camera.getPicture(option).then((value) => {
            if(type === 'photo') {
                this.photo = value;
                this.photoImg = 'data:image/jpeg;base64,' + value;
                this.vehicle.photo = this.getRandomName();
            }
            if(type === 'carRegistration') {
                this.carRegistration = value;
                this.carRegistrationImg = 'data:image/jpeg;base64,' + value;
                this.vehicle.carRegistration = this.getRandomName();
                form.value.vehicleCarRegistration = value;
                this.validate(form.value);
            }
        });
    }

    validate(values: any) {
        this.brandNotValid = !values.vehicleBrand;
        this.carRegistrationNotValid = !values.vehicleCarRegistration;
        this.colorNotValid = !values.vehicleColor;
        this.modelNotValid = !values.vehicleModel;
        this.nameNotValid = !values.vehicleName;
        this.registrationNumberNotValid = !values.vehicleRegistrationNumber;
        this.isNotValid = this.brandNotValid || this.carRegistrationNotValid || this.colorNotValid || this.modelNotValid || this.nameNotValid || this.registrationNumberNotValid;
    }

}
