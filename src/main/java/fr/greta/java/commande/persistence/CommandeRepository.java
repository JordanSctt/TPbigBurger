package fr.greta.java.commande.persistence;

import fr.greta.java.burger.persistence.BurgerEntity;
import fr.greta.java.commande.domain.Commande;
import fr.greta.java.commande.domain.CommandeEtat;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.generic.tools.ConnectionFactory;
import fr.greta.java.generic.tools.JdbcTool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandeRepository {

    private final String SELECT_REQUEST = "SELECT _burger_id, _label, _price FROM _burger";
    private final String SEARCH_REQUEST_BYETAT = "SELECT _commande_id FROM _commande WHERE _etatcommande = ?";

    private final String INSERT_REQUEST = "INSERT INTO _commande (_user_id, _startdateprep, _enddateprep, _etatcommande) VALUES (?, ?, ?, ?)";
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
            preparedStatement.setTimestamp(2, entity.getStartDatePrep());
            preparedStatement.setTimestamp(3, entity.getEndDatePrep());
            preparedStatement.setString(4, entity.getEtatCommande());
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

    public List<CommandeEntity> chercheByEtat() throws RepositoryException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            conn = connectionFactory.create();
            stmt = conn.prepareStatement(SEARCH_REQUEST_BYETAT);
            stmt.setString(1, CommandeEtat.EN_COURS_DE_PREPARATION.name());
            resultSet = stmt.executeQuery();

            List<CommandeEntity> list = new ArrayList<>();

            while (resultSet.next()) {
                list.add(toEntity(resultSet));
            }
            return list;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requête:" + SEARCH_REQUEST_BYETAT, e);
        } finally {
            JdbcTool.close(resultSet, stmt, conn);
        }
    }

    private CommandeEntity toEntity(ResultSet resultSet) throws SQLException {
        CommandeEntity entity = new CommandeEntity();
        entity.setId(resultSet.getInt("_commande_id"));
        return entity;
    }

}
