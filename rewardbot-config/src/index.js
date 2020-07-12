import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';

fetch("https://raw.githubusercontent.com/miscounting/RewardBotJ/master/src/main/resources/schema/config.schema.json",
    {mode: "cors"})
    .then(response => response.json())
    .then(schema => {
      ReactDOM.render(
        <App schema = {schema} />,
        document.getElementById('root')
      );
    } )
    .catch(err => {
        console.log(err)
    });



// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
