import React, { Component } from 'react';

import "./style.css"
import { Button, Div, Text } from "../../component";
import logo from "./images/cbi.png"

class Login extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            username: "",
            password: ""
         }
    }

    render() { 
        const { username, password } = this.state;

        return ( 
            <Div class="container-login">
                <Div class="header-login">            
                    <img src={logo} className="image" alt="image" width="80px"/>
                    <span>SIGN IN</span>
                </Div>
                <Div class="form-login">
                    <Div class="field-login">
                        <Text value={username} class="input-login" name="username" id="username" placeholder="Username" onChange={this.setValue} />
                        <span id="valid-username"></span>
                    </Div>
                    <Div class="field-login">
                        <input type="password" value={password} className="input-login" name="password" id="password" placeholder="Password" onChange={this.setValue} />
                        <span id="valid-password"></span>
                    </Div>
                    <Button name="login" id="login" class="button-login" value="Login" onClick={() => this.props.doLogin(username, password)} />
                </Div>
                <Div class="footer-login"></Div>
            </Div>
         );
    }
}
 
export default Login;