import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch, useParams, useHistory, Redirect, NavLink } from 'react-router-dom';

import { Button, Div } from '../../component';
import { Login, Barang, Customer, Kasir, StockOpnameForm, StockOpname } from '../index'
import './style.css';


class Body extends Component {
    constructor(props) {
        super(props);
        this.state = {  }
    }

    render() { 
        const style = {
            width:'110px', 
            height:'40px', 
            color:'lightcoral', 
            border:'2px solid lightcoral', 
            backgroundColor:'rgba(0,0,0,0)', 
            borderRadius:'20px', 
            float:'left',
            textDecoration: 'none',
            lineHeight: '35px',
            textAlign: 'center',
            marginRight: '20px'
        }

        return ( 
            <React.Fragment>
                <Div class="helper">
                    {/* <Button value="Barang" id="logout" name="logout" class="button" />
                    <Button value="Customer" id="logout" name="logout" class="button" />
                    <Button value="Kasir" id="logout" name="logout" class="button" />
                    <Button value="Logout" id="logout" name="logout" class="button" /> */}

                    <NavLink to="/customer" style={style}>Customer</NavLink>
                    <NavLink to="/barang" style={style}>Barang</NavLink>
                    <NavLink to="/kasir" style={style}>Kasir</NavLink> 
                    <NavLink to="/stock-opname" style={style}>Stock Opname</NavLink>
                </Div>
                
                    <Switch>
                        <Route path="/barang" component={Barang} />
                        <Route path="/customer" component={Customer} />
                        <Route path="/kasir" component={Kasir} />
                        <Route path="/stock-opname/form/:id" component={StockOpnameForm} />
                        <Route path="/stock-opname/form" component={StockOpnameForm} />
                        <Route path="/stock-opname" component={StockOpname} />
                        {/* <Route path="/stock-opname-form/:id" render={(props) => <StockOpnameForm {...props}/>} /> */}
                        <Route path="/" component={Barang} />
                    </Switch>
                
            </React.Fragment>
         );
    }
}
 
export default Body;