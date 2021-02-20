import React, { Component } from 'react';
import { Button, Paragraph, Div, Text } from '../../component';
import './style.css';

class StockOpnameForm extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            id: "",
            nama: "",
            jumlah: "",
            waktu: "",
            jumlahSekarang: "",
            alasan: ""
         }
        this.today = null;
    }

    componentDidMount() {
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
                jumlah: json.jumlah 
            });
            
        })
        .catch((e) => {
            alert("Failed fetching data!!", e)
        });

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
        const hari = this.today.getDay();
        const jam = this.today.getHours();
        const menit = this.today.getMinutes();
        const detik = this.today.getSeconds();

        let namaHari;
        let namaBulan;

        switch(hari) {
            case 0 : namaHari = "Minggu"; break;
            case 1 : namaHari = "Senin"; break;
            case 2 : namaHari = "Selasa"; break;
            case 3 : namaHari = "Rabu"; break;
            case 4 : namaHari = "Kamis"; break;
            case 5 : namaHari = "Jumat"; break;
            case 6 : namaHari = "Sabtu"; break;
        }

        switch(bulan) {
            case 0 : namaBulan = "Januari"; break;
            case 1 : namaBulan = "Februari"; break;
            case 2 : namaBulan = "Maret"; break;
            case 3 : namaBulan = "April"; break;
            case 4 : namaBulan = "Mei"; break;
            case 5 : namaBulan = "Juni"; break;
            case 6 : namaBulan = "Juli"; break;
            case 7 : namaBulan = "Agustus"; break;
            case 8 : namaBulan = "September"; break;
            case 9 : namaBulan = "Oktober"; break;
            case 10 : namaBulan = "November"; break;
            case 11 : namaBulan = "Desember"; break;
        }

        let tampilanTanggal = namaHari + ", " + tanggal + " " + namaBulan + " " + tahun;
        tampilanTanggal += " " + jam + ":" + menit + ":" + detik;

        this.setState({
            waktu: tampilanTanggal
        });
    }

    render() { 
        const { id, nama, jumlah, waktu, jumlahSekarang, alasan } = this.state;

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
                    <Button value="Save" class="button-submit" />
                    <Button value="Clear Form" class="button-clear" />
                </Div>
            </Div>
         );
    }
}
 
export default StockOpnameForm;