package fr.greta.java.burger.persistence;

import fr.greta.java.burger.domain.ProduitType;
import fr.greta.java.generic.exception.RepositoryException;

import fr.greta.java.generic.tools.ConnectionFactory;
import fr.greta.java.generic.tools.JdbcTool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitRepository {

    private final String SELECT_REQUEST = "SELECT _produit_id, _label, _price, _type FROM _produit";
    private final String INSERT_REQUEST = "INSERT INTO _produit (_label, _price, _type) VALUES (?, ?, ?)";
    private final String SELECT_REQUEST_WHERE_ID = SELECT_REQUEST + " WHERE _produit_id = ?";

    private ConnectionFactory connectionFactory = new ConnectionFactory();


    public ProduitEntity create(ProduitEntity entity) throws RepositoryException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            conn = connectionFactory.create();
            preparedStatement = conn.prepareStatement(INSERT_REQUEST, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getLabel());
            preparedStatement.setDouble(2, entity.getPrice());
            preparedStatement.setString(3, entity.getProduitType().name());
            preparedStatement.executeUpdate();

            rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                entity.setId(rs.getInt(1));
            }
            return entity;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requête:" + INSERT_REQUEST, e);
        } finally {
            JdbcTool.close(rs, preparedStatement, conn);
        }
    }

    public List<ProduitEntity> findAll() throws RepositoryException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            conn = connectionFactory.create();
            stmt = conn.prepareStatement(SELECT_REQUEST);
            resultSet = stmt.executeQuery();

            List<ProduitEntity> produitList = new ArrayList<>();
            while (resultSet.next()) {
                produitList.add(toEntity(resultSet));
            }
            return produitList;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requête:" + SELECT_REQUEST, e);
        } finally {
            JdbcTool.close(resultSet, stmt, conn);
        }
    }

    public void delete(int id) throws RepositoryException {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = connectionFactory.create();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("DELETE FROM _produit WHERE _produit_id = "+id+"");

        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requête: DELETE", e);
        } finally {
            JdbcTool.close(rs, stmt, conn);
        }
    }

    public ProduitEntity findById(int id) throws RepositoryException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            conn = connectionFactory.create();
            stmt = conn.prepareStatement(SELECT_REQUEST_WHERE_ID);
            stmt.setInt(1, id);
            resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return toEntity(resultSet);
            }
            throw new RepositoryException("Erreur lors de l'execution de la requête:" + SELECT_REQUEST_WHERE_ID);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requête:" + SELECT_REQUEST_WHERE_ID, e);
        } finally {
            JdbcTool.close(resultSet, stmt, conn);
        }
    }

    private ProduitEntity toEntity(ResultSet resultSet) throws SQLException {
        ProduitEntity entity = new ProduitEntity();
        entity.setId(resultSet.getInt("_produit_id"));
        entity.setLabel(resultSet.getString("_label"));
        entity.setPrice(resultSet.getDouble("_price"));
        entity.setProduitType(ProduitType.valueOf(resultSet.getString("_type")));

        return entity;
    }








}
