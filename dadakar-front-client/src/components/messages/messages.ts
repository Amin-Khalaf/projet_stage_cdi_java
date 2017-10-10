import { Component } from '@angular/core';
import { ModalController, NavParams, ViewController, Refresher } from 'ionic-angular';

import { MessageComponent } from '../../components/message/message';

import { Message } from '../../models/message.model';

import { MessageService } from '../../services/message.service';

@Component({
    selector: 'messages',
    templateUrl: 'messages.html'
})
export class MessagesComponent {

    messageType: string = 'all';
    messages: Message[] = [];
    messagesRead: Message[] = [];
    messagesUnread: Message[] = [];
    userId: string;

    constructor(private msgService: MessageService, private modal: ModalController, private params: NavParams, private view: ViewController) {
        this.userId = this.params.get("userId");
        this.setMessages();
    }

    dismiss() {
        this.view.dismiss()
    }

    getMessages(messageType): Message[] {
        switch(messageType) {
            case 'all':
                return this.messages;
            case 'read':
                return this.messagesRead;
            case 'unread':
                return this.messagesUnread;
        }
    }

    refresh(refresher: Refresher) {
        this.msgService.findByReceiverId(this.userId).subscribe(data => {
            if(data) {
                this.messages = data;
                this.messagesRead = [];
                this.messagesUnread = [];
                for(let i = 0, j = this.messages.length; i < j; i++) {
                    if(this.messages[i].seen) this.messagesRead.push(this.messages[i])
                    else this.messagesUnread.push(this.messages[i]);
                }
                //sorting message by descending date
                this.messages = this.messages.sort((a: Message, b: Message) => {
                    if(a.horo < b.horo) return 1;
                    else if (a.horo > b.horo) return -1;
                    else return 0;
                });
                this.messagesRead = this.messagesRead.sort((a: Message, b: Message) => {
                    if(a.horo < b.horo) return 1;
                    else if (a.horo > b.horo) return -1;
                    else return 0;
                });
                this.messagesUnread = this.messagesUnread.sort((a: Message, b: Message) => {
                    if(a.horo < b.horo) return 1;
                    else if (a.horo > b.horo) return -1;
                    else return 0;
                });
            }
            refresher.complete();
        });
    }

    setMessages() {
        this.msgService.findByReceiverId(this.userId).subscribe(data => {
            if(data) {
                this.messages = data;
                this.messagesRead = [];
                this.messagesUnread = [];
                for(let i = 0, j = this.messages.length; i < j; i++) {
                    if(this.messages[i].seen) this.messagesRead.push(this.messages[i])
                    else this.messagesUnread.push(this.messages[i]);
                }
                //sorting message by descending date
                this.messages = this.messages.sort((a: Message, b: Message) => {
                    if(a.horo < b.horo) return 1;
                    else if (a.horo > b.horo) return -1;
                    else return 0;
                });
                this.messagesRead = this.messagesRead.sort((a: Message, b: Message) => {
                    if(a.horo < b.horo) return 1;
                    else if (a.horo > b.horo) return -1;
                    else return 0;
                });
                this.messagesUnread = this.messagesUnread.sort((a: Message, b: Message) => {
                    if(a.horo < b.horo) return 1;
                    else if (a.horo > b.horo) return -1;
                    else return 0;
                });
            }
        });
    }

    viewMessage(message: Message) {
        let messageView = this.modal.create(MessageComponent ,{
            message: message
        });
        messageView.onDidDismiss(() => {
            if(!message.seen) {
                message.seen = true;
                this.msgService.update(message).subscribe();
                this.setMessages();
            }
        });
        messageView.present();
    }

}
