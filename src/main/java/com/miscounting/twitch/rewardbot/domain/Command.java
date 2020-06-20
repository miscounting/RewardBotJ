package com.miscounting.twitch.rewardbot.domain;

public class Command {
    private String optionalName;
    private String twitchRewardId;
    private Action action;

    public String getOptionalName() {
        return optionalName;
    }

    public String getTwitchRewardId() {
        return twitchRewardId;
    }

    public Action getAction() {
        return action;
    }

}
