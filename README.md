# RewardBot

A simple bot designed to integrate with Twitch custom channel point rewards.  RewardBotJ snoops on your twitch reward redemptions using **twitch4j**, identifies rewards with configured actions, and performs those actions automatically.

# To use
### Install

## Configure

This repository includes a config.example.yaml file that should help you get started.  You will need to edit it and rename to config.yaml.  Use it to define a list of commands for the bot to perform.  Commands must have a twitch_reward_id and an action.  Actions currently include:

* Press hotkeys (easy way to integrate with OBS or other programs)
* Whisper at bot (good for invoking commands on Streamlabs chatbot!)
* Perform one of a list of actions at random!

### Find your custom reward IDs
Create a custom reward on your Twitch channel.

Open InstaFluff's reward ID finder tool:

[https://www.instafluff.tv/TwitchCustomRewardID/?channel=yourchannel](https://www.instafluff.tv/TwitchCustomRewardID/?channel=yourchannel)