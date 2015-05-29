package org.javagaming.bot;

public class DanceCommand extends Command
{
    public DanceCommand(JGOBot bot)
    {
        super(bot, "dance");
        this.description = "JUST DANCE";

        this.usage = new String[]{ "::dance!!!!" };
        this.hidden = true;
    }

    @Override
    public void execute(String sender, String[] commandArgs)
    {
        this.bot.send("1..2...3....");
        this.bot.sendCTCPCommand(JGOBot.CHANNEL, "ACTION broke the floor while dancing!");
    }
}
