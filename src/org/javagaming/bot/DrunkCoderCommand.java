package org.javagaming.bot;

public class DrunkCoderCommand extends Command{
	public DrunkCoderCommand(JGOBot bot) {
		super(bot, "323", new String[] {
		});
		this.description = "JUST DANCE";
		this.usage = new String[]{
		};
		this.hidden = true;
	}

	@Override
	public void execute(String sender, String[] commandArgs) {
		this.bot.send("xkcd.com/323");
	}
}

