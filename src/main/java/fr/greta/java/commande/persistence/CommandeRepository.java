package fr.greta.java.commande.persistence;

import fr.greta.java.burger.persistence.BurgerEntity;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.generic.tools.ConnectionFactory;
import fr.greta.java.generic.tools.JdbcTool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommandeRepository {

    private final String SELECT_REQUEST = "SELECT _burger_id, _label, _price FROM _burger";
    private final String INSERT_REQUEST = "INSERT INTO _commande (_user_id) VALUES (?)";
    private final String SELECT_REQUEST_WHERE_ID = SELECT_REQUEST + " WHERE _burger_id = ?";

        private ConnectionFactory connectionFactory = new ConnectionFactory();

    public CommandeEntity create(CommandeEntity entity) throws RepositoryException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            conn = connectionFactory.create();
            preparedStatement = conn.prepareStatement(INSERT_REQUEST, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, entity.getUserID());
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
}
