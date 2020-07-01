# RewardBot

A simple bot designed to integrate with Twitch custom channel point rewards.  RewardBotJ snoops on your twitch reward redemptions using **twitch4j**, identifies rewards with configured actions, and performs those actions automatically.

Unlike other similar tools, this one does not depend on forcing your users to submit text along with the reward redemption!

# To use
## Install
This repo contains a zip file with an executable jar, an example config file, and some (hopefully) helpful scripts.

## Configure

This bot is config-driven.  Use the config file to define your commands and the actions that should be taken when they are invoked.  Currently-supported actions include:

* Press hotkeys (easy way to integrate with OBS or other programs)
* Whisper at bot (good for invoking commands on Streamlabs chatbot!)
* Perform one of a list of actions at random!

[See configuration documentation](src/main/resources/docs/config.md)

## Find your custom reward IDs
Create a custom reward on your Twitch channel.

Run the program, redeem your rewards in your twitch chat, and watch the console for helpful logs.

# What do?
What don't do?!

Hotkeys are useful for triggering actions in a variety of programs.  SLOBs lets you start/stop streaming, show/hide views, trigger scene transitions with hotkeys.

Whispering to your bot is a great way to trigger secret commands in Streamlabs chatbot.

Random is always silly.

Have fun!

# TODOs

* Replace printlns with logging
* Create a web form to populate config
* Figure out modular/executable packaging
