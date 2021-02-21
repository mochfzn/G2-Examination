import React, { Component } from 'react';
import { Redirect } from 'react-router-dom';
import { connect } from "react-redux"

import { Text, Div, Paragraph, Table, TableRow, TableData, Button, Select, Option } from '../../component';
import  { Helper } from '../index';

class Customer extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            customer: [],
            id: "",
            nama: "",
            alamat: "",
            telepon: ""
         }
        this.valueSubmit = "Save";
        this.valueSelect = "id";
    }

    componentDidMount() {
        fetch('http://localhost:8080/market/customer/', {
            method: "get",
            headers: {
                 "Content-Type": "application/json; ; charset=utf-8",
                 "Access-Control-Allow-Headers": "Authorization, Content-Type",
                 "Access-Control-Allow-Origin": "*"
            }
        })
        .then(response => response.json())
        .then(json => {
            this.setState({ customer: json })
        })
        .catch((e) => {
            alert("Failed fetching data!!", e)
        });
    }

    onClickSubmit = () => {
        if(this.state.nama === "") 
        {
            alert("Nama tidak boleh kosong!");
        }
        else if(this.state.alamat === "") 
        {
            alert("Alamat tidak boleh kosong!");
        }
        else if(this.state.telepon === "") 
        {
            alert("Telepon tidak boleh kosong!");
            return false;
        }
        else 
        {
            const objek = {
                id: this.state.id,
                nama: this.state.nama,
                alamat: this.state.alamat,
                telepon: this.state.telepon
            };

            if(this.valueSubmit === "Save") 
            {
                fetch('http://localhost:8080/market/customer/', {
                    method: "post",
                    headers: {
                        "Content-Type": "application/json; ; charset=utf-8",
                        "Access-Control-Allow-Headers": "Authorization, Content-Type",
                        "Access-Control-Allow-Origin": "*"
                    },
                    body: JSON.stringify(objek)
                })
                .then(response => response.json())
                .then(() => {
                    fetch('http://localhost:8080/market/customer/', {
                        method: "get",
                        headers: {
                            "Content-Type": "application/json; ; charset=utf-8",
                            "Access-Control-Allow-Headers": "Authorization, Content-Type",
                            "Access-Control-Allow-Origin": "*"
                        }
                    })
                    .then(response => response.json())
                    .then(json => {
                        this.setState({ customer: json });
                    })
                    .catch(() => {
                        alert("Failed fetching data!!");
                    });
                })
                .catch(() => {
                    alert("Failed sending data!!");
                });
            }
            else if(this.valueSubmit === "Update")
            {
                fetch('http://localhost:8080/market/customer/' + this.state.id, {
                    method: "put",
                    headers: {
                        "Content-Type": "application/json; ; charset=utf-8",
                        "Access-Control-Allow-Headers": "Authorization, Content-Type",
                        "Access-Control-Allow-Origin": "*"
                    },
                    body: JSON.stringify(objek)
                })
                .then(response => response.json())
                .then(() => {
                    fetch('http://localhost:8080/market/customer/', {
                        method: "get",
                        headers: {
                            "Content-Type": "application/json; ; charset=utf-8",
                            "Access-Control-Allow-Headers": "Authorization, Content-Type",
                            "Access-Control-Allow-Origin": "*"
                        }
                    })
                    .then(response => response.json())
                    .then(json => {
                        this.setState({ customer: json });
                    })
                    .catch(() => {
                        alert("Failed fetching data!!");
                    });
                })
                .catch(() => {
                    alert("Failed sending data!!");
                });

                this.valueSubmit = "Save";
            }
        }

        this.onClickClear();
    }

    onClickUpdate = objek => {
        this.valueSubmit = "Update";
        this.setState({
            id: objek.id,
            nama: objek.nama,
            alamat: objek.alamat,
            telepon: objek.telepon
        });
    }

    onClickDelete = id => {
        fetch('http://localhost:8080/market/customer/' + id, {
            method: "delete",
            headers: {
                "Content-Type": "application/json; ; charset=utf-8",
                "Access-Control-Allow-Headers": "Authorization, Content-Type",
                "Access-Control-Allow-Origin": "*"
            }
        })
        .then(() => {
            fetch('http://localhost:8080/market/customer/', {
                method: "get",
                headers: {
                    "Content-Type": "application/json; ; charset=utf-8",
                    "Access-Control-Allow-Headers": "Authorization, Content-Type",
                    "Access-Control-Allow-Origin": "*"
                }
            })
            .then(response => response.json())
            .then(json => {
                this.setState({ customer: json });
            })
            .catch((e) => {
                console.log(e);
                alert("Failed fetching data!!");
            });
        })
        .catch((e) => {
            console.log(e);
            alert("Failed sending data!!");
        });
    }

    onClickClear = () => {
        this.setState({ 
            nama: "",
            alamat: "",
            telepon: ""
         });
    }

    onChangeText = (el, attribut) => {
        this.setState({
            [attribut]: el.target.value
        });
    }

    onChangeSelect = el => {
        this.valueSelect = el.target.value;
    }

    onChangeSearch = el => {
        const nilai = el.target.value;

        if(nilai === "") 
        {
            fetch('http://localhost:8080/market/customer/', {
                method: "get",
                headers: {
                    "Content-Type": "application/json; ; charset=utf-8",
                    "Access-Control-Allow-Headers": "Authorization, Content-Type",
                    "Access-Control-Allow-Origin": "*"
                }
            })
            .then(response => response.json())
            .then(json => {
                this.setState({ customer: json });
            })
            .catch((e) => {
                console.log(e);
                alert("Failed fetching data!!");
            });
        }
        else if(this.valueSelect === "id")
        {
            fetch('http://localhost:8080/market/customer/' + nilai, {
                method: "get",
                headers: {
                    "Content-Type": "application/json; ; charset=utf-8",
                    "Access-Control-Allow-Headers": "Authorization, Content-Type",
                    "Access-Control-Allow-Origin": "*"
                }
            })
            .then(response => response.json())
            .then(json => {
                let customer = [];
                customer.push(json);

                this.setState({ 
                    customer
                });
            })
            .catch((e) => {
                console.log(e);
                alert("Failed fetching data!!");
            });
        }
        else if(this.valueSelect === "nama")
        {
            fetch('http://localhost:8080/market/customer/nama/' + nilai, {
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
                    customer: json 
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
        
        const { nama, alamat, telepon } = this.state;

        return ( 
            <React.Fragment>
                <Helper />
                <Div class="form-data">
                    <Div class="judul">
                        <Paragraph>Formulir Pengguna</Paragraph>
                    </Div>
                    <Div class="form">
                        <Text name="customer-nama" id="customer-nama" value={nama} class="input" placeholder="Nama" onChange={el => this.onChangeText(el, "nama")} />
                        <Text name="customer-alamat" id="customer-alamat" value={alamat} class="input" placeholder="Alamat" onChange={el => this.onChangeText(el, "alamat")} />
                        <Text name="customer-telepon" id="customer-telepon" value={telepon} class="input" placeholder="Telepon" onChange={el => this.onChangeText(el, "telepon")} />
                    </Div>
                    <Div class="tombol">
                        <Button value="Clear Form" class="button-clear" id="customer-clear" name="customer-clear" onClick={this.onClickClear} />
                        <Button value={this.valueSubmit} class="button-submit" id="customer-submit" name="customer-submit" onClick={this.onClickSubmit} />
                    </Div>
                </Div>
                <Div class="table-data">
                    <Div class="judul">
                        <Paragraph>Data Pengguna</Paragraph>
                    </Div>
                    <Div class="cari">
                        <label className="judul">Cari: </label>
                        <Select id="option-cari" name="option-cari" class="select" onChange={this.onChangeSelect}>
                            <Option value="id">ID</Option>
                            <Option value="nama">Nama</Option>
                        </Select>
                        <Text name="cari" id="cari" class="input" placeholder="Cari..." onChange={el => this.onChangeSearch(el)}/>
                    </Div>
                    <Table class="table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nama</th>
                                <th>Alamat</th>
                                <th>Telepon</th>
                                <th>Aksi</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.customer.map((value, index) => {
                                    return (
                                        <TableRow key={index}>
                                            <TableData>{value.id}</TableData>
                                            <TableData>{value.nama}</TableData>
                                            <TableData>{value.alamat}</TableData>
                                            <TableData>{value.telepon}</TableData>
                                            <TableData>
                                                <Button value="Update" class="button-submit" id="customer-update" name="customer-update" onClick={() => this.onClickUpdate(value)} />
                                                <Button value="Delete" class="button-submit" id="customer-delete" name="customer-delete" onClick={() => this.onClickDelete(value.id)} />
                                            </TableData>
                                        </TableRow>
                                    )
                                })
                            }
                        </tbody>
                    </Table>
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
 
export default connect(mapStateToProps)(Customer);