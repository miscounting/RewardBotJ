# Configuration Schema

```txt
rewardbotj.config.schema.json
```




| Abstract            | Extensible | Status         | Identifiable | Custom Properties | Additional Properties | Access Restrictions | Defined In                                                             |
| :------------------ | ---------- | -------------- | ------------ | :---------------- | --------------------- | ------------------- | ---------------------------------------------------------------------- |
| Can be instantiated | Yes        | Unknown status | No           | Forbidden         | Allowed               | none                | [config.schema.json](../out/config.schema.json "open original schema") |

## Configuration Type

`object` ([Configuration](config.md))

# Configuration Properties

| Property              | Type     | Required | Nullable       | Defined by                                                                                          |
| :-------------------- | -------- | -------- | -------------- | :-------------------------------------------------------------------------------------------------- |
| [channel](#channel)   | `string` | Required | cannot be null | [Configuration](config-properties-channel.md "rewardbotj.config.schema.json#/properties/channel")   |
| [bot_name](#bot_name) | `string` | Optional | cannot be null | [Configuration](config-properties-bot_name.md "rewardbotj.config.schema.json#/properties/bot_name") |
| [oauth](#oauth)       | `string` | Required | cannot be null | [Configuration](config-properties-oauth.md "rewardbotj.config.schema.json#/properties/oauth")       |
| [commands](#commands) | `array`  | Required | cannot be null | [Configuration](config-properties-commands.md "rewardbotj.config.schema.json#/properties/commands") |

## channel

The streamer's channel


`channel`

-   is required
-   Type: `string`
-   cannot be null
-   defined in: [Configuration](config-properties-channel.md "rewardbotj.config.schema.json#/properties/channel")

### channel Type

`string`

## bot_name

The Twitch bot account


`bot_name`

-   is optional
-   Type: `string`
-   cannot be null
-   defined in: [Configuration](config-properties-bot_name.md "rewardbotj.config.schema.json#/properties/bot_name")

### bot_name Type

`string`

## oauth

OAuth Token for IRC USer (You can get yours here: <http://twitchapps.com/tmi/>)


`oauth`

-   is required
-   Type: `string`
-   cannot be null
-   defined in: [Configuration](config-properties-oauth.md "rewardbotj.config.schema.json#/properties/oauth")

### oauth Type

`string`

## commands




`commands`

-   is required
-   Type: `object[]` ([Command](config-definitions-command.md))
-   cannot be null
-   defined in: [Configuration](config-properties-commands.md "rewardbotj.config.schema.json#/properties/commands")

### commands Type

`object[]` ([Command](config-definitions-command.md))

# Configuration Definitions

## Definitions group command

Reference this group by using

```json
{"$ref":"rewardbotj.config.schema.json#/definitions/command"}
```




`command`

-   is optional
-   Type: `object` ([Command](config-definitions-command.md))
-   cannot be null
-   defined in: [Configuration](config-definitions-command.md "rewardbotj.config.schema.json#/definitions/command")

### command Type

`object` ([Command](config-definitions-command.md))

## Definitions group action

Reference this group by using

```json
{"$ref":"rewardbotj.config.schema.json#/definitions/action"}
```




`action`

-   is optional
-   Type: `object` ([Action](config-definitions-action.md))
-   cannot be null
-   defined in: [Configuration](config-definitions-action.md "rewardbotj.config.schema.json#/definitions/action")

### action Type

`object` ([Action](config-definitions-action.md))
