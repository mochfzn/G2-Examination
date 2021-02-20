package com.nexsoft.minimarket.repository;
import com.nexsoft.minimarket.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("CustomerRepository")
public class CustomerRepositoryImpl implements CustomerRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Customer> findAll() {
        List<Customer> customerList;

        try {
            customerList = this.jdbcTemplate.query("SELECT * FROM customer",
                    (rs, rowNum) ->
                            new Customer(
                                    rs.getString("id"),
                                    rs.getString("nama"),
                                    rs.getString("alamat"),
                                    rs.getString("telepon")
                            ));
        } catch (IndexOutOfBoundsException e) {
            customerList = null;
        }

        return customerList;
    }

    @Override
    public Customer findById(String id) {
        Customer customer;

        try {
            customer = this.jdbcTemplate.query(
                    "SELECT * FROM customer WHERE id = ?",
                    preparedStatement -> preparedStatement.setString(1, id),
                    (rs, rowNum) ->
                            new Customer(
                                    rs.getString("id"),
                                    rs.getString("nama"),
                                    rs.getString("alamat"),
                                    rs.getString("telepon")
                            )
            ).get(0);
        } catch(IndexOutOfBoundsException e) {
            customer = null;
        }

        return customer;
    }

    @Override
    public List<Customer> findByName(String nama) {
        List<Customer> customerList;

        try {
            customerList = jdbcTemplate.query(
                    "SELECT * FROM customer WHERE nama LIKE ?",
                    preparedStatement -> preparedStatement.setString(1, "%" + nama + "%"),
                    (rs, rowNum) ->
                            new Customer(
                                    rs.getString("id"),
                                    rs.getString("nama"),
                                    rs.getString("alamat"),
                                    rs.getString("telepon")
                            )
            );
        } catch (IndexOutOfBoundsException e) {
            customerList = null;
        }

        return customerList;
    }

    @Override
    public Customer findByNameSpecific(String nama) {
        Customer customer;

        try {
            customer = jdbcTemplate.query(
                    "SELECT * FROM customer WHERE nama = ?",
                    preparedStatement -> preparedStatement.setString(1, nama),
                    (rs, rowNum) ->
                            new Customer(
                                    rs.getString("id"),
                                    rs.getString("nama"),
                                    rs.getString("alamat"),
                                    rs.getString("telepon")
                            )
            ).get(0);
        } catch (IndexOutOfBoundsException e) {
            customer = null;
        }

        return customer;
    }

    @Override
    public int insert(Customer customer) {
        return jdbcTemplate.update(
                "INSERT INTO customer(id, nama, alamat, telepon) values(?, ?, ?, ?)",
                customer.getId(), customer.getNama(), customer.getAlamat(), customer.getTelepon()
        );
    }

    @Override
    public int update(Customer customer) {
        return jdbcTemplate.update(
                "UPDATE customer SET nama = ?, alamat = ?, telepon = ? WHERE id = ?",
                customer.getNama(), customer.getAlamat(), customer.getTelepon(), customer.getId()
        );
    }

    @Override
    public int delete(String id) {
        return jdbcTemplate.update(
                "DELETE FROM customer WHERE id = ?", id
        );
    }
}
