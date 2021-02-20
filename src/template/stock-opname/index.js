import React, { Component } from 'react';
import { Text, Div, TableRow, TableData, Button } from '../../component';
import { NavLink } from 'react-router-dom';

class StockOpname extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            barang: [],
         }
    }

    componentDidMount() {
        fetch('http://localhost:8080/market/barang/', {
            method: "get",
            headers: {
                 "Content-Type": "application/json; ; charset=utf-8",
                 "Access-Control-Allow-Headers": "Authorization, Content-Type",
                 "Access-Control-Allow-Origin": "*"
            }
        })
        .then(response => response.json())
        .then(json => {
            this.setState({ barang: json })
        })
        .catch((e) => {
            alert("Failed fetching data!!", e)
        });
    }

    render() { 
        return ( 
            <Div class="table-data">
                <Div class="judul">
                    <p>Data Pengguna</p>
                </Div>
                <Div class="cari">
                    <label className="judul">Cari: </label>
                    <Text name="cari" id="cari" class="input" placeholder="Cari..." />
                    <Button value="Cari" class="button-search" />
                    <NavLink to="/stock-opname/form" className="link">Tambah</NavLink>
                </Div>
                <table className="table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nama</th>
                            <th>Harga</th>
                            <th>Jumlah</th>
                        </tr>
                    </thead>
                    <tbody>
                    {
                            this.state.barang.map((value, index) => {
                                return (
                                    <TableRow key={index}>
                                        <TableData>
                                            <NavLink to={"/stock-opname/form/" + value.id} className="link">{value.id}</NavLink>
                                        </TableData>
                                        <TableData>{value.nama}</TableData>
                                        <TableData>{value.harga}</TableData>
                                        <TableData>{value.jumlah}</TableData>
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
 
export default StockOpname;