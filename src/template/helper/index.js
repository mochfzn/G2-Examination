import React, { Component } from 'react';
import { NavLink, Redirect } from 'react-router-dom';
import { connect } from "react-redux"

import { Button, Div } from '../../component';

class Helper extends Component {
    constructor(props) {
        super(props);
        this.state = { }
    }

    doLogout = () => {
        this.props.changeLogin();
    }

    render() { 
        if(this.props.isLogin === false)
        {
            return (
                <Redirect to="/" />
            )
        }
        else if(this.props.akses === "kasir")
        {
            return (
                <Div class="helper"> 
                    <NavLink to="/stock-opname" className="link">Stock Opname</NavLink>
                    <NavLink to="/transaksi" className="link">Transaksi</NavLink>
                    <Button value="Logout" id="logout" name="logout" class="button" onClick={this.doLogout} />
                </Div>
            )
        }
        else 
        {
            return ( 
                <Div class="helper">
                    <NavLink to="/customer" className="link">Customer</NavLink>
                    <NavLink to="/barang" className="link">Barang</NavLink>
                    <NavLink to="/kasir" className="link">Kasir</NavLink> 
                    <NavLink to="/stock-opname" className="link">Stock Opname</NavLink>
                    <NavLink to="/transaksi" className="link">Transaksi</NavLink>
                    <Button value="Logout" id="logout" name="logout" class="button" onClick={this.doLogout} />
                </Div>
             );
        }
    }
}

const mapStateToProps = state => {
    return {
        isLogin: state.Auth.statusLogin,
        akses: state.Auth.akses
    }
}

const mapDispatchToProps = dispatch => ({
    changeLogin: () => dispatch({ type: "logout-berhasil" })
});
 
export default connect(mapStateToProps, mapDispatchToProps)(Helper);