import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch, useParams, useHistory, Redirect, NavLink } from 'react-router-dom';

import { Button, Div } from '../../component';
import { Login, Barang, Customer, Kasir, StockOpnameForm, StockOpname, Transaksi, TransaksiForm } from '../index'
import './style.css';


class Body extends Component {
    constructor(props) {
        super(props);
        this.state = {  }
    }

    render() { 
        return ( 
            <React.Fragment>
                <Div class="helper">
                    <NavLink to="/customer" className="link">Customer</NavLink>
                    <NavLink to="/barang" className="link">Barang</NavLink>
                    <NavLink to="/kasir" className="link">Kasir</NavLink> 
                    <NavLink to="/stock-opname" className="link">Stock Opname</NavLink>
                    <NavLink to="/transaksi" className="link">Transaksi</NavLink>
                    <Button value="Logout" id="logout" name="logout" class="button" />
                </Div>
                
                <Switch>
                    <Route path="/barang" component={Barang} />
                    <Route path="/customer" component={Customer} />
                    <Route path="/kasir" component={Kasir} />
                    <Route path="/stock-opname/form/:id" component={StockOpnameForm} />
                    <Route path="/stock-opname/form" component={StockOpnameForm} />
                    <Route path="/transaksi/form" component={TransaksiForm} />
                    <Route path="/stock-opname" component={StockOpname} />
                    <Route path="/transaksi" component={Transaksi} />
                    <Route path="/" component={Barang} />
                </Switch>
                
            </React.Fragment>
         );
    }
}
 
export default Body;