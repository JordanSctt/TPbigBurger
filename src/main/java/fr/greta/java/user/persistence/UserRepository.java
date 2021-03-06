package fr.greta.java.user.persistence;

import fr.greta.java.commande.domain.CommandeEtat;
import fr.greta.java.commande.persistence.CommandeEntity;
import fr.greta.java.cuisto.persistence.CuistoEntity;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.generic.tools.ConnectionFactory;
import fr.greta.java.generic.tools.JdbcTool;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private ConnectionFactory connectionFactory = new ConnectionFactory();

    private final String SELECT_REQUEST = "SELECT * FROM _user WHERE _name = ? AND _password = ?";
    private final String INSERT_REQUEST = "INSERT INTO _user (_name, _password, _phone, _adresse, _role) VALUES (?, ?, ?, ?, ?)";
    private final String INSERT_REQUEST_PLUSADRESSE = "INSERT INTO _user (_name, _password, _phone, _role, _adresse) VALUES (?, ?, ?, ?, ?)";
    private final String INSERT_ADRESSE = "INSERT INTO _user (_adresse) VALUES (?) WHERE _user_id = ?";

    private final String SELECT_REQUEST_WHERE_ID = "SELECT * FROM _user WHERE _user_id = ?";


    public UserEntity findByNameAndPassword(String name, String password) throws RepositoryException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            conn = connectionFactory.create();
            stmt = conn.prepareStatement(SELECT_REQUEST);
            stmt.setString(1, name);
            stmt.setString(2, password);
            resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return toEntity(resultSet);
            }
            UserEntity entity = new UserEntity();
                    return  entity;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requête:" + SELECT_REQUEST, e);
        } finally {
            JdbcTool.close(resultSet, stmt, conn);
        }
    }

    public UserEntity findById(int id) throws RepositoryException {
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

    public UserEntity create(UserEntity entity) throws RepositoryException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            conn = connectionFactory.create();
            preparedStatement = conn.prepareStatement(INSERT_REQUEST, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getPhone());
            preparedStatement.setString(4, entity.getAdresse());
            preparedStatement.setString(5, "user");
            preparedStatement.executeUpdate();

            rs = preparedStatement.getGeneratedKeys();
            if(rs.next()) {
                entity.setId(rs.getInt(1));
            }
            return entity;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requête:" + INSERT_REQUEST, e);
        } finally {
            JdbcTool.close(rs, preparedStatement, conn);
        }
    }



    private UserEntity toEntity(ResultSet resultSet) throws SQLException {
        UserEntity entity;
        if (resultSet.getString("_name").isEmpty() || resultSet.getString("_name") == null ||
                resultSet.getString("_password").isEmpty() || resultSet.getString("_password") == null) {

            return entity = new UserEntity();


        }
      /*  if (resultSet.getString("_role").equals("admin")) {
            entity = new AdminEntity();
        } else {
            entity = new UserEntity();
        } */
        entity = new UserEntity();

        entity.setId(resultSet.getInt("_user_id"));
        entity.setName(resultSet.getString("_name"));
        entity.setPassword(resultSet.getString("_password"));
        entity.setPhone(resultSet.getString("_phone"));
        entity.setRole(resultSet.getString("_role"));
        entity.setAdresse(resultSet.getString("_adresse"));
        return entity;
    }



    }

