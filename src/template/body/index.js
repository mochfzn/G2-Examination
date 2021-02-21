import React, { Component } from 'react';
import { Route, Switch } from 'react-router-dom';

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
                <Switch>
                    <Route path="/barang" component={Barang} />
                    <Route path="/customer" component={Customer} />
                    <Route path="/kasir" component={Kasir} />
                    <Route path="/stock-opname/form/:id" component={StockOpnameForm} />
                    <Route path="/stock-opname/form" component={StockOpnameForm} />
                    <Route path="/transaksi/form" component={TransaksiForm} />
                    <Route path="/stock-opname" component={StockOpname} />
                    <Route path="/transaksi" component={Transaksi} />
                    <Route path="/" component={Login} />
                </Switch>
            </React.Fragment>
         );
    }
}
 
export default Body;