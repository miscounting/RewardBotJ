import React, {useState} from 'react';
import Form from "@rjsf/material-ui";
import Container from '@material-ui/core/Container';
import Grid from '@material-ui/core/Grid';

const log = (type) => console.log.bind(console, type);

const defaultJson = {
    "bot_name": "Your bot's twitch ID, if you have one",
    "channel": "Your twitch ID",
    "oauth": "your secret OAUTH token",
    "commands": [
        {
            "optionalName": "press some keys command",
            "twitchRewardId": "The ID twitch uses for your points reward",
            "action": {
                "name": "What I want to happen",
                "keysToPress": "shift control alt meta 1",
                "messageToWhisper": "!icanwhispertoyourbot",
                "performAtRandom": [
                    {
                        "name": "action A",
                        "messageToWhisper": "Hey bot, play sound A"
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
