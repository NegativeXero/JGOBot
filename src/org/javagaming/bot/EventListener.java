package org.javagaming.bot;

public abstract class EventListener
{
    JGOBot bot;

    public EventListener(JGOBot bot)
    {
        this.bot = bot;
    }

    public void onAction(String sender, String action)
    {
    }

    public void onConnect()
    {
    }

    public void onDisconnect()
    {
    }

    public void onKick(String op, String kicked, String reason)
    {
    }

    public void onJoin(String sender)
    {
    }

    public void onMessage(String sender, String message)
    {
    }
}
