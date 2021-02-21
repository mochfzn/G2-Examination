-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 21 Feb 2021 pada 16.20
-- Versi server: 10.4.17-MariaDB
-- Versi PHP: 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `militer`
--
CREATE DATABASE IF NOT EXISTS `militer` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `militer`;

-- --------------------------------------------------------

--
-- Struktur dari tabel `batalyon`
--

CREATE TABLE `batalyon` (
  `id` varchar(50) NOT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `id_personil` varchar(50) DEFAULT NULL,
  `lokasi` varchar(50) DEFAULT NULL,
  `jml_personil` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `batalyon`
--

INSERT INTO `batalyon` (`id`, `nama`, `id_personil`, `lokasi`, `jml_personil`) VALUES
('7054df29-f3af-424b-b67f-9bb7c7dff4c0', 'Garda Samudera', '692dd5ea-f90c-4d20-ae7a-0d4a30f52eb1', 'Jakarta', 5);

-- --------------------------------------------------------

--
-- Struktur dari tabel `batalyon_detail`
--

CREATE TABLE `batalyon_detail` (
  `id` varchar(50) NOT NULL,
  `id_batalyon` varchar(50) DEFAULT NULL,
  `id_personil` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `batalyon_detail`
--

INSERT INTO `batalyon_detail` (`id`, `id_batalyon`, `id_personil`) VALUES
('073be26e-6880-410c-8794-6733bfe07622', '7054df29-f3af-424b-b67f-9bb7c7dff4c0', 'b210e448-d9c0-46f8-ba38-32a52925e2fa'),
('079ba0a7-eca8-4cc5-9b92-e349e0a23e79', '7054df29-f3af-424b-b67f-9bb7c7dff4c0', '5b11b35a-d798-416a-8915-268019d210a9'),
('32ef9a60-9701-4a88-ae7c-897ad3f1bc65', '7054df29-f3af-424b-b67f-9bb7c7dff4c0', 'd1cd9d84-7b2d-4a77-80a8-3abeb3850589'),
('5654f8fe-0069-4f96-964b-dba5e38f63b6', '7054df29-f3af-424b-b67f-9bb7c7dff4c0', '4bdae137-eee0-45a9-a3be-8725793c6bc4'),
('637615ee-205b-4456-a5a7-589501eadf19', '7054df29-f3af-424b-b67f-9bb7c7dff4c0', 'a8e4b567-c525-4fef-8f11-cb9e6a7c0f9a');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pangkat`
--

CREATE TABLE `pangkat` (
  `id` int(11) NOT NULL,
  `nama` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pangkat`
--

INSERT INTO `pangkat` (`id`, `nama`) VALUES
(1, 'Jendral'),
(2, 'Letnan Jendral'),
(3, 'Mayor Jendral'),
(4, 'Brigadir Jendral'),
(5, 'Kolonel'),
(6, 'Letnan'),
(7, 'Sersan'),
(8, 'Kopral'),
(9, 'Prajurit');

-- --------------------------------------------------------

--
-- Struktur dari tabel `personil`
--

CREATE TABLE `personil` (
  `id` varchar(50) NOT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `id_pangkat` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `personil`
--

INSERT INTO `personil` (`id`, `nama`, `id_pangkat`) VALUES
('01538dee-a8ad-4e33-9e06-0dfcfdc16e74', 'Bima', '1'),
('377a95f7-e02b-4d4a-a002-bc7273553bd8', 'Dimas', '2'),
('4bdae137-eee0-45a9-a3be-8725793c6bc4', 'Dika', '6'),
('5b11b35a-d798-416a-8915-268019d210a9', 'Hafis', '8'),
('692dd5ea-f90c-4d20-ae7a-0d4a30f52eb1', 'Syamas', '3'),
('932f9c77-7925-4717-abc7-ef96a331ccd1', 'Adam', '4'),
('a8e4b567-c525-4fef-8f11-cb9e6a7c0f9a', 'Aufa', '7'),
('b210e448-d9c0-46f8-ba38-32a52925e2fa', 'Dihan', '9'),
('d1cd9d84-7b2d-4a77-80a8-3abeb3850589', 'Fachri', '5');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `batalyon`
--
ALTER TABLE `batalyon`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `batalyon_detail`
--
ALTER TABLE `batalyon_detail`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `pangkat`
--
ALTER TABLE `pangkat`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `personil`
--
ALTER TABLE `personil`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `pangkat`
--
ALTER TABLE `pangkat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- Database: `mini_market`
--
CREATE DATABASE IF NOT EXISTS `mini_market` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `mini_market`;

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

CREATE TABLE `barang` (
  `id` varchar(50) NOT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `harga` decimal(9,2) UNSIGNED DEFAULT NULL,
  `jumlah` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`id`, `nama`, `harga`, `jumlah`) VALUES
('01f8b587-ce1c-4fad-b7dd-cda6ef8764aa', 'Permen', '2000.00', 100),
('0c5dd48d-1f27-4221-9937-10eceeab190f', 'Air Mineral', '8000.00', 95),
('142b2fe6-d7d5-411a-9dc4-6fa725cacdc5', 'Mie Instan', '3000.00', 95),
('1878dfeb-8fbe-4199-bf2a-1c25c375359f', 'Minyak Goreng', '78000.00', 100),
('2571e181-217f-4919-a6c2-bc695d5dc26f', 'Sabun Cuci', '7500.00', 100),
('273fa650-4d98-435c-9f70-dc0ec4404012', 'Pasta Gigi', '17000.00', 100),
('274c810c-5df9-4f69-a6d7-dee87165b9b0', 'Snack', '7000.00', 100),
('3b0c5411-a82f-41b9-bbf0-e34a0b164bdb', 'Coklat', '18000.00', 100),
('40def590-0f1c-4f29-9717-f701ee31da64', 'Kopi', '2000.00', 100),
('473f98ed-4dbd-49b1-a705-adfb271b5d98', 'Sabun Muka ', '32000.00', 100),
('4bba48ab-73a9-4035-8047-5715fd70e8dd', 'Deodorant', '51000.00', 100),
('504442d5-0069-401f-bf0e-b921aaea9927', 'Garam', '5000.00', 100),
('54f2f146-98c3-4a6c-9d95-2044d9b74162', 'Susu', '32000.00', 100),
('5faac77c-9021-4064-9c9d-60eb0cfb553a', 'Jagung', '4000.00', 120),
('6494569b-ea77-4e5b-8767-8508c380fdfa', 'Tisu', '2000.00', 100),
('65462a6c-71f7-42a2-885b-865d5e9393c3', 'Sikat Gigi', '4000.00', 100),
('6d796889-cedc-453d-84a7-e75280f71410', 'Kedelai', '3000.00', 100),
('72d2d0b0-5490-4c70-8aae-dc5c24b38f6e', 'Gula', '11000.00', 100),
('80370fe6-f4d5-40f6-8a06-d40f20e19a9d', 'Botol Mineral', '4000.00', 100),
('80f5965d-e334-4a0a-8c3e-e120feccd727', 'Telur Ayam', '34000.00', 100),
('82a4786a-f968-4cd1-b61c-dd9131d18ab6', 'Kecap', '10000.00', 100),
('8c0492b9-4d11-4e03-b2eb-d451f8d22572', 'Tepung Bumbu', '3000.00', 100),
('91f9df96-54f0-47a4-8b43-d298048961f3', 'Saos', '13000.00', 100),
('997d3f72-f8a7-4822-a916-577268ab7509', 'Bawang Merah', '6000.00', 100),
('ada8a0d8-bbc1-4713-8419-e9699096a636', 'Beras', '26000.00', 100),
('b28ed51a-c01e-4a8c-9482-f78a96a39343', 'Shampoo', '36000.00', 100),
('b63b62df-6342-4308-b22a-37bbe34cdcd1', 'Pewangi Pakaian', '7000.00', 100),
('bba8d8fb-453c-4908-81b5-c277667458b2', 'Wafer', '8000.00', 100),
('bf1cad2d-59ae-49c1-aa17-a27581d6573d', 'Kondisioner', '73000.00', 100),
('c2450679-7c44-41b3-a942-fb7dac0cd1bb', 'Bumbu Penyedap Rasa', '1000.00', 100),
('cb4c131c-3952-42f2-a459-3185873df3ee', 'Sabun Cuci Piring', '10000.00', 100),
('cdd4e9ca-3244-4a3e-905d-89fcab8de64b', 'Sabun', '4000.00', 100),
('d654de56-5630-4edb-88d6-c250727fdad2', 'Daging Sapi', '110000.00', 100),
('ff6d7c86-7726-4351-98c3-818a454d608a', 'Daging Ayam', '54000.00', 100);

-- --------------------------------------------------------

--
-- Struktur dari tabel `customer`
--

CREATE TABLE `customer` (
  `id` varchar(50) NOT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `alamat` varchar(100) DEFAULT NULL,
  `telepon` varchar(14) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `customer`
--

INSERT INTO `customer` (`id`, `nama`, `alamat`, `telepon`) VALUES
('5b9f45a4-11f2-4a5c-920b-4c72f91b5bee', 'Ema', 'Kebon Kelapa', '0895331652433'),
('6c930733-fe12-461d-a884-22d0fbae484a', 'Frida', 'Petojo Utara', '0895331652433'),
('71669683-fafe-4897-b341-38fa1660b7a0', 'Mukti', 'Duri Pulo', '0895331652433'),
('71f1d52c-70ce-4d55-8b11-25ccdc5a5fc4', 'Hanum', 'Cideng', '0895331652433'),
('7736d2bb-09df-42cb-bd95-df4008b5742b', 'Hendro', 'Petojo Selatan', '0895331652433'),
('9fdd37d7-e1fc-478c-974e-2a7a6c3323f1', 'Octavia', 'Bendungan Hilir', '0895331652433'),
('c0d73457-3775-4ef5-ba60-d8ef7a9b4cad', 'Isnaini', 'Karet Tengsin', '0895331652433'),
('dd055d3d-a4e2-40fc-9a83-b45f893f00b5', 'Noviani', 'Kebon Melati', '0895331652433'),
('e35bb039-6823-4b15-aa0b-87b325f52eea', 'Hani', 'Kebon Kacang', '0895331652433'),
('f418df58-9ed0-4caa-b428-5b7b946e0160', 'Hasibuan', 'Petamburan', '0895331652433');

-- --------------------------------------------------------

--
-- Struktur dari tabel `kasir`
--

CREATE TABLE `kasir` (
  `id` varchar(50) NOT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `alamat` varchar(100) DEFAULT NULL,
  `telepon` varchar(14) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `kasir`
--

INSERT INTO `kasir` (`id`, `nama`, `alamat`, `telepon`) VALUES
('1a2f5531-bb40-47bb-b069-4e92d19985b2', 'Candra', 'Cikini', '0895331652433'),
('26163cef-f7bf-49cf-907f-4f388eb6e726', 'Maulana', 'Senen', '0895331652433'),
('27484806-f7e9-46a9-9240-23d1a7c2a404', 'Aji', 'Kwitang', '0895331652433'),
('49e1e401-190c-48c4-bac4-dac276f6c20c', 'Reze', 'Kenari', '0895331652433'),
('5b9e1c12-48e0-4d4c-990f-fed92c63b613', 'Prasetyo', 'Peseban', '0895331652433'),
('6dbd94e4-c8af-442b-be6c-100d9c1fc0a8', 'Taufik', 'Kramat', '0895331652433'),
('7b07b7d2-6fad-4e4b-a821-3eaccf00ac8e', 'Gunawan', 'Bungur', '0895331652433'),
('810f20d2-b011-4326-b458-b6023444d3b6', 'Andri', 'Galur', '0895331652433'),
('9ebbcd4b-80b9-457d-85ba-9f9c16681ae1', 'Gusti', 'Tanah Tinggi', '0895331652433'),
('b26250e1-ccdf-44bb-8517-7e743c6e0401', 'Dimas', 'Kampung Rawa', '0895331652433'),
('bd628fc4-e668-4578-adec-eed1d1638ea9', 'Pratama', 'Johar Baru', '0895331652433'),
('d7de6772-9557-4880-bb85-8302e38c2d5c', 'Rahman', 'Rawa Sari', '0895331652433'),
('e8c1a409-33b7-4cca-a1dd-cb05942f61d3', 'Akbar', 'Kemayoran', '0895331652433'),
('eb3f1bfb-b701-4562-bddf-35ed3ac901bf', 'Wibowo', 'Kebon Kosong', '0895331652433');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pengguna`
--

CREATE TABLE `pengguna` (
  `id` varchar(50) NOT NULL,
  `id_kasir` varchar(50) DEFAULT NULL,
  `akses` varchar(10) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pengguna`
--

INSERT INTO `pengguna` (`id`, `id_kasir`, `akses`, `username`, `password`) VALUES
('0ff49d57-06c0-4f2f-9844-27bb683b95b4', '5b9e1c12-48e0-4d4c-990f-fed92c63b613', 'kasir', 'prasetyo', 'kenangan'),
('14c01a82-6e58-4a1a-800b-63d1ddfb8842', 'd7de6772-9557-4880-bb85-8302e38c2d5c', 'kasir', 'apalah', 'terserah'),
('24198e97-4dfa-4239-a36d-4a96e2d23542', '7b07b7d2-6fad-4e4b-a821-3eaccf00ac8e', 'admin', 'gunawan', 'tidakmengerti'),
('2591b07d-6056-4d94-b4da-5a478dd732b4', 'eb3f1bfb-b701-4562-bddf-35ed3ac901bf', 'kasir', 'apalah', 'terserah'),
('3144cfea-801a-4488-9ea7-752b51dc7fdf', '9ebbcd4b-80b9-457d-85ba-9f9c16681ae1', 'kasir', 'apalah', 'terserah'),
('6fec4107-8acd-4039-b9d6-5688b83975f0', 'b26250e1-ccdf-44bb-8517-7e743c6e0401', 'kasir', 'apalah', 'terserah'),
('905e90b2-17ab-4a99-92c4-bc067de7b497', '810f20d2-b011-4326-b458-b6023444d3b6', 'kasir', 'apalah', 'terserah'),
('9da0bacc-98c1-4973-8611-dc0f1fecbea2', '1a2f5531-bb40-47bb-b069-4e92d19985b2', 'kasir', 'candra', 'cobalah'),
('9dffb98c-3889-4b76-9383-0f64b2e86c50', '27484806-f7e9-46a9-9240-23d1a7c2a404', 'admin', 'aji', 'semua'),
('a32f4b88-cd78-4a9e-853b-eb562a51c9ee', '6dbd94e4-c8af-442b-be6c-100d9c1fc0a8', 'kasir', 'apalah', 'terserah'),
('aa07f41c-7524-4cef-9218-179542189293', 'bd628fc4-e668-4578-adec-eed1d1638ea9', 'kasir', 'apalah', 'terserah'),
('c34ad391-d736-43f0-beef-b3a536288fda', '49e1e401-190c-48c4-bac4-dac276f6c20c', 'kasir', 'reza', 'waktu'),
('ce51f2cb-0f80-4d24-8595-78e43f66813f', 'e8c1a409-33b7-4cca-a1dd-cb05942f61d3', 'kasir', 'apalah', 'terserah'),
('f29bb21e-5cd3-4339-9203-1e9cf872bc0e', '26163cef-f7bf-49cf-907f-4f388eb6e726', 'kasir', 'maulana', 'mengerti');

-- --------------------------------------------------------

--
-- Struktur dari tabel `stock_opname`
--

CREATE TABLE `stock_opname` (
  `id` varchar(50) NOT NULL,
  `id_barang` varchar(50) DEFAULT NULL,
  `alasan` varchar(200) DEFAULT NULL,
  `waktu` datetime DEFAULT NULL,
  `jumlah` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `stock_opname`
--

INSERT INTO `stock_opname` (`id`, `id_barang`, `alasan`, `waktu`, `jumlah`) VALUES
('0cd38581-83d1-43ba-ac91-46be5ed72560', '6d796889-cedc-453d-84a7-e75280f71410', 'Terdapat kedelai yang tersembunyi di gudang.', '2021-02-21 00:00:00', 100),
('7f4b9a1a-f9b7-4dd8-be9e-3b76dab3f279', 'ada8a0d8-bbc1-4713-8419-e9699096a636', 'Beras ditemukan di gudang', '2021-02-21 00:00:00', 100),
('d51ac44e-e4a5-4ef5-bf84-bfd07b9f3e8d', '72d2d0b0-5490-4c70-8aae-dc5c24b38f6e', 'ditemukan digudang', '2021-02-21 00:00:00', 100);

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id` varchar(50) NOT NULL,
  `waktu` datetime DEFAULT NULL,
  `total` decimal(11,2) DEFAULT NULL,
  `id_customer` varchar(50) DEFAULT NULL,
  `id_kasir` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`id`, `waktu`, `total`, `id_customer`, `id_kasir`) VALUES
('7386cde6-f3bd-4c36-969a-b28eacb0dbdb', '2021-02-21 21:35:53', '65000.00', '5b9f45a4-11f2-4a5c-920b-4c72f91b5bee', 'eb3f1bfb-b701-4562-bddf-35ed3ac901bf'),
('74ff6990-72d1-40bb-a51a-fefc71e0aaac', '2021-02-21 22:03:16', '200000.00', '5b9f45a4-11f2-4a5c-920b-4c72f91b5bee', '27484806-f7e9-46a9-9240-23d1a7c2a404'),
('c2300a7b-1414-4235-8d8c-83c189445f6d', '2021-02-21 14:14:31', '28000.00', '5b9f45a4-11f2-4a5c-920b-4c72f91b5bee', 'eb3f1bfb-b701-4562-bddf-35ed3ac901bf');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi_detail`
--

CREATE TABLE `transaksi_detail` (
  `id` varchar(50) NOT NULL,
  `id_transaksi` varchar(50) DEFAULT NULL,
  `id_barang` varchar(50) DEFAULT NULL,
  `jumlah` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `transaksi_detail`
--

INSERT INTO `transaksi_detail` (`id`, `id_transaksi`, `id_barang`, `jumlah`) VALUES
('07dd6a0c-1104-41a6-ae62-ce3f3adba886', '7386cde6-f3bd-4c36-969a-b28eacb0dbdb', '142b2fe6-d7d5-411a-9dc4-6fa725cacdc5', 5),
('1e220989-50ae-43b0-ac9f-df1d4995cf91', '74ff6990-72d1-40bb-a51a-fefc71e0aaac', '01f8b587-ce1c-4fad-b7dd-cda6ef8764aa', 100),
('5a991d09-1885-4716-83b5-5dda8e1aab9a', '7386cde6-f3bd-4c36-969a-b28eacb0dbdb', '0c5dd48d-1f27-4221-9937-10eceeab190f', 5),
('71453fb4-bfa2-4361-9818-5614f9ba801e', 'c2300a7b-1414-4235-8d8c-83c189445f6d', '0c5dd48d-1f27-4221-9937-10eceeab190f', 3),
('7d324437-a535-4960-b114-25a8b19154cf', 'c2300a7b-1414-4235-8d8c-83c189445f6d', '01f8b587-ce1c-4fad-b7dd-cda6ef8764aa', 2),
('e3059db2-38d3-4f38-bd50-ee78447e260e', '7386cde6-f3bd-4c36-969a-b28eacb0dbdb', '01f8b587-ce1c-4fad-b7dd-cda6ef8764aa', 5);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `kasir`
--
ALTER TABLE `kasir`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `pengguna`
--
ALTER TABLE `pengguna`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `stock_opname`
--
ALTER TABLE `stock_opname`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_barang` (`id_barang`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_customer` (`id_customer`),
  ADD KEY `id_kasir` (`id_kasir`);

--
-- Indeks untuk tabel `transaksi_detail`
--
ALTER TABLE `transaksi_detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_transaksi` (`id_transaksi`),
  ADD KEY `id_barang` (`id_barang`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `stock_opname`
--
ALTER TABLE `stock_opname`
  ADD CONSTRAINT `stock_opname_ibfk_1` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id`);

--
-- Ketidakleluasaan untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`id_customer`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`id_kasir`) REFERENCES `kasir` (`id`);

--
-- Ketidakleluasaan untuk tabel `transaksi_detail`
--
ALTER TABLE `transaksi_detail`
  ADD CONSTRAINT `transaksi_detail_ibfk_1` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id`),
  ADD CONSTRAINT `transaksi_detail_ibfk_2` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id`);
--
-- Database: `penjualan`
--
CREATE DATABASE IF NOT EXISTS `penjualan` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `penjualan`;

-- --------------------------------------------------------

--
-- Struktur dari tabel `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `harga` decimal(10,3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `product`
--

INSERT INTO `product` (`id`, `nama`, `harga`) VALUES
(1, 'Asian Dolce Latte', '37000.000'),
(2, 'Caramel Macchiato', '49000.000'),
(3, 'Caffe Americano', '96600.000'),
(4, 'Vanilla Latte', '83000.000');

-- --------------------------------------------------------

--
-- Struktur dari tabel `sales`
--

CREATE TABLE `sales` (
  `id` varchar(100) NOT NULL,
  `customer` varchar(50) DEFAULT NULL,
  `tanggal` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `sales`
--

INSERT INTO `sales` (`id`, `customer`, `tanggal`) VALUES
('71f0354a-1133-4f52-9ff3-e905dd84168d', 'Sukiyama', '2021-02-02');

-- --------------------------------------------------------

--
-- Struktur dari tabel `sales_detail`
--

CREATE TABLE `sales_detail` (
  `id` varchar(100) NOT NULL,
  `id_sales` varchar(100) DEFAULT NULL,
  `id_product` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `sales_detail`
--

INSERT INTO `sales_detail` (`id`, `id_sales`, `id_product`, `quantity`) VALUES
('4431dbe0-770a-4ed2-98e9-48ee19a29dc7', '71f0354a-1133-4f52-9ff3-e905dd84168d', 4, 0),
('ceba1d49-8659-4af0-aa17-7a393a4ae85c', '71f0354a-1133-4f52-9ff3-e905dd84168d', 2, 0);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `sales_detail`
--
ALTER TABLE `sales_detail`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- Database: `phpmyadmin`
--
CREATE DATABASE IF NOT EXISTS `phpmyadmin` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `phpmyadmin`;

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__bookmark`
--

CREATE TABLE `pma__bookmark` (
  `id` int(10) UNSIGNED NOT NULL,
  `dbase` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `user` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `label` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `query` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Bookmarks';

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__central_columns`
--

CREATE TABLE `pma__central_columns` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `col_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `col_type` varchar(64) COLLATE utf8_bin NOT NULL,
  `col_length` text COLLATE utf8_bin DEFAULT NULL,
  `col_collation` varchar(64) COLLATE utf8_bin NOT NULL,
  `col_isNull` tinyint(1) NOT NULL,
  `col_extra` varchar(255) COLLATE utf8_bin DEFAULT '',
  `col_default` text COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Central list of columns';

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__column_info`
--

CREATE TABLE `pma__column_info` (
  `id` int(5) UNSIGNED NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `column_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `comment` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `mimetype` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `transformation` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `transformation_options` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `input_transformation` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `input_transformation_options` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Column information for phpMyAdmin';

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__designer_settings`
--

CREATE TABLE `pma__designer_settings` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `settings_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Settings related to Designer';

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__export_templates`
--

CREATE TABLE `pma__export_templates` (
  `id` int(5) UNSIGNED NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `export_type` varchar(10) COLLATE utf8_bin NOT NULL,
  `template_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `template_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Saved export templates';

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__favorite`
--

CREATE TABLE `pma__favorite` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `tables` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Favorite tables';

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__history`
--

CREATE TABLE `pma__history` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `timevalue` timestamp NOT NULL DEFAULT current_timestamp(),
  `sqlquery` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='SQL history for phpMyAdmin';

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__navigationhiding`
--

CREATE TABLE `pma__navigationhiding` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `item_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `item_type` varchar(64) COLLATE utf8_bin NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Hidden items of navigation tree';

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__pdf_pages`
--

CREATE TABLE `pma__pdf_pages` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `page_nr` int(10) UNSIGNED NOT NULL,
  `page_descr` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='PDF relation pages for phpMyAdmin';

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__recent`
--

CREATE TABLE `pma__recent` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `tables` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Recently accessed tables';

--
-- Dumping data untuk tabel `pma__recent`
--

INSERT INTO `pma__recent` (`username`, `tables`) VALUES
('root', '[{\"db\":\"militer\",\"table\":\"batalyon\"}]');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__relation`
--

CREATE TABLE `pma__relation` (
  `master_db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `master_table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `master_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Relation table';

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__savedsearches`
--

CREATE TABLE `pma__savedsearches` (
  `id` int(5) UNSIGNED NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `search_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `search_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Saved searches';

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__table_coords`
--

CREATE TABLE `pma__table_coords` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `pdf_page_number` int(11) NOT NULL DEFAULT 0,
  `x` float UNSIGNED NOT NULL DEFAULT 0,
  `y` float UNSIGNED NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table coordinates for phpMyAdmin PDF output';

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__table_info`
--

CREATE TABLE `pma__table_info` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `display_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table information for phpMyAdmin';

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__table_uiprefs`
--

CREATE TABLE `pma__table_uiprefs` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `prefs` text COLLATE utf8_bin NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Tables'' UI preferences';

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__tracking`
--

CREATE TABLE `pma__tracking` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `version` int(10) UNSIGNED NOT NULL,
  `date_created` datetime NOT NULL,
  `date_updated` datetime NOT NULL,
  `schema_snapshot` text COLLATE utf8_bin NOT NULL,
  `schema_sql` text COLLATE utf8_bin DEFAULT NULL,
  `data_sql` longtext COLLATE utf8_bin DEFAULT NULL,
  `tracking` set('UPDATE','REPLACE','INSERT','DELETE','TRUNCATE','CREATE DATABASE','ALTER DATABASE','DROP DATABASE','CREATE TABLE','ALTER TABLE','RENAME TABLE','DROP TABLE','CREATE INDEX','DROP INDEX','CREATE VIEW','ALTER VIEW','DROP VIEW') COLLATE utf8_bin DEFAULT NULL,
  `tracking_active` int(1) UNSIGNED NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Database changes tracking for phpMyAdmin';

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__userconfig`
--

CREATE TABLE `pma__userconfig` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `timevalue` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `config_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User preferences storage for phpMyAdmin';

--
-- Dumping data untuk tabel `pma__userconfig`
--

INSERT INTO `pma__userconfig` (`username`, `timevalue`, `config_data`) VALUES
('root', '2021-02-21 15:20:24', '{\"Console\\/Mode\":\"collapse\",\"lang\":\"id\"}');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__usergroups`
--

CREATE TABLE `pma__usergroups` (
  `usergroup` varchar(64) COLLATE utf8_bin NOT NULL,
  `tab` varchar(64) COLLATE utf8_bin NOT NULL,
  `allowed` enum('Y','N') COLLATE utf8_bin NOT NULL DEFAULT 'N'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User groups with configured menu items';

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__users`
--

CREATE TABLE `pma__users` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `usergroup` varchar(64) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Users and their assignments to user groups';

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `pma__bookmark`
--
ALTER TABLE `pma__bookmark`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `pma__central_columns`
--
ALTER TABLE `pma__central_columns`
  ADD PRIMARY KEY (`db_name`,`col_name`);

--
-- Indeks untuk tabel `pma__column_info`
--
ALTER TABLE `pma__column_info`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `db_name` (`db_name`,`table_name`,`column_name`);

--
-- Indeks untuk tabel `pma__designer_settings`
--
ALTER TABLE `pma__designer_settings`
  ADD PRIMARY KEY (`username`);

--
-- Indeks untuk tabel `pma__export_templates`
--
ALTER TABLE `pma__export_templates`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `u_user_type_template` (`username`,`export_type`,`template_name`);

--
-- Indeks untuk tabel `pma__favorite`
--
ALTER TABLE `pma__favorite`
  ADD PRIMARY KEY (`username`);

--
-- Indeks untuk tabel `pma__history`
--
ALTER TABLE `pma__history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `username` (`username`,`db`,`table`,`timevalue`);

--
-- Indeks untuk tabel `pma__navigationhiding`
--
ALTER TABLE `pma__navigationhiding`
  ADD PRIMARY KEY (`username`,`item_name`,`item_type`,`db_name`,`table_name`);

--
-- Indeks untuk tabel `pma__pdf_pages`
--
ALTER TABLE `pma__pdf_pages`
  ADD PRIMARY KEY (`page_nr`),
  ADD KEY `db_name` (`db_name`);

--
-- Indeks untuk tabel `pma__recent`
--
ALTER TABLE `pma__recent`
  ADD PRIMARY KEY (`username`);

--
-- Indeks untuk tabel `pma__relation`
--
ALTER TABLE `pma__relation`
  ADD PRIMARY KEY (`master_db`,`master_table`,`master_field`),
  ADD KEY `foreign_field` (`foreign_db`,`foreign_table`);

--
-- Indeks untuk tabel `pma__savedsearches`
--
ALTER TABLE `pma__savedsearches`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `u_savedsearches_username_dbname` (`username`,`db_name`,`search_name`);

--
-- Indeks untuk tabel `pma__table_coords`
--
ALTER TABLE `pma__table_coords`
  ADD PRIMARY KEY (`db_name`,`table_name`,`pdf_page_number`);

--
-- Indeks untuk tabel `pma__table_info`
--
ALTER TABLE `pma__table_info`
  ADD PRIMARY KEY (`db_name`,`table_name`);

--
-- Indeks untuk tabel `pma__table_uiprefs`
--
ALTER TABLE `pma__table_uiprefs`
  ADD PRIMARY KEY (`username`,`db_name`,`table_name`);

--
-- Indeks untuk tabel `pma__tracking`
--
ALTER TABLE `pma__tracking`
  ADD PRIMARY KEY (`db_name`,`table_name`,`version`);

--
-- Indeks untuk tabel `pma__userconfig`
--
ALTER TABLE `pma__userconfig`
  ADD PRIMARY KEY (`username`);

--
-- Indeks untuk tabel `pma__usergroups`
--
ALTER TABLE `pma__usergroups`
  ADD PRIMARY KEY (`usergroup`,`tab`,`allowed`);

--
-- Indeks untuk tabel `pma__users`
--
ALTER TABLE `pma__users`
  ADD PRIMARY KEY (`username`,`usergroup`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `pma__bookmark`
--
ALTER TABLE `pma__bookmark`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `pma__column_info`
--
ALTER TABLE `pma__column_info`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `pma__export_templates`
--
ALTER TABLE `pma__export_templates`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `pma__history`
--
ALTER TABLE `pma__history`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `pma__pdf_pages`
--
ALTER TABLE `pma__pdf_pages`
  MODIFY `page_nr` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `pma__savedsearches`
--
ALTER TABLE `pma__savedsearches`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- Database: `sonoo`
--
CREATE DATABASE IF NOT EXISTS `sonoo` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `sonoo`;

-- --------------------------------------------------------

--
-- Struktur dari tabel `book`
--

CREATE TABLE `book` (
  `book_id` int(11) NOT NULL,
  `title` varchar(128) NOT NULL,
  `author` varchar(45) NOT NULL,
  `price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `card_detail`
--

CREATE TABLE `card_detail` (
  `id_card` varchar(100) DEFAULT NULL,
  `id_product` int(11) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `card_detail`
--

INSERT INTO `card_detail` (`id_card`, `id_product`, `quantity`, `id`) VALUES
('830c4b04-d2c0-4a87-803a-499e5bbbddb6', 1, 10, 4),
('830c4b04-d2c0-4a87-803a-499e5bbbddb6', 2, 10, 5),
('830c4b04-d2c0-4a87-803a-499e5bbbddb6', 3, 10, 6),
('9385796a-53bf-44cc-b3de-3d933f6be5d6', 1, 10, 7),
('9385796a-53bf-44cc-b3de-3d933f6be5d6', 2, 10, 8),
('d77729d6-47a6-4fcf-afe7-3328dd7f546a', 1, 10, 14),
('d77729d6-47a6-4fcf-afe7-3328dd7f546a', 4, 10, 15);

-- --------------------------------------------------------

--
-- Struktur dari tabel `card_header`
--

CREATE TABLE `card_header` (
  `id` varchar(100) NOT NULL,
  `tanggal` date DEFAULT NULL,
  `customer` varchar(50) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `card_header`
--

INSERT INTO `card_header` (`id`, `tanggal`, `customer`, `status`) VALUES
('7921b4fb-534f-41e5-84f4-226d05f457d9', '2021-01-28', 'Fauzan', b'0'),
('830c4b04-d2c0-4a87-803a-499e5bbbddb6', '2021-01-29', 'Ayo', b'1'),
('9385796a-53bf-44cc-b3de-3d933f6be5d6', '2021-01-28', 'Fauzan', b'0'),
('c77dd11b-8cc2-4d0d-bbeb-e37db61aaaff', '2021-01-28', 'Fauzan', b'1'),
('d77729d6-47a6-4fcf-afe7-3328dd7f546a', '2021-01-29', 'Erik', b'0');

-- --------------------------------------------------------

--
-- Struktur dari tabel `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'Kantor'),
(2, 'Sekolah'),
(3, 'Keagamaan'),
(4, 'Olahraga');

-- --------------------------------------------------------

--
-- Struktur dari tabel `email`
--

CREATE TABLE `email` (
  `id` int(2) DEFAULT NULL,
  `alamatEmail` varchar(50) DEFAULT NULL,
  `idStaff` varchar(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `emp`
--

CREATE TABLE `emp` (
  `id` int(10) DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  `age` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `emp`
--

INSERT INTO `emp` (`id`, `name`, `age`) VALUES
(33, 'Vimal', 30);

-- --------------------------------------------------------

--
-- Struktur dari tabel `jabatan`
--

CREATE TABLE `jabatan` (
  `id` int(11) NOT NULL,
  `nama` varchar(20) DEFAULT NULL,
  `tunjanganMakan` int(10) DEFAULT NULL,
  `tunjanganTransport` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `jabatan`
--

INSERT INTO `jabatan` (`id`, `nama`, `tunjanganMakan`, `tunjanganTransport`) VALUES
(1, 'Staff', 45000, 800000),
(2, 'Manager', 100000, 2000000),
(3, 'Supervisor', 45000, 800000),
(4, 'Direktur', 60000, 1000000),
(6, 'Foreman', 50000, 600000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `karyawan`
--

CREATE TABLE `karyawan` (
  `id` varchar(6) NOT NULL,
  `nama` varchar(20) DEFAULT NULL,
  `gajiPokok` int(10) DEFAULT NULL,
  `idJabatan` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `karyawan`
--

INSERT INTO `karyawan` (`id`, `nama`, `gajiPokok`, `idJabatan`) VALUES
('202101', 'Fauzan', 5000000, 1),
('202102', 'Rizal', 5000000, 1),
('202103', 'Rafi', 8000000, 1),
('202104', 'Raka', 4500000, 1),
('202105', 'Dimas', 8000000, 3),
('202106', 'Doni', 6700000, 2),
('202107', 'Dika', 7000000, 2),
('202108', 'Agung', 5000000, 1),
('202109', 'Aufa', 9900000, 4),
('202110', 'Ajeng', 7600000, 3),
('202111', 'Dihan', 3000000, 1),
('202112', 'Adam', 8200000, 3),
('202113', 'Fachri', 6700000, 2),
('202114', 'Ikhsan', 2000000, 1),
('202115', 'Hara', 3000000, 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `manager`
--

CREATE TABLE `manager` (
  `id` varchar(6) DEFAULT NULL,
  `nama` varchar(20) DEFAULT NULL,
  `jmlAbsen` int(2) DEFAULT NULL,
  `gajiPokok` int(10) DEFAULT NULL,
  `tunjanganPulsa` int(10) DEFAULT NULL,
  `tunjanganTransport` int(10) DEFAULT NULL,
  `tunjanganEntertaint` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `categoryid` int(11) DEFAULT NULL,
  `harga` decimal(4,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `product`
--

INSERT INTO `product` (`id`, `name`, `categoryid`, `harga`) VALUES
(1, 'Kemeja', 1, '40.32'),
(2, 'Baju Putih', 2, '40.32'),
(3, 'Koko', 3, '56.38'),
(4, 'Training', 4, '77.34');

-- --------------------------------------------------------

--
-- Struktur dari tabel `staff`
--

CREATE TABLE `staff` (
  `id` varchar(6) DEFAULT NULL,
  `nama` varchar(20) DEFAULT NULL,
  `jmlAbsen` int(2) DEFAULT NULL,
  `gajiPokok` int(10) DEFAULT NULL,
  `tunjanganPulsa` int(10) DEFAULT NULL,
  `tunjanganMakan` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `telepon`
--

CREATE TABLE `telepon` (
  `id` int(2) DEFAULT NULL,
  `nomor` varchar(14) DEFAULT NULL,
  `idManager` varchar(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `id` int(4) NOT NULL,
  `username` varchar(10) NOT NULL,
  `email` varchar(25) NOT NULL,
  `password` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`id`, `username`, `email`, `password`) VALUES
(1, 'admin', 'admin@gmail.com', 'admin'),
(3, 'pedia', 'fikri@gmail.com', 'fikri');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`book_id`),
  ADD UNIQUE KEY `book_id_UNIQUE` (`book_id`),
  ADD UNIQUE KEY `title_UNIQUE` (`title`);

--
-- Indeks untuk tabel `card_detail`
--
ALTER TABLE `card_detail`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `card_header`
--
ALTER TABLE `card_header`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `jabatan`
--
ALTER TABLE `jabatan`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `karyawan`
--
ALTER TABLE `karyawan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_JabatanKaryawan` (`idJabatan`);

--
-- Indeks untuk tabel `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `book`
--
ALTER TABLE `book`
  MODIFY `book_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT untuk tabel `card_detail`
--
ALTER TABLE `card_detail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT untuk tabel `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT untuk tabel `jabatan`
--
ALTER TABLE `jabatan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT untuk tabel `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT untuk tabel `user`
--
ALTER TABLE `user`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `karyawan`
--
ALTER TABLE `karyawan`
  ADD CONSTRAINT `FK_JabatanKaryawan` FOREIGN KEY (`idJabatan`) REFERENCES `jabatan` (`id`);
--
-- Database: `test`
--
CREATE DATABASE IF NOT EXISTS `test` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `test`;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
