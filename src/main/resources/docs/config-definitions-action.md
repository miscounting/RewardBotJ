# Action Schema

```txt
rewardbotj.config.schema.json#/definitions/action
```




| Abstract            | Extensible | Status         | Identifiable | Custom Properties | Additional Properties | Access Restrictions | Defined In                                                               |
| :------------------ | ---------- | -------------- | ------------ | :---------------- | --------------------- | ------------------- | ------------------------------------------------------------------------ |
| Can be instantiated | No         | Unknown status | No           | Forbidden         | Allowed               | none                | [config.schema.json\*](../out/config.schema.json "open original schema") |

## action Type

`object` ([Action](config-definitions-action.md))

# Action Properties

| Property                              | Type     | Required | Nullable       | Defined by                                                                                                                                                |
| :------------------------------------ | -------- | -------- | -------------- | :-------------------------------------------------------------------------------------------------------------------------------------------------------- |
| [name](#name)                         | `string` | Optional | cannot be null | [Configuration](config-definitions-action-properties-name.md "rewardbotj.config.schema.json#/definitions/action/properties/name")                         |
| [messageToWhisper](#messageToWhisper) | `string` | Optional | cannot be null | [Configuration](config-definitions-action-properties-messagetowhisper.md "rewardbotj.config.schema.json#/definitions/action/properties/messageToWhisper") |
| [keysToPress](#keysToPress)           | `string` | Optional | cannot be null | [Configuration](config-definitions-action-properties-keystopress.md "rewardbotj.config.schema.json#/definitions/action/properties/keysToPress")           |
| [performAtRandom](#performAtRandom)   | `array`  | Optional | cannot be null | [Configuration](config-definitions-action-properties-performatrandom.md "rewardbotj.config.schema.json#/definitions/action/properties/performAtRandom")           |

## name

The name of this action; optional, for debugging.


`name`

-   is optional
-   Type: `string`
-   cannot be null
-   defined in: [Configuration](config-definitions-action-properties-name.md "rewardbotj.config.schema.json#/definitions/action/properties/name")

### name Type

`string`

## messageToWhisper

A message to whisper to your bot (if configured). Useful for triggering Streamlabs Chatbot commands.


`messageToWhisper`

-   is optional
-   Type: `string`
-   cannot be null
-   defined in: [Configuration](config-definitions-action-properties-messagetowhisper.md "rewardbotj.config.schema.json#/definitions/action/properties/messageToWhisper")

### messageToWhisper Type

`string`

## keysToPress

 string to represent hotkeys. Must follow the Java KeyStroke format:

      # See: https://docs.oracle.com/javase/7/docs/api/javax/swing/KeyStroke.html#getKeyStroke(java.lang.String)
      # example: shift control alt command A


`keysToPress`

-   is optional
-   Type: `string`
-   cannot be null
-   defined in: [Configuration](config-definitions-action-properties-keystopress.md "rewardbotj.config.schema.json#/definitions/action/properties/keysToPress")

### keysToPress Type

`string`
