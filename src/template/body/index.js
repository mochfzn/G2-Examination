import React, { Component } from 'react';

import { Button, Div } from '../../component';
import { MasterBarang, MasterCustomer, MasterKasir } from '../index'
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
                    <Button value="Logout" id="logout" name="logout" class="button" onClick={this.props.doLogout} />
                </Div>
                <MasterBarang />
            </React.Fragment>
         );
    }
}
 
export default Body;