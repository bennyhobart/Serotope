package MessageHandler;

public class Telegram {
	int sender;
	int reciever;
	Object extraData;
	int timeSent;
	Telegram(int sender, int reciever,Object extraData, int timeSent) {
		this.sender=sender;
		this.reciever=reciever;
		this.extraData=extraData;
		this.timeSent=timeSent;
	}
}
