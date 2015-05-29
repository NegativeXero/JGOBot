package org.javagaming.bot;

public class DanceCommand extends Command{

	public DanceCommand(JGOBot bot) {
		super(bot, "dance");
	}

	@Override
	public void execute(String sender, String[] commandArgs) {
		this.bot.send("No.");
	}
	
}
