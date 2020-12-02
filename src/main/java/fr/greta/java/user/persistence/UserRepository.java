package fr.greta.java.user.persistence;

import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.generic.tools.ConnectionFactory;
import fr.greta.java.generic.tools.JdbcTool;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    private ConnectionFactory connectionFactory = new ConnectionFactory();

    private final String SELECT_REQUEST = "SELECT _name, _password, _phone FROM _user WHERE _name = ? AND _password = ?";
    private final String INSERT_REQUEST = "INSERT INTO _user (_name, _password, _phone) VALUES (?, ?, ?)";

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
                throw new RepositoryException("Erreur lors de l'execution de la requête:" + SELECT_REQUEST);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RepositoryException("Erreur lors de l'execution de la requête:" + SELECT_REQUEST, e);
            } finally {
                JdbcTool.close(resultSet, stmt, conn);
            }
        }

    public UserEntity create(UserEntity entity) throws RepositoryException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = connectionFactory.create();
            preparedStatement = conn.prepareStatement(INSERT_REQUEST);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getPhone());
            preparedStatement.executeUpdate();

            return entity;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requête:" + INSERT_REQUEST, e);
        } finally {
            JdbcTool.close(preparedStatement, conn);
        }
    }


    private UserEntity toEntity(ResultSet resultSet) throws SQLException {
        UserEntity entity = new UserEntity();
        //entity.setId(resultSet.getInt("_user_id"));
        entity.setName(resultSet.getString("_name"));
        entity.setPassword(resultSet.getString("_password"));
        entity.setPhone(resultSet.getString("_phone"));
        return entity;
    }


}
