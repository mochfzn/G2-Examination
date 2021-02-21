import React, { Component } from 'react';

import { Div, Paragraph, Label, Select, Option, Text, Button } from '../../component';
import './style.css';

class TransaksiForm extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            customers: [],
            goods: [],
            items: [
                {
                    barang: 
                    { 
                        id: "",
                        nama: "",
                        harga: "",
                        jumlah: ""
                     },
                    jumlah: "",
                    subTotal: "",
                }
            ],
            customer: {  },
            kasir: {
                id: "eb3f1bfb-b701-4562-bddf-35ed3ac901bf",
                nama: "Wibowo",
                alamat: "Kebon Kosong",
                telepon: "0895331652433"
            },
            waktu: "",
            total: ""
         }
         this.today = "";
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
            this.setState({ 
                customers: json
            });
        })
        .catch((e) => {
            alert("Failed fetching data!!", e)
        });

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
                goods: json
            });
            
        })
        .catch((e) => {
            alert("Failed fetching data!!", e)
        });

        this.setWaktu();
    }

    onChangeSelectCustomer = (id) => {
        let objek = this.state.customers.find(value => {
            return value.id === id;
        });

        this.setState({
            customer: objek
        });
    }

    onChangeInput = (attribut, value, index) => {
        this.state.items[index][attribut] = value;
        const newItems = this.state.items;
        this.setState({
            items: newItems
        });
    }

    onChangeInputTotal = (value) => {
        this.setState({
            total: value
        });
    }

    onChangeInputQuantity = (value, index) => {
        let newItems;

        this.state.items[index].jumlah = value;

        newItems = this.state.items;
        this.setState({
            items: newItems
        });

        if(!Number.isNaN(Number.parseInt(value))) 
        {
            const harga = this.state.items[index].barang.harga;
            let hitung = harga * value;
    
            this.state.items[index].subTotal = hitung;

            newItems = this.state.items;
            this.setState({
                items: newItems
            });
        } else {
            this.state.items[index].subTotal = "";

            newItems = this.state.items;
            this.setState({
                items: newItems
            });
        }

        this.calculateTotal();
    }

    calculateTotal = () => {
        let total = 0;
        this.state.items.forEach((value) => {
            total += value.subTotal;
        });

        this.setState({
            total
        });
    }

    onChangeSelectBarang = (barangId, index) => {
        let objekBarang = this.state.goods.find(value => {
            return value.id === barangId;
        });

        this.state.items[index].barang = objekBarang;

        let newItems = this.state.items;
        this.setState({
            items: newItems
        });
    }

    addChild = () => {
        const newItems = this.state.items;
        newItems.push({
            barang: { 
                id: "",
                nama: "",
                harga: "",
                jumlah: ""
             },
            jumlah: "",
            subTotal: "",
        });

        this.setState({
            items: newItems
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

        switch(bulan) {
            case 0 : namaBulan = "Jan"; break;
            case 1 : namaBulan = "Feb"; break;
            case 2 : namaBulan = "Mar"; break;
            case 3 : namaBulan = "Apr"; break;
            case 4 : namaBulan = "Mei"; break;
            case 5 : namaBulan = "Jun"; break;
            case 6 : namaBulan = "Jul"; break;
            case 7 : namaBulan = "Agu"; break;
            case 8 : namaBulan = "Sep"; break;
            case 9 : namaBulan = "Okt"; break;
            case 10 : namaBulan = "Nov"; break;
            case 11 : namaBulan = "Des"; break;
        }

        let tampilanTanggal = tanggal + " " + namaBulan + " " + tahun;
        tampilanTanggal += " " + jam + ":" + menit + ":" + detik;

        this.setState({
            waktu: tampilanTanggal
        });
    }

    removeChild = index => {
        this.state.items.splice(index, 1);
        const newItems =this.state.items;

        this.setState({
            items: newItems
        });
    }

    onClickSave = () => {
        const objek = {
            waktu: this.today.toJSON(),
            total: this.state.total,
            customer: this.state.customer,
            kasir: this.state.kasir,
            detail: this.state.items.map(value => {
                return {barang: value.barang, jumlah: value.jumlah};
            })
        };

        fetch('http://localhost:8080/market/transaksi/', {
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
            this.props.history.push("/transaksi");
        })
        .catch(() => {
            alert("Failed sending data!!");
        });
    }

    render() { 
        const { waktu, total } = this.state;

        return ( 
            <Div class="transaksi">
                <Div class="judul">
                    <Paragraph>Formulir Pengguna</Paragraph>
                </Div>
                <Div class="field">
                    <Label for="transaksi-customer">Customer</Label>
                    <Select id="transaksi-customer" name="transaksi-customer" class="select" onChange={event => this.onChangeSelectCustomer(event.target.value)}>
                        <Option value="">Pilih</Option>
                        {
                            this.state.customers.map((value, index) => {
                                return(
                                    <Option value={value.id} key={index}>{value.nama}</Option>
                                )  
                            })
                        }
                    </Select>
                </Div>
                <Div class="field">
                    <Label for="transaksi-waktu">Date Time</Label>
                    <Text name="transaksi-waktu" id="transaksi-waktu" class="input" disabled="disabled" value={waktu} onChange={event => this.onChangeInput("waktu", event.target.value)} />
                </Div>
                {
                    this.state.items.map((value, index) => {
                        let addButton;
                        if(index === this.state.items.length - 1) {
                            addButton = 
                                <Div class="add">
                                        <Button value="+" class="button-add" onClick={this.addChild} />
                                </Div>;
                        }

                        return(
                            <Div class="items" key={index}>
                                <Div class="item">
                                    <Label for="user-city">Item</Label>
                                    <Select id="transaksi-barang" name="transaksi-barang" class="select" onChange={event => this.onChangeSelectBarang(event.target.value, index)}>
                                        <Option value="">Pilih</Option>
                                        {
                                            this.state.goods.map((value, index) => {
                                                return(
                                                    <Option value={value.id} key={index}>{value.nama}</Option>
                                                )
                                            })
                                        }
                                    </Select>
                                </Div>
                                <Div class="amount">
                                    <Label for={"transaksi-harga-" + index}>Amount</Label>
                                    <Text name="transaksi-harga" id={"transaksi-harga-" + index} class="input" disabled="disabled" placeholder="Amount" value={(value.barang.harga === "") ? "" : "Rp. " + value.barang.harga + ",-"} onChange={event => this.onChangeInput("harga", event.target.value, index)} />
                                </Div>
                                <Div class="qty">
                                    <Label for={"transaksi-jumlah-" + index}>Qty</Label>
                                    <Text name="transaksi-jumlah" id={"transaksi-jumlah-" + index} class="input" placeholder="Qty" value={value.jumlah} onChange={event => this.onChangeInputQuantity(event.target.value, index)} />
                                </Div>
                                <Div class="sub-total">
                                    <Label for={"transaksi-sub-total-" + index}>Sub Total</Label>
                                    <Text name="transaksi-sub-total" id={"transaksi-sub-total-" + index} class="input" disabled="disabled" placeholder="Sub Total" value={value.subTotal} onChange={event => this.onChangeInput("subTotal", event.target.value, index)} />
                                </Div>
                                <Div class="remove">
                                    <Button value="-" class="button-remove" onClick={() => this.removeChild(index)} />
                                </Div>
                                {addButton}
                            </Div>
                        )
                    })
                }
                
                <Div class="total">
                    <Label for="user-city">Total</Label>
                    <Text name="transaksi-total" id="transaksi-total" class="input" disabled="disabled" value={total} onChange={event => this.onChangeInputTotal(event.target.value)} />
                    <Button value="Simpan" class="button" name="transaksi-simpan" id="transaksi-simpan" onClick={this.onClickSave} />
                </Div>
            </Div>
         );
    }
}
 
export default TransaksiForm;