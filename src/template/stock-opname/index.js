import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';
import { Redirect } from 'react-router-dom';
import { connect } from "react-redux"

import { Text, Div, TableRow, TableData, Button } from '../../component';
import { Helper } from '../index';

class StockOpname extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            barang: [],
            cari: ""
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

    onChangeCari = nilai => {
        this.setState({
            cari: nilai
        });
    }

    onClickSearch = () => {
        if(this.state.cari === "")
        {
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
                this.setState({ 
                    barang: json
                });
            })
            .catch((e) => {
                console.log(e);
                alert("Failed fetching data!!");
            });
        }
        else 
        {
            fetch('http://localhost:8080/market/barang/' + this.state.cari, {
                method: "get",
                headers: {
                    "Content-Type": "application/json; ; charset=utf-8",
                    "Access-Control-Allow-Headers": "Authorization, Content-Type",
                    "Access-Control-Allow-Origin": "*"
                }
            })
            .then(response => response.json())
            .then(json => {
                let barang = [];
                barang.push(json);
    
                this.setState({ 
                    barang
                });
            })
            .catch((e) => {
                console.log(e);
                alert("Failed fetching data!!");
            });
        }
        
    }

    render() { 
        if(this.props.isLogin === false) {
            return <Redirect to="/" />
        }

        return ( 
            <React.Fragment>
                <Helper />
                <Div class="table-data">
                    <Div class="judul">
                        <p>Data Pengguna</p>
                    </Div>
                    <Div class="cari">
                        <label className="judul">Cari berdasarkan ID: </label>
                        <Text name="cari" id="cari" class="input" placeholder="Cari..." value={this.state.cari} onChange={event => this.onChangeCari(event.target.value)}  />
                        <Button value="Cari" class="button-search" id="cari" name="cari" onClick={this.onClickSearch} />
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
            </React.Fragment>
         );
    }
}
 
const mapStateToProps = state => {
    return {
        isLogin: state.Auth.statusLogin,
        akses: state.Auth.akses
    }
}

export default connect(mapStateToProps)(StockOpname);