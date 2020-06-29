package com.miscounting.twitch.rewardbot.domain;

public class Timeout {

    private int delayInSeconds;
    private Action action;

    public int getDelayInSeconds() {
        return delayInSeconds;
    }

    public Action getAction() {
        return action;
    }
}
