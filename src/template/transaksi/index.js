import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';

import { Text, Div, TableRow, TableData, Button } from '../../component';

class Transaksi extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            transaksi: [],
            cari: ""
         }
    }

    componentDidMount() {
        // fetch('http://localhost:8080/market/transaksi/', {
        //     method: "get",
        //     headers: {
        //          "Content-Type": "application/json; ; charset=utf-8",
        //          "Access-Control-Allow-Headers": "Authorization, Content-Type",
        //          "Access-Control-Allow-Origin": "*"
        //     }
        // })
        // .then(response => response.json())
        // .then(json => {
        //     this.setState({ transaksi: json })
        // })
        // .catch((e) => {
        //     alert("Failed fetching data!!", e)
        // });
    }

    render() { 
        return ( 
            <Div class="table-data">
                <Div class="judul">
                    <p>Data Pengguna</p>
                </Div>
                <Div class="cari">
                    <label className="judul">Cari berdasarkan ID: </label>
                    <Text name="cari" id="cari" class="input" placeholder="Cari..." value={this.state.cari} onChange={event => this.onChangeCari(event.target.value)}  />
                    <Button value="Cari" class="button-search" id="cari" name="cari" onClick={this.onClickSearch} />
                    <NavLink to="/transaksi/form" className="link">Tambah</NavLink>
                </Div>
                <table className="table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Waktu</th>
                            <th>Customer No</th>
                            <th>Total Transaksi</th>
                            <th>Aksi</th>
                        </tr>
                    </thead>
                    <tbody>
                    {
                            this.state.transaksi.map((value, index) => {
                                return (
                                    <TableRow key={index}>
                                        <TableData>
                                            <NavLink to={"/transaksi/form/" + value.id} className="link">{value.id}</NavLink>
                                        </TableData>
                                        <TableData>{value.waktu}</TableData>
                                        <TableData>{value.customer.id}</TableData>
                                        <TableData>{value.total}</TableData>
                                        <TableData>
                                            <Button value="Update" class="button-submit" id="barang-update" name="barang-update" />
                                            <Button value="Delete" class="button-submit" id="barang-delete" name="barang-delete" />
                                        </TableData>
                                    </TableRow>
                                )
                            })
                        }
                    </tbody>
                </table>
            </Div>
         );
    }
}
 
export default Transaksi;