package org.javagaming.bot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.PircBot;

public class JGOBot extends PircBot {

	final static String NETWORK = "irc.freenode.net";
	final static String CHANNEL = "#jgobot";
	//final static String CHANNEL = "#java-gaming";
	
	final static String COMMAND_IDENTIFIER = "::";
	final static String BOT_NAME = "JGOBot";
	
	private List<Command> commands = new ArrayList<Command>();
	
	public static void main(String[] args) {
		JGOBot bot = new JGOBot();
		bot.setVerbose(true);
		try {
			bot.connect(NETWORK);
		} catch (IOException | IrcException e) {
			System.err.println("Failed to join freenode. Exitting now.");
			e.printStackTrace();
		}
		bot.joinChannel(CHANNEL);
	}
	
	public JGOBot() {
		//Set the bot's nickname
		this.setName(BOT_NAME);
		registerCommand(new HelpCommand(this));
		registerCommand(new DanceCommand(this));
	}
	
	private void registerCommand(Command command) {
		commands.add(command);
	}
	
	//Event triggered by a message being sent in the channel
	public void onMessage(String channel, String sender, 
			String login, String hostname, String message) {
		if(message.startsWith(COMMAND_IDENTIFIER) && !sender.equalsIgnoreCase(BOT_NAME)) {
			String[] commandArgs = message.split(" ");
			commandArgs[0] = commandArgs[0].replaceAll(COMMAND_IDENTIFIER, "");
			Command command = getCommand(commandArgs[0]);
			if(command != null)
				command.execute(sender, commandArgs);
			else
				send("Command not recognised.");
		}
	}
	
	public Command getCommand(String command) {
		for(Command c : commands){
			if(command.equalsIgnoreCase(c.COMMAND_NAME)) {
				return c;
			}
		}
		for(Command c : commands) {
			for(String s : c.ALIASES) {
				if(command.equalsIgnoreCase(s)) {
					return c;
				}
			}
		}
		return null;
	}
	
	public String[] getCommands() {
		String[] commandList = new String[commands.size()];
		int i = 0;
		for(Command command : commands) {
			commandList[i] = command.COMMAND_NAME;
			i++;
		}
		return commandList;
	}

	public void send(String message){
		this.sendMessage(JGOBot.CHANNEL, message);
	}
}
