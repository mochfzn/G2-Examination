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
            pengguna: { },
            username: "",
            password: "",
         }
    }

    doLogin = () => {
        fetch(`http://localhost:8080/market/pengguna/?username=${encodeURIComponent(this.state.username)}&password=${encodeURIComponent(this.state.password)}`, {
            method: "get",
            headers: {
                "Content-Type": "application/json; ; charset=utf-8",
                "Access-Control-Allow-Headers": "Authorization, Content-Type",
                "Access-Control-Allow-Origin": "*"
            }
        })
        .then(response => response.json())
        .then(json => {
            this.setState({ 
                pengguna: json
            });

            if(typeof json.errorMessage !== 'undefined')
            {
                alert(json.errorMessage);
            }

            if(this.state.pengguna.akses === "admin")
            {
                this.props.changeLogin("admin", this.state.pengguna.kasir);
            }
            else if(this.state.pengguna.akses === "kasir")
            {
                this.props.changeLogin("kasir", this.state.pengguna.kasir);
            }
        })
        .catch((e) => {
            console.log(e);
            alert("Failed fetching data!!");
        });
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
    changeLogin: (akses, kasir) => dispatch({ type: "login-berhasil", akses: akses, kasir: kasir })
});
 
export default connect(mapStateToProps, mapDispatchToProps)(Login);