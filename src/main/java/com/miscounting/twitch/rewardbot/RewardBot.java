package com.miscounting.twitch.rewardbot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.github.twitch4j.pubsub.events.RewardRedeemedEvent;
import com.miscounting.twitch.rewardbot.domain.Command;
import com.miscounting.twitch.rewardbot.domain.Configuration;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class RewardBot {

    /**
     * Holds the Bot Configuration
     */
    private Configuration configuration;

    /**
     * Twitch4J API
     */
    private final TwitchClient twitchClient;

    private String channelId;

    private final Map<String, Command> commandMap = new HashMap<>();

    public RewardBot() {
        System.out.println("I have started");
        loadConfiguration();
        System.out.println("Configuration loaded. " + configuration);

        //region Auth
        OAuth2Credential credential = new OAuth2Credential(
                "twitch", configuration.getOauth());
        //endregion

        twitchClient = TwitchClientBuilder.builder()
                .withEnableChat(true)
                .withChatAccount(credential)
                .withEnablePubSub(true)
                .build();
        // TODO validate config
        // TODO replace common keys like 'command' and 'win'
        // TODO perform things at random
        // TODO clean up printlns and add logging
        // TODO learn how to package
        // TODO don't lazy-load the pubsub subscription - figure out getUsers API permissions.

        twitchClient.getChat().joinChannel(configuration.getChannel());

        SimpleEventHandler eventHandler = twitchClient.getEventManager().getEventHandler(SimpleEventHandler.class);
        // Register Event-based features
        eventHandler.onEvent(ChannelMessageEvent.class, (event) -> {
            System.out.println(event.getUser().getName() + ": " + event.getMessage());
            // This is a hack to pull the channel ID out of a chat message in the channel we're watching.
            // This keeps us in the scope of the chat API oauth token we use.
            if (channelId == null) {
                System.out.println("Registering channel ID");
                channelId = event.getChannel().getId();

                //subscribe to pubsub about this channel
                twitchClient.getPubSub().listenForChannelPointsRedemptionEvents(credential, channelId);
            }
        });

        eventHandler.onEvent(RewardRedeemedEvent.class, (event) -> {
            String message = String.format(
                    "%s just redeemed %s for %s!  Id: %s",
                    event.getRedemption().getUser().getDisplayName(),
                    event.getRedemption().getReward().getTitle(),
                    event.getRedemption().getReward().getCost(),
                    event.getRedemption().getReward().getId()
            );

            String rewardId = event.getRedemption().getReward().getId();
            if (commandMap.containsKey(rewardId)) {
                commandMap.get(rewardId).getAction().execute(twitchClient.getChat(), configuration);
            }
        });
    }

    public static void main(String[] args) {

        RewardBot bot = new RewardBot();

    }

    /**
     * Load the Configuration
     */
    private void loadConfiguration() {
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream("config.yaml");

            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            configuration = mapper.readValue(is, Configuration.class);

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

}
