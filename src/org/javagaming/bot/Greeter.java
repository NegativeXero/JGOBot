package org.javagaming.bot;

public class Greeter extends EventListener{
	
	String[] greetings = new String[]{
			"hello", "hi", "hey", "hullo", "welcome", "hiya", "hallo",
			"good morning", "good evening", "good afternoon", 
			"howdy", "salut", "salutations", "Guten tag"
		};
	
	public Greeter(JGOBot bot) {
		super(bot);
	}

	@Override
	public void onConnect(){
		this.bot.send("Hello everyone!");
	}
	
	@Override
	public void onJoin(String sender){
		if(!sender.equalsIgnoreCase(JGOBot.BOT_NAME))
			this.bot.send("Welcome to " + JGOBot.CHANNEL + ", " + sender + ".");
	}
	
	@Override
	public void onMessage(String sender, String message){
		message = message.toLowerCase();
		if(message.contains(JGOBot.BOT_NAME)){
			for(String greeting : greetings){
				if(message.contains(greeting)){
					bot.send("Hello, " + sender + "!");
					return;
				}
			}
		}
	}
}
