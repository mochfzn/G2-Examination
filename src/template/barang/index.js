import React, { Component } from 'react';

import { Text, Div, Paragraph, Table, TableRow, TableData, Button, Select, Option } from '../../component';

class Barang extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            barang: [],
            id: "",
            nama: "",
            harga: "",
            jumlah: ""
         }
         this.valueSubmit = "Save";
         this.valueSelect = "id";
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

    onClickSubmit = () => {
        if(this.state.nama === "") 
        {
            alert("Nama tidak boleh kosong!");
        }
        else if(this.state.harga === "") 
        {
            alert("Harga tidak boleh kosong!");
        }
        else if(this.state.jumlah === "") 
        {
            alert("Jumlah tidak boleh kosong!");
            return false;
        }
        else 
        {
            const objek = {
                id: this.state.id,
                nama: this.state.nama,
                harga: this.state.harga,
                jumlah: this.state.jumlah
            };

            if(this.valueSubmit === "Save") 
            {
                fetch('http://localhost:8080/market/barang/', {
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
                        this.setState({ barang: json });
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
                this.valueSubmit = "Save";

                fetch('http://localhost:8080/market/barang/' + this.state.id, {
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
                        this.setState({ barang: json });
                    })
                    .catch(() => {
                        alert("Failed fetching data!!");
                    });
                })
                .catch(() => {
                    alert("Failed sending data!!");
                });
            }
        }

        this.onClickClear();
    }

    onClickUpdate = objek => {
        this.valueSubmit = "Update";
        this.setState({
            id: objek.id,
            nama: objek.nama,
            harga: objek.harga,
            jumlah: objek.jumlah
        });
    }

    onClickDelete = id => {
        if(window.confirm("Apakah anda ingin menghapus data tersebut?"))
        {
            fetch('http://localhost:8080/market/barang/' + id, {
                method: "delete",
                headers: {
                    "Content-Type": "application/json; ; charset=utf-8",
                    "Access-Control-Allow-Headers": "Authorization, Content-Type",
                    "Access-Control-Allow-Origin": "*"
                }
            })
            .then(() => {
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
                    this.setState({ barang: json });
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
    }

    onClickClear = () => {
        this.setState({ 
            nama: "",
            harga: "",
            jumlah: ""
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
                this.setState({ barang: json });
            })
            .catch((e) => {
                console.log(e);
                alert("Failed fetching data!!");
            });
        }
        else if(this.valueSelect === "id")
        {
            fetch('http://localhost:8080/market/barang/' + nilai, {
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
        else if(this.valueSelect === "nama")
        {
            fetch('http://localhost:8080/market/barang/nama/' + nilai, {
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
    }

    render() { 
        const { nama, harga, jumlah } = this.state;

        return ( 
            <React.Fragment>
                <Div class="form-data">
                    <Div class="judul">
                        <Paragraph>Formulir Pengguna</Paragraph>
                    </Div>
                    <Div class="form">
                        <Text name="barang-nama" id="barang-nama" class="input" placeholder="Nama" value={nama} onChange={el => this.onChangeText(el, "nama")} />
                        <Text name="barang-harga" id="barang-harga" class="input" placeholder="Harga" value={harga} onChange={el => this.onChangeText(el, "harga")} />
                        <Text name="barang-jumlah" id="barang-jumlah" class="input" placeholder="Jumlah" value={jumlah} onChange={el => this.onChangeText(el, "jumlah")} />
                    </Div>
                    <Div class="tombol">
                        <Button value="Clear Form" class="button-clear" id="barang-clear" name="barang-clear" onClick={this.onClickClear} />
                        <Button value={this.valueSubmit} class="button-submit" id="barang-submit" name="barang-submit" onClick={this.onClickSubmit} />
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
                                <th>Harga</th>
                                <th>Jumlah</th>
                                <th>Aksi</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.barang.map((value, index) => {
                                    return (
                                        <TableRow key={index}>
                                            <TableData>{value.id}</TableData>
                                            <TableData>{value.nama}</TableData>
                                            <TableData>{value.harga}</TableData>
                                            <TableData>{value.jumlah}</TableData>
                                            <TableData>
                                                <Button value="Update" class="button-submit" id="barang-update" name="barang-update" onClick={() => this.onClickUpdate(value)} />
                                                <Button value="Delete" class="button-submit" id="barang-delete" name="barang-delete" onClick={() => this.onClickDelete(value.id)} />
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
 
export default Barang;