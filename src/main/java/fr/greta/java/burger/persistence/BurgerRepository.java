package fr.greta.java.burger.persistence;

import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.generic.tools.ConnectionFactory;
import fr.greta.java.generic.tools.JdbcTool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BurgerRepository {

    private final String SELECT_REQUEST = "SELECT _burger_id, _label, _price FROM _burger";
    private final String INSERT_REQUEST = "INSERT INTO _burger (_label, _price) VALUES (?, ?)";
    private final String SELECT_REQUEST_WHERE_ID = SELECT_REQUEST + " WHERE _burger_id = ?";

    private ConnectionFactory connectionFactory = new ConnectionFactory();


    public BurgerEntity create(BurgerEntity entity) throws RepositoryException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            conn = connectionFactory.create();
            preparedStatement = conn.prepareStatement(INSERT_REQUEST, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getLabel());
            preparedStatement.setDouble(2, entity.getPrice());
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

    public List<BurgerEntity> findAll() throws RepositoryException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            conn = connectionFactory.create();
            stmt = conn.prepareStatement(SELECT_REQUEST);
            resultSet = stmt.executeQuery();

            List<BurgerEntity> burgerList = new ArrayList<>();
            while (resultSet.next()) {
                burgerList.add(toEntity(resultSet));
            }
            return burgerList;
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
            rs = stmt.executeQuery("DELETE FROM _burger WHERE _burger_id = "+id+"");

        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requête: DELETE", e);
        } finally {
            JdbcTool.close(rs, stmt, conn);
        }
    }

    public BurgerEntity findById(int id) throws RepositoryException {
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



    private BurgerEntity toEntity(ResultSet resultSet) throws SQLException {
        BurgerEntity entity = new BurgerEntity();
        entity.setId(resultSet.getInt("_burger_id"));
        entity.setLabel(resultSet.getString("_label"));
        entity.setPrice(resultSet.getDouble("_price"));

        return entity;
    }








}
