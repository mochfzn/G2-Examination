import React, { Component } from 'react';
import { Redirect, withRouter } from 'react-router-dom';
import { connect } from "react-redux"

import { Button, Paragraph, Div, Text, Select, Option } from '../../component';
import { Helper } from '../index';
import './style.css';

class StockOpnameForm extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id: "",
            nama: "",
            harga: "",
            jumlah: "",
            waktu: "",
            jumlahSekarang: "",
            alasan: "",
            barang: []
        }
        this.today = null;
    }

    componentDidMount() {
        if (typeof this.props.match.params.id !== 'undefined') {
            fetch('http://localhost:8080/market/barang/' + this.props.match.params.id, {
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
                        id: json.id,
                        nama: json.nama,
                        harga: json.harga,
                        jumlah: json.jumlah
                    });

                })
                .catch((e) => {
                    alert("Failed fetching data!!", e)
                });
        }
        else {
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
                    alert("Failed fetching data!!", e)
                });
        }

        this.setWaktu();
    }

    onChangeText = (attribut, nilai) => {
        this.setState({
            [attribut]: nilai
        });
    }

    setWaktu = () => {
        this.today = new Date();
        const tahun = this.today.getFullYear();
        const bulan = this.today.getMonth();
        const tanggal = this.today.getDate();
        const jam = this.today.getHours();
        const menit = this.today.getMinutes();
        const detik = this.today.getSeconds();

        let namaBulan;

        switch (bulan) {
            case 0: namaBulan = "Jan"; break;
            case 1: namaBulan = "Feb"; break;
            case 2: namaBulan = "Mar"; break;
            case 3: namaBulan = "Apr"; break;
            case 4: namaBulan = "Mei"; break;
            case 5: namaBulan = "Jun"; break;
            case 6: namaBulan = "Jul"; break;
            case 7: namaBulan = "Agu"; break;
            case 8: namaBulan = "Sep"; break;
            case 9: namaBulan = "Okt"; break;
            case 10: namaBulan = "Nov"; break;
            case 11: namaBulan = "Des"; break;
        }

        let tampilanTanggal = tanggal + " " + namaBulan + " " + tahun;
        tampilanTanggal += " " + jam + ":" + menit + ":" + detik;

        this.setState({
            waktu: tampilanTanggal
        });
    }

    onClickSave = () => {
        if (this.state.jumlahSekarang === "") {
            alert("Jumlah saat ini tidak boleh kosong!");
        }
        else if (this.state.alasan === "") {
            alert("Alasan tidak boleh kosong!");
        }
        else {
            const objek = {
                barang: {
                    id: this.state.id,
                    nama: this.state.nama,
                    harga: this.state.harga,
                    jumlah: this.state.jumlah
                },
                jumlah: this.state.jumlahSekarang,
                waktu: "2021-02-21T01:01:50",
                alasan: this.state.alasan
            }

            fetch('http://localhost:8080/market/stock-opname/', {
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
                    this.props.history.push("/stock-opname");
                })
                .catch(() => {
                    alert("Failed sending data!!");
                });
        }
    }

    onChangeSelect = value => {
        fetch('http://localhost:8080/market/barang/' + value, {
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
                    id: json.id,
                    nama: json.nama,
                    harga: json.harga,
                    jumlah: json.jumlah
                });

            })
            .catch((e) => {
                alert("Failed fetching data!!", e)
            });
    }

    onClickClear = () => {
        this.setState({
            jumlahSekarang: "",
            alasan: ""
        })
    }

    render() {
        if(this.props.isLogin === false) {
            return <Redirect to="/" />
        }

        const { id, nama, jumlah, waktu, jumlahSekarang, alasan } = this.state;

        if (typeof this.props.match.params.id === 'undefined') {
            return (
                <React.Fragment>
                    <Helper />
                    <Div class="stock-opname">
                        <Div class="judul">
                            <Paragraph>Stock Opname</Paragraph>
                        </Div>
                        <Div class="form">
                            <Div class="row">
                                <Select id="opname-id-semua" name="opname-id-semua" class="select" onChange={event => this.onChangeSelect(event.target.value)}>
                                    <Option value="">Pilih</Option>
                                    {
                                        this.state.barang.map((value, index) => {
                                            return (
                                                <Option key={index} value={value.id}>{value.nama}</Option>
                                            )
                                        })
                                    }
                                </Select>
                                <Text name="opname-nama" id="opname-nama" class="input" placeholder="Nama" disabled="disabled" value={nama} onChange={() => this.onChangeText("nama", nama)} />
                                <Text name="opname-waktu" id="opname-waktu" class="input" placeholder="Waktu" disabled="disabled" value={waktu} onChange={this.setWaktu} />
                            </Div>
                            <Div class="big-row">
                                <Div class="col">
                                    <Text name="opname-jml-sebelum" id="opname-jml-sebelum" class="input" placeholder="Jumlah Sebelum" disabled="disabled" value={jumlah} onChange={() => this.onChangeText("jumlah", jumlah)} />
                                    <Text name="opname-jml-sesudah" id="opname-jml-sesudah" class="input" placeholder="Jumlah Sesudah" value={jumlahSekarang} onChange={event => this.onChangeText("jumlahSekarang", event.target.value)} />
                                </Div>
                                <Div class="col">
                                    <textarea name="opname-alasan" id="opname-alasan" className="textarea" value={alasan} onChange={event => this.onChangeText("alasan", event.target.value)} placeholder="Alasan"></textarea>
                                </Div>
                            </Div>
                        </Div>
                        <Div class="tombol">
                            <Button value="Save" class="button-submit" id="opname-submit" name="opname-submit" onClick={this.onClickSave} />
                            <Button value="Clear Form" class="button-clear" id="opname-clear" name="opname-clear" onClick={this.onClickClear} />
                        </Div>
                    </Div>
                </React.Fragment>
            )
        }
        else {
            return (
                <Div class="stock-opname">
                    <Div class="judul">
                        <Paragraph>Stock Opname</Paragraph>
                    </Div>
                    <Div class="form">
                        <Div class="row">
                            <Text name="opname-id" id="opname-id" class="input" placeholder="ID" disabled="disabled" value={id} onChange={() => this.onChangeText("id", id)} />
                            <Text name="opname-nama" id="opname-nama" class="input" placeholder="Nama" disabled="disabled" value={nama} onChange={() => this.onChangeText("nama", nama)} />
                            <Text name="opname-waktu" id="opname-waktu" class="input" placeholder="Waktu" disabled="disabled" value={waktu} onChange={this.setWaktu} />
                        </Div>
                        <Div class="big-row">
                            <Div class="col">
                                <Text name="opname-jml-sebelum" id="opname-jml-sebelum" class="input" placeholder="Jumlah Sebelum" disabled="disabled" value={jumlah} onChange={() => this.onChangeText("jumlah", jumlah)} />
                                <Text name="opname-jml-sesudah" id="opname-jml-sesudah" class="input" placeholder="Jumlah Sesudah" value={jumlahSekarang} onChange={event => this.onChangeText("jumlahSekarang", event.target.value)} />
                            </Div>
                            <Div class="col">
                                <textarea name="opname-alasan" id="opname-alasan" className="textarea" value={alasan} onChange={event => this.onChangeText("alasan", event.target.value)} placeholder="Alasan"></textarea>
                            </Div>
                        </Div>
                    </Div>
                    <Div class="tombol">
                        <Button value="Save" class="button-submit" id="opname-submit" name="opname-submit" onClick={this.onClickSave} />
                        <Button value="Clear Form" class="button-clear" id="opname-clear" name="opname-clear" onClick={this.onClickClear} />
                    </Div>
                </Div>
            )
        }
    }
}

const mapStateToProps = state => {
    return {
        isLogin: state.Auth.statusLogin,
        akses: state.Auth.akses
    }
}

export default connect(mapStateToProps)(withRouter(StockOpnameForm));