package org.javagaming.bot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.PircBot;

public class JGOBot extends PircBot {

	final static String NETWORK = "irc.freenode.net";
	//final static String CHANNEL = "#jgobot";
	final static String CHANNEL = "#java-gaming";
	
	final static String COMMAND_IDENTIFIER = "::";
	final static String BOT_NAME = "JGOBot";

	private List<Command> commands = new ArrayList<Command>();
	private List<EventListener> listeners = new ArrayList<EventListener>();
	
	public static void main(String[] args) {
		JGOBot bot = new JGOBot();
		bot.setVerbose(true);	//debug info is output.
		try {
			bot.connect(NETWORK);
		} catch (IOException | IrcException e) {
			System.err.println("Failed to join freenode. Exitting now.");
			e.printStackTrace();
		}
		bot.joinChannel(CHANNEL);
	}
	
	public JGOBot() {
		this.setName(BOT_NAME);
		register(new HelpCommand(this));
		register(new DanceCommand(this));
		register(new Greeter(this));
	}

	//Registers commands
	private void register(Command command) {
		commands.add(command);
	}

	//Registers listeners
	private void register(EventListener event) {
		listeners.add(event);
	}
	
	//Event triggered by someone using /me (including the bot)
	public void onAction(String sender, String login, 
			String hostname, String target, String action){
		for(EventListener listener : listeners){
			listener.onAction(sender, action);
		}
	}

	//Event triggered when the bot connects
	public void onConnect(){
		for(EventListener listener : listeners){
			listener.onConnect();
		}
	}
	
	//Event triggered when the bot disconnects
		public void onDisconnect(){
			for(EventListener listener : listeners){
				listener.onDisconnect();
			}
		}
	
	//Event triggered by someone joining the channel (includes the bot)
	public void onJoin(String channel, String sender, String login, String hostname){
		for(EventListener listener : listeners){
			listener.onJoin(sender);
		}
	}
	
	//Event triggered by someone being kicked by an OP
	public void onKick(String channel, String kickerNick, String kickerLogin, 
			String kickerHostname, String recipientNick, String reason){
		for(EventListener listener : listeners){
			listener.onKick(kickerNick, recipientNick, reason);
		}
	}
	
	//Event triggered by a message being sent in the channel
	public void onMessage(String channel, String sender, 
			String login, String hostname, String message) {
		//Manage commands
		if(message.startsWith(COMMAND_IDENTIFIER) && !sender.equalsIgnoreCase(BOT_NAME)) {
			String[] commandArgs = message.split(" ");
			commandArgs[0] = commandArgs[0].replaceAll(COMMAND_IDENTIFIER, "");
			if(!commandArgs[0].equalsIgnoreCase("")){
				Command command = getCommand(commandArgs[0]);
				command.execute(sender, commandArgs);
			} else {
				send("Command not recognised.");
			}
		}
		//Send events to listeners if not a command
		else {
			for(EventListener listener : listeners){
				listener.onMessage(sender, message);
			}
		}
	}
	
	
	//Returns the command object the corresponds to the provided command name
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
	
	//Returns a list of all the registered command names
	public String[] getCommands() {
		String[] commandList = new String[commands.size()];
		int i = 0;
		for(Command command : commands) {
			commandList[i] = command.COMMAND_NAME;
			i++;
		}
		return commandList;
	}

	//Sends a message in chat
	public void send(String message){
		this.sendMessage(JGOBot.CHANNEL, message);
	}
}
