import React, { Component } from 'react';
import { Button, Paragraph, Div, Text } from '../../component';
import './style.css';

class StockOpnameForm extends Component {
    constructor(props) {
        super(props);
        this.state = {  }
    }

    componentDidMount() {
        
    }

    render() { 
        return ( 
            <Div class="stock-opname">
                <Div class="judul">
                    <Paragraph>Formulir Pengguna</Paragraph>
                </Div>
                <Div class="form">
                    <Div class="row">
                        <Text name="opname-id" id="opname-id" class="input" placeholder="ID" disabled="disabled" />
                        <Text name="opname-nama" id="opname-nama" class="input" placeholder="Nama" disabled="disabled" />
                        <Text name="opname-waktu" id="opname-waktu" class="input" placeholder="Waktu" disabled="disabled" />
                    </Div>
                    <Div class="big-row">
                        <Div class="col">
                            <Text name="opname-jml-sebelum" id="opname-jml-sebelum" class="input" placeholder="Jumlah Sebelum" disabled="disabled" />
                            <Text name="opname-jml-sesudah" id="opname-jml-sesudah" class="input" placeholder="Jumlah Sesudah" />
                        </Div>
                        <Div class="col">
                            <textarea name="opname-alasan" id="opname-alasan" class="textarea" placeholder="Alasan"></textarea>
                        </Div>
                    </Div>
                </Div>
                <Div class="tombol">
                    <Button value="Save" class="button-submit" />
                    <Button value="Clear Form" class="button-clear" />
                </Div>
            </Div>
         );
    }
}
 
export default StockOpnameForm;