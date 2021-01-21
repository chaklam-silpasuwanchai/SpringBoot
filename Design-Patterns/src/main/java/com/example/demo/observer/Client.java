package com.example.demo.observer;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//create subject
		Topic politics = new Topic();
		Topic sports = new Topic();
		
		//create observers
		Observer ob1 = new TopicSubscriber("Political CNN");
		Observer ob2 = new TopicSubscriber("General BBC");
		Observer ob3 = new TopicSubscriber("Sports CNN");
		
		//register observers to the subject
		politics.register(ob1);
		politics.register(ob2);
		sports.register(ob3);
		
		//attach observer to subject
		ob1.setSubject(politics);
		ob2.setSubject(politics);
		ob3.setSubject(sports);
		
		//check if any update is available
		ob1.update();
		
		//now send message to subject
		politics.postMessage("Prime Minister election!");
		sports.postMessage("Manchester City wins!");
	}

}
