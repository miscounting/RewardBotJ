# RewardBot

A simple bot designed to integrate with Twitch custom channel point rewards.  RewardBotJ snoops on your twitch reward redemptions using **twitch4j**, identifies rewards with configured actions, and performs those actions automatically.

Unlike other similar tools, this one does not depend on forcing your users to submit text along with the reward redemption!

# To use
## Install
This repo contains a zip file with an executable jar, an example config file, and some (hopefully) helpful scripts.

## Configure

This repository includes a config.example.yaml file that should help you get started.  You will need to edit it and rename to config.yaml.  Use it to define a list of commands for the bot to perform.  Commands must have a twitch_reward_id and an action.  Actions currently include:

* Press hotkeys (easy way to integrate with OBS or other programs)
* Whisper at bot (good for invoking commands on Streamlabs chatbot!)
* Perform one of a list of actions at random!

### Find your custom reward IDs
Create a custom reward on your Twitch channel.

Run the program, redeem your rewards in your twitch chat, and watch the console for helpful logs.