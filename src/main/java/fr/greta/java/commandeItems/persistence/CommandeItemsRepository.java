package fr.greta.java.commandeItems.persistence;

import fr.greta.java.commande.persistence.CommandeEntity;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.generic.tools.ConnectionFactory;
import fr.greta.java.generic.tools.JdbcTool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommandeItemsRepository {



    private final String SELECT_REQUEST = "SELECT _burger_id, _label, _price FROM _burger";
    private final String INSERT_REQUEST = "INSERT INTO _commandeitems (_burger_id, _commande_id, _quantity) VALUES (?, ?, ?)";
    private final String SELECT_REQUEST_WHERE_ID = SELECT_REQUEST + " WHERE _burger_id = ?";

    private ConnectionFactory connectionFactory = new ConnectionFactory();

    public CommandeItemsEntity create(CommandeItemsEntity entity) throws RepositoryException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            conn = connectionFactory.create();
            preparedStatement = conn.prepareStatement(INSERT_REQUEST);
            preparedStatement.setInt(1, entity.getBurgerId());
            preparedStatement.setInt(2, entity.getCommandeId());
            preparedStatement.setInt(3, entity.getQuantity());
            preparedStatement.executeUpdate();
            return entity;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requête:" + INSERT_REQUEST, e);
        } finally {
            JdbcTool.close(rs, preparedStatement, conn);
        }
    }
}
