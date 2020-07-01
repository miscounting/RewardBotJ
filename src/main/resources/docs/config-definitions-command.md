# Command Schema

```txt
rewardbotj.config.schema.json#/definitions/command
```




| Abstract            | Extensible | Status         | Identifiable | Custom Properties | Additional Properties | Access Restrictions | Defined In                                                               |
| :------------------ | ---------- | -------------- | ------------ | :---------------- | --------------------- | ------------------- | ------------------------------------------------------------------------ |
| Can be instantiated | No         | Unknown status | No           | Forbidden         | Allowed               | none                | [config.schema.json\*](../out/config.schema.json "open original schema") |

## command Type

`object` ([Command](config-definitions-command.md))

# Command Properties

| Property                          | Type     | Required | Nullable       | Defined by                                                                                                                                              |
| :-------------------------------- | -------- | -------- | -------------- | :------------------------------------------------------------------------------------------------------------------------------------------------------ |
| [optionalName](#optionalName)     | `string` | Optional | cannot be null | [Configuration](config-definitions-command-properties-optionalname.md "rewardbotj.config.schema.json#/definitions/command/properties/optionalName")     |
| [twitchRewardId](#twitchRewardId) | `string` | Required | cannot be null | [Configuration](config-definitions-command-properties-twitchrewardid.md "rewardbotj.config.schema.json#/definitions/command/properties/twitchRewardId") |
| [action](#action)                 | `object` | Required | cannot be null | [Configuration](config-definitions-action.md "rewardbotj.config.schema.json#/definitions/command/properties/action")                                    |

## optionalName

The name of this command. Optional - used mostly for debugging.


`optionalName`

-   is optional
-   Type: `string`
-   cannot be null
-   defined in: [Configuration](config-definitions-command-properties-optionalname.md "rewardbotj.config.schema.json#/definitions/command/properties/optionalName")

### optionalName Type

`string`

## twitchRewardId

Twitch-generated identifier for your custom channel points reward. You can find your reward ID here: <https://www.instafluff.tv/TwitchCustomRewardID/?channel=yourchannel>


`twitchRewardId`

-   is required
-   Type: `string`
-   cannot be null
-   defined in: [Configuration](config-definitions-command-properties-twitchrewardid.md "rewardbotj.config.schema.json#/definitions/command/properties/twitchRewardId")

### twitchRewardId Type

`string`

## action




`action`

-   is required
-   Type: `object` ([Action](config-definitions-action.md))
-   cannot be null
-   defined in: [Configuration](config-definitions-action.md "rewardbotj.config.schema.json#/definitions/command/properties/action")

### action Type

`object` ([Action](config-definitions-action.md))
