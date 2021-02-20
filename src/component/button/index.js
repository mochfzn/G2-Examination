import React, { Component } from 'react';

class Button extends Component {
    constructor(props) {
        super(props);
        this.state = {  }
    }
    render() { 
        return ( 
            <input type="button" value={this.props.value} className={this.props.class} id={this.props.id} onClick={this.props.onClick} />
         );
    }
}
 
export default Button;