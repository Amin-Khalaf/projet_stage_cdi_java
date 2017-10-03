import { Component, OnInit } from '@angular/core';
import { ControlContainer, FormBuilder, FormGroup, FormControl, FormArray, Validators } from '@angular/forms';
import { NavController, NavParams, AlertController } from 'ionic-angular';

import { Address } from '../../models/address.model';

import { AddressService } from '../../services/address.service';

@Component({
  selector: 'page-run-create1',
  templateUrl: 'run-create1.html',
})
export class RunCreate1Page {
  formRunCreate1: FormGroup;
  towns: string[] = [];
  listDistricts: Address[][] = [[]];

  constructor(public navCtrl: NavController, public navParams: NavParams, private addressService : AddressService, private alertCtrl : AlertController, private fb: FormBuilder) {
  }

  ngOnInit(){
    let startAddress  = this.fb.group({
      town: null,
      district: null,
      place: null
    });
    let endAddress  = this.fb.group({
      town: null,
      district: null,
      place: null
    });
    let places = new FormArray([]);
    this.formRunCreate1 = this.fb.group({
      startAddress: startAddress,
      endAddress: endAddress,
      places: places
    });
    // let startTown = null;
    // let startDistrict = null;
    // let startPlace = null;
    // let endTown = null;
    // let endDistrict = null;
    // let endPlace = null;
    // let places = [];
    // this.formRunCreate1 = new FormGroup({
    //   'startTown': new FormControl(startTown, Validators.required),
    //   'startDistrict': new FormControl(startDistrict, Validators.required),
    //   'startPlace': new FormControl(startPlace, Validators.required),
    //   'endTown': new FormControl(endTown, Validators.required),
    //   'endDistrict': new FormControl(endDistrict, Validators.required),
    //   'endPlace': new FormControl(endPlace, Validators.required),
    //   'places': new FormArray(places)
    // });
    // this.findTowns();
  }

  onAddPlace(){
    (<FormArray>this.formRunCreate1.get('places')).push(this.fb.group({
      town: null,
      district: null,
      place: null
    }));
  }

  removePlace(index: number){
    (<FormArray>this.formRunCreate1.get('places')).removeAt(index);
  }


  findTowns(){
    let addresses : Address[] = [];
    this.addressService.findAll().subscribe((data) => {
      addresses = data;
      for (let address of addresses) {
        if (this.towns.indexOf(address.town) < 0)
          this.towns.push(address.town);
      }
    });
  }

  findDistricts(town: string, index : number){
    this.addressService.findByTown(town).subscribe(
      data =>{
        if (data.length == 0) {
          this.handleError('Aucun quartier trouvé');
        }
        if (this.listDistricts.length >= index + 1) {
          this.listDistricts[index] = data;
        } else {
          this.listDistricts.push(data);
        }
      },
      error => {
        this.handleError(error.json().error);
      }
    );
  }

  onSelectTown(town: string, index: number, values: any) {
    console.log(town);
    this.findDistricts(town, index);
  }

  private handleError(errorMessage: string){
    const alert = this.alertCtrl.create({
      title: 'An error occured',
      message: errorMessage,
      buttons: ['Ok']
    });
    alert.present();
  }

  onGoToStep2(){
    console.log(this.formRunCreate1);
  }

}
