import React, { Component } from 'react';
import { Redirect } from 'react-router-dom';
import { connect } from "react-redux"

import "./style.css"
import { Button, Div, Text } from "../../component";
import logo from "./images/cbi.png"

class Login extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            username: "",
            password: "",
         }
    }

    doLogin = () => {
        if((this.state.username === "admin") && (this.state.password === "Admin"))
        {
            this.props.changeLogin("admin");
        }
        else if((this.state.username === "kasir") && (this.state.password === "Kasir"))
        {
            this.props.changeLogin("kasir");
        }
        else
        {
            alert("Username atau password salah!");
        }
    }

    onChangeInput = (attribut, value) => {
        this.setState({
            [attribut]: value
        });
    }

    render() { 
        if(this.props.isLogin === true) {
            return (
                <Redirect to="/transaksi" />
            )
        }

        const { username, password } = this.state;

        return ( 
            <Div class="container-login">
                <Div class="header-login">            
                    <img src={logo} className="image" alt="image" width="80px"/>
                    <span>SIGN IN</span>
                </Div>
                <Div class="form-login">
                    <Div class="field-login">
                        <Text value={username} class="input-login" name="username" id="username" placeholder="Username" onChange={event => this.onChangeInput("username", event.target.value)} />
                        <span id="valid-username"></span>
                    </Div>
                    <Div class="field-login">
                        <input type="password" value={password} className="input-login" name="password" id="password" placeholder="Password" onChange={event => this.onChangeInput("password", event.target.value)} />
                        <span id="valid-password"></span>
                    </Div>
                    <Button name="login" id="login" class="button-login" value="Login" onClick={this.doLogin} />
                </Div>
                <Div class="footer-login"></Div>
            </Div>
         );
    }
}

const mapStateToProps = state => {
    return {
        isLogin: state.Auth.statusLogin,
        akses: state.Auth.akses
    }
}

const mapDispatchToProps = dispatch => ({
    changeLogin: akses => dispatch({ type: "login-berhasil", akses: akses })
});
 
export default connect(mapStateToProps, mapDispatchToProps)(Login);