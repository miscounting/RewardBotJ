package com.miscounting.twitch.rewardbot.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Configuration {

    private String oauth;

    private String channel;

    private String botName;

    private List<Command> commands;

    public String getOauth() { return oauth; }

    public String getChannel() {
        return channel;
    }

    public String getBotName() {
        return botName;
    }

    public List<Command> getCommands() { return commands; }

    @Override
    public String toString() {
        return "Configuration{" +
                ", oauth=" + oauth +
                ", channel=" + channel +
                ", bot=" + botName +
                ", commands=" + commands +
                '}';
    }
}