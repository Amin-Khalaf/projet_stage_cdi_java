import { Component, OnInit, ChangeDetectorRef, Output, EventEmitter } from '@angular/core';
import { FormGroup, ControlContainer } from '@angular/forms';
import { AlertController } from 'ionic-angular';

import { Address } from '../../models/address.model';
import { AddressService } from '../../services/address.service';

@Component({
  selector: '[formGroup] app-address-form, [formGroupName] app-address-form',
  templateUrl: 'address-form.html'
})
export class AddressForm implements OnInit {
  addressForm: FormGroup;
  // valid = false;
  towns: string[] = [];
  listDistricts: Address[][] = [[]];

  constructor(private addressService: AddressService, private alertCtrl: AlertController, private controlCtnr: ControlContainer, private cdr: ChangeDetectorRef) {
  }

  @Output() sendChange = new EventEmitter();

  ngOnInit() {
    this.addressForm = <FormGroup>this.controlCtnr.control;
    this.findTowns();
    this.cdr.detectChanges();
  }

  findTowns() {
    let addresses: Address[] = [];
    this.addressService.findAll().subscribe((data) => {
      addresses = data;
      for (let address of addresses) {
        if (this.towns.indexOf(address.town) < 0)
          this.towns.push(address.town);
      }
    });
  }

  findDistricts(town: string, index: number) {
    this.addressService.findByTown(town).subscribe(
      data => {
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
    this.findDistricts(town, index);
  }

  private handleError(errorMessage: string) {
    const alert = this.alertCtrl.create({
      title: 'An error occured',
      message: errorMessage,
      buttons: ['Ok']
    });
    alert.present();
  }

  sendOnChange() {
    this.sendChange.emit();
  }

}
