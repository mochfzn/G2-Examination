import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch, Redirect } from 'react-router-dom';

import { Button, Div } from '../../component';
import { Login, Barang, Customer, Kasir, StockOpnameForm, StockOpname } from '../index'
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
                    <Button value="Logout" id="logout" name="logout" class="button" />
                </Div>
                <Router>
                    <Switch>
                        <Route path="/barang">
                            <Barang />
                        </Route>
                        <Route path="/customer">
                            <Customer />
                        </Route>
                        <Route path="/kasir">
                            <Kasir />
                        </Route>
                        <Route path="/stock-opname">
                            <StockOpname />
                        </Route>
                        <Route path="/stock-opname/form">
                            <StockOpnameForm />
                        </Route>
                        <Route path="/">
                            <Barang />
                        </Route>
                    </Switch>
                </Router>
            </React.Fragment>
         );
    }
}
 
export default Body;