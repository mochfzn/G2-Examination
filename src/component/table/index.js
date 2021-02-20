import React, { Component } from 'react';

class Table extends Component {
    constructor(props) {
        super(props);
        this.state = {  }
    }
    render() { 
        return ( 
            <table className={this.props.class}>{this.props.children}</table>
         );
    }
}
 
export default Table;