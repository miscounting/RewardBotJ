import React, {useState} from 'react';
import Form from "@rjsf/material-ui";
import Container from '@material-ui/core/Container';
import Grid from '@material-ui/core/Grid';

const log = (type) => console.log.bind(console, type);

const defaultJson = {
    "bot_name": "YOUR_TWITCH_BOT_NAME",
    "channel": "YOUR_TWITCH_CHANNEL_NAME",
    "oauth": "YOUR_SECRET_OAUTH_TOKEN",
    "commands": [
        {
            "optionalName": "press some keys command",
            "twitchRewardId": "TWITCH_GENERATED_COMMAND_ID",
            "action": {
                "name": "press some keys",
                "keysToPress": "shift control alt meta 1"
            }
        },
        {
            "optionalName": "whisper to your bot command",
            "twitchRewardId": "TWITCH_GENERATED_COMMAND_ID_2",
            "action": {
                "messageToWhisper": "!whisperedCommand"
            }
        },
        {
            "optionalName": "Perform one random action command",
            "twitchRewardId": "TWITCH_GENERATED_COMMAND_ID_3",
            "action": {
                "performAtRandom": [
                    {
                        "name": "action A",
                        "messageToWhisper": "Hey bot, play sound A"
                    },
                    {
                        "name": "action B",
                        "messageToWhisper": "Hey bot, play sound B"
                    },
                    {
                        "name": "action C",
                        "keysToPress": "alt shift 1"
                    }
                ]
            }
        }
    ]
}

const App = (props) => {
    const [formData, setFormData] = useState(defaultJson);

    return <Container>
        <Grid container spacing={3}>
            <Grid item xs={7}>
                <Form schema={props.schema}
                      formData={formData}
                      onChange={log("changed")}
                      onSubmit={(a) => {
                          setFormData(a.formData)
                        }
                      }
                  onError={log("errors")}/>
            </Grid>
            <Grid item xs={3}>
                <div>
                    <pre>{JSON.stringify(formData, null, 4)}</pre>
                </div>
            </Grid>
        </Grid>
    </Container>;
}

export default App;
