package fr.greta.java.commandeItems.persistence;

import fr.greta.java.commande.persistence.CommandeEntity;
import fr.greta.java.commandeItems.domain.CommandeItems;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.generic.tools.ConnectionFactory;
import fr.greta.java.generic.tools.JdbcTool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandeItemsRepository {



    private final String SELECT_REQUEST_ALL = "SELECT * FROM _commandeitems";
    private final String INSERT_REQUEST = "INSERT INTO _commandeitems (_burger_id, _commande_id, _quantity) VALUES (?, ?, ?)";
    private final String SELECT_REQUEST_WHERE_ID = SELECT_REQUEST_ALL + " WHERE _commande_id = ?";

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

    public List<CommandeItemsEntity> findAllCommandeItemsByCommandeID (int id) throws  RepositoryException {


        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        try {
            conn = connectionFactory.create();
            stmt = conn.prepareStatement(SELECT_REQUEST_WHERE_ID);
            stmt.setInt(1, id);
            resultSet = stmt.executeQuery();

            List<CommandeItemsEntity> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(toEntity(resultSet));
            }
            return list;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requête:" + SELECT_REQUEST_WHERE_ID, e);
        } finally {
            JdbcTool.close(resultSet, stmt, conn);
        }

    }

    private CommandeItemsEntity toEntity(ResultSet resultSet) throws SQLException {
        CommandeItemsEntity entity = new CommandeItemsEntity();
        entity.setBurgerId(resultSet.getInt("_burger_id"));
        entity.setCommandeId(resultSet.getInt("_commande_id"));
        entity.setQuantity(resultSet.getInt("_quantity"));

        return entity;
    }




}
