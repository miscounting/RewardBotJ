package com.miscounting.twitch.rewardbot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.github.twitch4j.pubsub.events.RewardRedeemedEvent;
import com.miscounting.twitch.rewardbot.domain.Action;
import com.miscounting.twitch.rewardbot.domain.Command;
import com.miscounting.twitch.rewardbot.domain.Configuration;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class RewardBot {

    /**
     * Holds the Bot Configuration
     */
    private Configuration configuration;

    /**
     * Twitch4J API
     */
    private final TwitchClient twitchClient;

    private final Map<String, Command> commandMap = new HashMap<>();

    public RewardBot() {
        System.out.println("I have started");
        loadConfiguration();
        System.out.println("Configuration loaded. " + configuration);
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        //region Auth
        OAuth2Credential credential = new OAuth2Credential(
                "twitch", configuration.getOauth());
        //endregion

        twitchClient = TwitchClientBuilder.builder()
                .withEnableChat(true)
                .withEnableHelix(true)
                .withChatAccount(credential)
                .withEnablePubSub(true)
                .build();
        // TODO replace common keys like 'command' and 'win'
        // TODO perform things at random
        // TODO clean up printlns and add logging

        twitchClient.getChat().joinChannel(configuration.getChannel());

        String channelId = twitchClient
                .getHelix()
                .getUsers(credential.getAccessToken(), null, Arrays.asList(configuration.getChannel()))
                .execute()
                .getUsers()
                .get(0)
                .getId();

        // Register for pubsub topic about channel point rewards.
        twitchClient.getPubSub().listenForChannelPointsRedemptionEvents(credential, channelId);

        SimpleEventHandler eventHandler = twitchClient.getEventManager().getEventHandler(SimpleEventHandler.class);
        // Register Event-based features
        eventHandler.onEvent(ChannelMessageEvent.class, (event) ->
                System.out.println(event.getUser().getName() + ": " + event.getMessage()));
        eventHandler.onEvent(RewardRedeemedEvent.class, (event) -> {
            String rewardId = event.getRedemption().getReward().getId();
            if (commandMap.containsKey(rewardId)) {
                commandMap.get(rewardId).getAction().execute(twitchClient.getChat(), configuration, executorService);
            }
            else {
                System.out.println("Could not find a command for reward " + rewardId);
            }
        });
    }

    public static void main(String[] args) {

        new RewardBot();

    }

    /**
     * Load the Configuration
     */
    private void loadConfiguration() {
        try {
            InputStream is;
            if (System.getProperty("CONFIGDIR") != null) {
                String pathToConfig = System.getProperty("CONFIGDIR","") + "config.yaml";
                System.out.println(pathToConfig);
                File file = new File(pathToConfig);
                System.out.println(file.getPath());
                is = new FileInputStream(file);
            }else {
                ClassLoader classloader = Thread.currentThread().getContextClassLoader();
                is = classloader.getResourceAsStream("config.yaml");
            }
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

            configuration = mapper.readValue(is, Configuration.class);

            List<String> validationErrors = validateConfiguration(configuration);
            if (validationErrors.size() > 0) {
                System.err.println("Configuration errors: ");
                for (String err : validationErrors) {
                    System.err.println(err);
                }
                System.exit(1);
            }

            for (Command command : configuration.getCommands()) {
                System.out.println("Adding comand: " + command.getOptionalName());
                commandMap.put(command.getTwitchRewardId(), command);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Unable to load Configuration ... Exiting.");
            System.exit(1);
        }
    }

    private List<String> validateConfiguration(Configuration configuration) {
        List<String> errorMessages = new ArrayList<>();
        if (configuration.getOauth() == null || configuration.getOauth().isEmpty()) {
            errorMessages.add("Missing required configuration field oauth.");
        }
        if (configuration.getChannel() == null || configuration.getChannel().isEmpty()) {
            errorMessages.add("Missing required configuration field channel.");
        }
        if (configuration.getBotName() != null && configuration.getBotName().isEmpty()) {
            errorMessages.add("Configuration field botName cannot be empty.");
        }
        for (Command command : configuration.getCommands()) {
            if (command.getTwitchRewardId() == null || command.getTwitchRewardId().isEmpty()) {
                errorMessages.add("Command is missing required field twitchRewardId.");
            }
            if (command.getAction() == null) {
                errorMessages.add(
                        String.format("Command %s is missing required field action.", command.getOptionalName()));
            } else {
                validateAction(command.getAction(), errorMessages);
            }
        }
        return errorMessages;
    }

    private void validateAction(Action action, List<String> errorMessages) {
        if (action.getKeysToPress() == null && action.getMessageToWhisper() == null && action.getPerformAtRandom() == null) {
            errorMessages.add(String.format("Action field %s must contain at least one action type.", action.getName()));
        }
        if (action.getKeysToPress() != null && KeyStroke.getKeyStroke(action.getKeysToPress()) == null) {
            errorMessages.add(String.format(
                    "Field keysToPress: %s in Action %s is not a valid KeyStroke.",
                    action.getKeysToPress(),
                    action.getName()));
        }
    }

}
