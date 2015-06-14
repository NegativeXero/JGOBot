package org.javagaming.bot;

public class Bender extends EventListener{
	String[] quotes = new String[]{
			"Bite my shiny metal ass.",
			"I am Bender. Please insert girder.",
			"Ah, computer dating. Its like pimping, but you rarely have to use the phrase upside your head.",
			"Your social security check's bounced. Stuff cost more than it used to. Young people use curse words. ",
			"Game's over, losers! I have all the money. Compare your lives to mine and then kill yourselves.",
			"This is the worst kind of discrimination. The kind against me!",
			"Oh. Your. God.",
			"Yeah. Well. I'm gonna go build my own theme park with blackjack and hookers. In fact, forget the park.",
			"Life is hilariously cruel.",
			"Take that, Beethoven – you deaf bastard! ",
			"I came here with a simple dream. A dream of killing all humans. And this is how it must end? Who's the real 7 billion ton robot monster here? Not I, not I...",
	};
	public Bender(JGOBot bot) {
		super(bot);
	}

	@Override
	public void onMessage(String sender, String message){
		if(message.toLowerCase().contains("blackjack and hookers")){
			this.bot.send(quotes[(int)(Math.random() * quotes.length)]);
		}
	}
}
