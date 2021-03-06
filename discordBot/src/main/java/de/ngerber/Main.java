package de.ngerber;

import de.ngerber.commands.ICommand;
import de.ngerber.commands.TestCommand;
import de.ngerber.data.Constants;
import de.ngerber.listener.CommandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.util.HashMap;

public class Main
{
    public static void main(String[] args) throws LoginException, InterruptedException
    {

        JDABuilder builder = JDABuilder.createDefault(args[0]);
        builder.setActivity(Activity.of(Activity.ActivityType.WATCHING,"Neo-tube"));

        HashMap<String, ICommand> commands = addCommands();
        addListeners(builder, commands);

        JDA jda = builder.build();
        jda.awaitReady();
    }

    private static HashMap<String, ICommand> addCommands()
    {
        HashMap<String, ICommand> commands = new HashMap<>();

        commands.put("hey", new TestCommand());

        return commands;
    }

    private static void addListeners(JDABuilder builder, HashMap<String, ICommand> commands)
    {
        builder.addEventListeners(new CommandListener(commands));
    }
}
