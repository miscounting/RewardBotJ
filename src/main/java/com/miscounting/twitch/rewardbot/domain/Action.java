package com.miscounting.twitch.rewardbot.domain;

import com.github.twitch4j.chat.TwitchChat;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Action {

    private String name;
    private String keysToPress;
    private String messageToWhisper;
    private List<Action> performAtRandom;
    private Timeout optionalTimeout;
    private int randomIndex;

    public String getName() {
        return name;
    }

    public String getKeysToPress() {
        return keysToPress;
    }

    public String getMessageToWhisper() {
        return messageToWhisper;
    }

    public Collection<Action> getPerformAtRandom() {
        return performAtRandom;
    }

    public Timeout getOptionalTimeout() { return optionalTimeout; }

    public void execute(TwitchChat twitchChat, Configuration config, ScheduledExecutorService executorService) {
        if (keysToPress != null && !keysToPress.isEmpty()) {
            try {
                System.out.println("Attempting to press " + keysToPress);
                Robot robot = new Robot();
                KeyStroke keystroke = KeyStroke.getKeyStroke(keysToPress);
                System.out.println("Keystroke: " + keystroke);
                char toPress = keystroke.getKeyChar();
                System.out.println("will press: " + toPress);
                robot.keyPress(toPress);
                robot.keyRelease(toPress);
                System.out.println("Done pressing.");
            } catch (AWTException e) {
                System.err.println("Exception trying to execute keypresses.");
                e.printStackTrace();
            }
        }
        if (messageToWhisper != null && !messageToWhisper.isEmpty()) {
            // whisper bot
            System.out.printf("Whispering to bot %s: %s", config.getBotName(), messageToWhisper);
            twitchChat.sendPrivateMessage(config.getBotName(), messageToWhisper);
        }
        if (performAtRandom != null && performAtRandom.size() > 0) {
            // perform at random
            randomIndex = new Random().nextInt(performAtRandom.size());
            performAtRandom.get(randomIndex).execute(twitchChat, config, executorService);
        }
        if (optionalTimeout != null) {
            executorService.schedule(() -> {optionalTimeout.getAction().execute(twitchChat, config, executorService);},
                    optionalTimeout.getDelayInSeconds(),
                    TimeUnit.SECONDS);
        }
    }
}

