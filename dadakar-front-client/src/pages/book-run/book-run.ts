import { Component } from '@angular/core';
import { MenuController, NavController, NavParams } from 'ionic-angular'

import { Rating } from '../../models/rating.model';
import { Run } from '../../models/run.model';
import { Search } from '../../models/search.model';
import { SubRun } from '../../models/subrun.model';
import { WayPoint } from '../../models/waypoint.model';

import { AuthProvider } from '../../providers/auth';

import { ImgService } from '../../services/image.service'

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
    wantedSubRun: SubRun;


  constructor(private authProvider: AuthProvider, private imgService: ImgService, private menu: MenuController, private params: NavParams) {
      this.menu.close();
      this.connected = this.params.get('connected');
      this.run = this.params.get('run');
      this.search = this.params.get('search');
      this.nbRatings = this.run.driver.ratings.length;
      this.getRatings();
      this.getAvatar(this.run.driver.photo);
      this.findWantedSubRun();
  }

  private findWantedSubRun(): void {
      for(let i = 0, j = this.run.subRuns.length; i < j; i++) {
          let startPlace: WayPoint = this.run.subRuns[i].startPlace;
          let endPlace: WayPoint = this.run.subRuns[i].endPlace;
          if(startPlace.address.town == this.search.startTown || startPlace.address.district == this.search.startDistrict || endPlace.address.town == this.search.endTown || endPlace.address.district == this.search.endDistrict) {
              this.wantedSubRun = this.run.subRuns[i];
              this.max = this.wantedSubRun.availableSeats;
              break;
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
      console.log(this.passengersLuggages);
  }

  viewRates() {

  }

}
