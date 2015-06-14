package org.javagaming.bot;

public class DrunkCoderCommand extends Command{
	public DrunkCoderCommand(JGOBot bot) {
		super(bot, "323", new String[] {
				"ballmer'speak", "ballmerspeak", "balmerspeak", "balmer'speak", "bpeak", "ballmer's_peak", "ballmers_peak", "balmers_peak", "balmer's_peak"
		});
		this.description = "JUST DANCE";
		this.usage = new String[]{
				JGOBot.COMMAND_IDENTIFIER + "323 - Link for convenience"	
		};
		this.hidden = true;
	}

	@Override
	public void execute(String sender, String[] commandArgs) {
		this.bot.send("xkcd.com/323");
	}
}
