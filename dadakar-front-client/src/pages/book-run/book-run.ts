import { Component } from '@angular/core';
import { MenuController, NavController, NavParams, ToastController } from 'ionic-angular'

import { Luggage } from '../../models/enums/luggage.model';
import { Passenger } from '../../models/passenger.model';
import { Rating } from '../../models/rating.model';
import { ResState } from '../../models/enums/resstate.model';
import { Run } from '../../models/run.model';
import { Search } from '../../models/search.model';
import { SubRun } from '../../models/subrun.model';
import { User } from '../../models/user.model';
import { WayPoint } from '../../models/waypoint.model';

import { ViewRatingsPage } from '../../pages/view-ratings/view-ratings';

import { AuthProvider } from '../../providers/auth';

import { ImgService } from '../../services/image.service';
import { RunService } from '../../services/run.service';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'page-book-run',
  templateUrl: 'book-run.html',
})
export class BookRunPage {

    activeMenu: string;
    connected: boolean;
    generalConditionsAccepted: boolean;
    max: number;
    nbPassengers: number[] = [1];
    nbPlaces: number = 1;
    nbRatings: number;
    note: number = 0;
    passengersLuggages: string[] = [];
    photo: string;
    run: Run;
    search: Search;
    subRunIndexes: number[] = [];
    user: User;
    wantedSubRuns: SubRun[] = [];


  constructor(private authProvider: AuthProvider, private imgService: ImgService, private menu: MenuController, private nav: NavController, private params: NavParams, private runService: RunService, private toast: ToastController, private userService: UserService) {
      this.menu.close();
      this.connected = this.params.get('connected');
      this.run = this.params.get('run');
      this.search = this.params.get('search');
      this.nbRatings = this.run.driver.ratings.length;
      this.getNbPassenger();
      this.getUser();
      this.getRatings();
      this.getAvatar(this.run.driver.photo);
      this.findWantedSubRuns();
  }

  private findWantedSubRuns(): void {
      let tempStartingPoint: WayPoint;
      for(let i = 0, j = this.run.subRuns.length; i < j; i++) {
          // find first entry point
          if(this.run.subRuns[i].startPlace.address.town == this.search.startTown && this.run.subRuns[i].startPlace.address.district == this.search.startDistrict) {
              // add starting point
              this.wantedSubRuns.push(this.run.subRuns[i]);
              this.subRunIndexes.push(i);
              this.max = this.run.subRuns[i].availableSeats;
              // if there is only one subrun => break otherwhise change startPlace
              if(this.run.subRuns[i].endPlace.address.town == this.search.endTown && this.run.subRuns[i].endPlace.address.district == this.search.endDistrict) {
                  break;
              } else {
                  tempStartingPoint = this.run.subRuns[i].endPlace;
              }
          } else if(tempStartingPoint != null && tempStartingPoint.address.town == this.run.subRuns[i].startPlace.address.town && tempStartingPoint.address.district == this.run.subRuns[i].startPlace.address.district) {
              //add step
              this.wantedSubRuns.push(this.run.subRuns[i]);
              this.subRunIndexes.push(i);
              if(this.run.subRuns[i].availableSeats < this.max) this.max = this.run.subRuns[i].availableSeats;
              // if it is the last subrun => break otherwhise change startPlace
              if(this.run.subRuns[i].endPlace.address.town == this.search.endTown && this.run.subRuns[i].endPlace.address.district == this.search.endDistrict) {
                  break;
              } else {
                  tempStartingPoint = this.run.subRuns[i].endPlace;
              }
          }
      }
  }

  private getAvatar(fileName: string): void {

      if(this.connected && fileName != '') {
          this.imgService.findByFileName(fileName).subscribe(data => {
              if(data) {
                  this.photo = 'data:image/jpeg;base64,' + data;
              } else {
                this.photo = '/assets/img/avatar.png';
              }
          });
      } else {
        this.photo = '/assets/img/avatar.png';
      }
  }

  private getRatings(): void {
      this.note = 0;
      let ratingValue: number = 0;
      let ratings: Rating[] = this.run.driver.ratings;
      this.nbRatings = ratings.length;
      for(let i = 0, j = ratings.length; i < j; i++) {
          ratingValue += ratings[i].value;
      }
      this.note = ratingValue / ratings.length;
  }

  private getUser() {
      this.authProvider.authUser.subscribe(jwt => {
          let accountId: string = jwt.accountDTO.accountId;
          this.userService.findByAccountId(accountId).subscribe(data => {
              this.user = data;
          });
      });
  }

  private menuBannedOrDeletedActive(): void {
      this.activeMenu = 'menu-banned';
      this.menu.enable(true, 'menu-banned');
      this.menu.enable(false, 'menu-not-connected');
      this.menu.enable(false, 'menu-connected');
  }

  private menuConnectedActive(): void {
      this.activeMenu = 'menu-connected';
      this.menu.enable(false, 'menu-banned');
      this.menu.enable(false, 'menu-not-connected');
      this.menu.enable(true, 'menu-connected');
  }

  private menuNotConnectedActive(): void {
      this.activeMenu = 'menu-not-connected';
      this.menu.enable(false, 'menu-banned');
      this.menu.enable(true, 'menu-not-connected');
      this.menu.enable(false, 'menu-connected');
  }

getNbPassenger() {
    this.nbPassengers = [];
    for(let i = 0; i < this.nbPlaces; i++) {
        this.nbPassengers.push(i + 1);
        this.passengersLuggages[i] = "PETIT";
        while(this.nbPlaces < this.passengersLuggages.length) this.passengersLuggages.pop();
    }
}

  ionViewWillEnter() {
      this.authProvider.authUser.subscribe(jwt => {
          if(jwt) {
              if(jwt.accountDTO.banned || jwt.accountDTO.deleted) this.menuBannedOrDeletedActive();
              else this.menuConnectedActive();
          } else {
              this.menuNotConnectedActive();
          }
      });
  }

  reserver() {
      // setting full run price based on user subRuns price
      let price: number = 0;
      for(let i = 0, j = this.wantedSubRuns.length; i < j; i++) {
          price += this.wantedSubRuns[i].price;
      }
      // setting passengers
      let passengers: Passenger[] = [];
      for(let i = 0; i < this.nbPlaces; i++) {
          let passenger: Passenger = {
              luggageType: this.passengersLuggages[i] == 'PETIT' ? 0 : this.passengersLuggages[i] == 'MOYEN' ? 1 : 2,
              passengerId: '',
              price: price,
              reservationState: ResState.PENDING,
              user: this.user
          }
          passengers.push(passenger);
      }
      // adding passengers to subRuns
      for(let i = 0, j = this.subRunIndexes.length; i < j; i++) {
          for(let k = 0, l = passengers.length; k < l; k++) {
              this.run.subRuns[this.subRunIndexes[i]].passengers.push(passengers[k]);
          }
      }
      this.runService.update(this.run).subscribe(() => {
        const toast = this.toast.create({
            message: 'la réservation à été effectué avec succès.',
            duration: 5000,
            position: 'middle'
        });
        toast.present();
        this.nav.popToRoot();
      });
  }

  viewRates() {
      this.nav.push(ViewRatingsPage, {
          user: this.run.driver
      });
  }

}
