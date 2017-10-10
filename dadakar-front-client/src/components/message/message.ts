import { Component } from '@angular/core';
import { NavParams, ToastController, ViewController } from 'ionic-angular';
import { LocalDateTime } from 'js-joda';

import { Message } from '../../models/message.model';

import { MessageService } from '../../services/message.service';

@Component({
    selector: 'message',
    templateUrl: 'message.html'
})
export class MessageComponent {

    message: Message;
    response: any = {
        msgId:'',
        horo: '',
        message: '',
        object: '',
        reveiverId: '',
        seen: false,
        senderId: ''
    }

    constructor(private msgService: MessageService, private params: NavParams, private toast: ToastController, private view: ViewController) {
        this.message = this.params.get("message");
        this.response.senderId = this.message.reveiverId;
        this.response.reveiverId = this.message.senderId;
        this.response.object = 'Re : ' + this.message.object;
        this.response.message = '\n\nMessage d\'origine :\n' + this.message.message;
    }

    dismiss() {
        this.view.dismiss();
    }

    sendMessage() {
        this.response.horo = LocalDateTime.now().year() + "-" + LocalDateTime.now().monthValue() + "-" + LocalDateTime.now().dayOfMonth() + " " + LocalDateTime.now().hour() + ":" + LocalDateTime.now().minute();
        this.msgService.add(this.response).subscribe(() => {
            const toast = this.toast.create({
                message: 'le message à bien été envoyé',
                duration: 5000,
                position: 'middle'
            });
            toast.present();
            this.view.dismiss();
        });
    }

}
