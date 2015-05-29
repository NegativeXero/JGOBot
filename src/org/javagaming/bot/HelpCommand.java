package org.javagaming.bot;

public class HelpCommand extends Command
{

    public HelpCommand(JGOBot bot)
    {
        super(bot, "help", new String[]{
                "commands", "h", "elp", "hlp", "hel", "c", "halp"
        });
        this.description = "Provides information about the commands that I have available";
        this.usage = new String[]{
                JGOBot.COMMAND_IDENTIFIER + "help - Lists commands available",
                JGOBot.COMMAND_IDENTIFIER + "help <command> - "
                + "Provides information about this command",
        };
    }

    @Override
    public void execute(String sender, String[] commandArgs)
    {
        //no parameters
        if (commandArgs.length == 1)
        {
            String[] commandList = bot.getCommands();
            this.bot.send("I recognise these commands:");
            for (String commandName : commandList)
            {
                Command command = bot.getCommand(commandName);
                if (!command.hidden)
                    this.bot.send(commandName.toUpperCase() + ": " + command.description + ".");
            }
            this.bot.send("Use " + JGOBot.COMMAND_IDENTIFIER + "help <command> for "
                          + "information about a specific command");
        }
        //one parameters
        else if (commandArgs.length == 2)
        {
            Command command = bot.getCommand(commandArgs[1]);
            if (command == null)
            {
                this.bot.send(commandArgs[1] + " is not a command that I recognise.");
                return;
            }
            this.bot.send(commandArgs[1] + " " + command.description);
            for (String commandUse : command.usage)
            {
                this.bot.send("\t" + commandUse);
            }
        }
        else
        {
            this.bot.send("Invalid number of parameters");
        }
    }
}
