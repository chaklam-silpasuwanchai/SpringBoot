package com.example.rr.tabegateway;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

import org.springframework.stereotype.Repository;

@Repository
public class ContractTableDataGateway extends AbstractTableDataGateway {
	public ContractTableDataGateway(DataSource dataSource) {
		super(dataSource);
	}
	private static final String SQL_GET_ALL = "SELECT * from contracts";
	private static final String SELECT_CONTRACT_SQL =
		      "SELECT c.id AS id, c.product_id AS product_id, c.revenue AS revenue, c.dateSigned AS dateSigned, p.type AS type "
		      + "FROM contracts c, products p "
		      + "WHERE c.id = ? AND c.product_id = p.id";
	private static final String INSERT_CONTRACT_SQL =
			"INSERT INTO contracts (product_id, revenue, dateSigned) VALUES (?, ?, ?)";
	public ResultSet findAll() {
		try (
				Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(SQL_GET_ALL);
			) {
			try (ResultSet rs = ps.executeQuery()) {
				//convert result to CachedRowSet for better efficiency
				//CachedRowSet stores data in memory so you can work on the data without keeping
				//the connection open all the time.
				RowSetFactory factory = RowSetProvider.newFactory();
				CachedRowSet crs = factory.createCachedRowSet();
				crs.populate(rs);
				return crs;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public ResultSet findOne(long contractId) {
		try (
				Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(SELECT_CONTRACT_SQL);
			) {
			ps.setLong(1, contractId);
			try (ResultSet rs = ps.executeQuery()) {
				RowSetFactory factory = RowSetProvider.newFactory();
				CachedRowSet crs = factory.createCachedRowSet();
				crs.populate(rs);
				return crs;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public int insert(
			int productId, BigDecimal contractPrice, LocalDate dateSigned) {
		try (
				Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(INSERT_CONTRACT_SQL, Statement.RETURN_GENERATED_KEYS);
			) {
			ps.setLong(1, productId);
			ps.setBigDecimal(2, contractPrice);
			ps.setDate(3, java.sql.Date.valueOf(dateSigned));
			ps.executeUpdate();
			try (ResultSet rs = ps.getGeneratedKeys()) {
				rs.next();
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
