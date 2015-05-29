package org.javagaming.bot;

public class Greeter extends EventListener{
	public Greeter(JGOBot bot) {
		super(bot);
	}

	@Override
	public void onJoin(String sender){
		if(!sender.equalsIgnoreCase(JGOBot.BOT_NAME))
			this.bot.send("Welcome to " + JGOBot.CHANNEL + ", " + sender + ".");
	}
}
