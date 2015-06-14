package org.javagaming.bot;

public abstract class Command {
	public final String COMMAND_NAME;
	public final String[] ALIASES;
	public String description;
	public String[] usage;
	public boolean hidden = false;
	JGOBot bot;
	public Command(JGOBot bot, String commandName) {
		this(bot, commandName, new String[]{});
	}
	
	public Command(JGOBot bot, String commandName, String[] aliases) {
		this.bot = bot;
		this.COMMAND_NAME = commandName;
		this.ALIASES = aliases;
	}
	
	public abstract void execute(String sender, String[] commandArgs);
}
