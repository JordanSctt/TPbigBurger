package fr.greta.java.commande.persistence;

import fr.greta.java.burger.persistence.BurgerEntity;
import fr.greta.java.commandeItems.persistence.CommandeItemsEntity;
import fr.greta.java.commande.domain.Commande;
import fr.greta.java.commande.domain.CommandeEtat;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.generic.tools.ConnectionFactory;
import fr.greta.java.generic.tools.JdbcTool;
import fr.greta.java.user.persistence.UserEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.List;

public class CommandeRepository {


    private final String SEARCH_REQUEST_BYETAT = "SELECT _commande_id FROM _commande WHERE _etatcommande = ?";
    private final String INSERT_REQUEST = "INSERT INTO _commande (_user_id, _startdateprep, _enddateprep, _etatcommande) VALUES (?, ?, ?, ?)";
    private final String SELECT_COMMANDE_BY_ID = "SELECT * FROM _commande WHERE _commande_id = ?";
    private final String SELECT_REQUEST = "SELECT * FROM _commande";
    private final String SEARCH_REQUEST_BY_USER_ID = "SELECT * FROM _commande WHERE _user_id = ?";


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
            stmt.setString(1, CommandeEtat.EN_COURS_DE_TRAITEMENT.name());
            resultSet = stmt.executeQuery();

            List<CommandeEntity> list = new ArrayList<>();

            while (resultSet.next()) {
                list.add(toEntityEtat(resultSet));
            }
            return list;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requête:" + SEARCH_REQUEST_BYETAT, e);
        } finally {
            JdbcTool.close(resultSet, stmt, conn);
        }
    }

    public List<CommandeEntity> findAllCommandes() throws RepositoryException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            conn = connectionFactory.create();
            stmt = conn.prepareStatement(SELECT_REQUEST);
            resultSet = stmt.executeQuery();

            List<CommandeEntity> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(toEntity(resultSet));
            }
            return list;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requête:" + SELECT_REQUEST, e);
        } finally {
            JdbcTool.close(resultSet, stmt, conn);
        }
    }
    public List<CommandeEntity> findAllCommandesByUserID(int userID) throws RepositoryException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            conn = connectionFactory.create();
            stmt = conn.prepareStatement(SEARCH_REQUEST_BY_USER_ID);
            stmt.setInt(1, userID);
            resultSet = stmt.executeQuery();

            List<CommandeEntity> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(toEntity(resultSet));
            }
            return list;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requête:" + SELECT_REQUEST, e);
        } finally {
            JdbcTool.close(resultSet, stmt, conn);
        }
    }

    public CommandeEntity findById(int id) throws RepositoryException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            conn = connectionFactory.create();
            stmt = conn.prepareStatement(SELECT_COMMANDE_BY_ID);
            stmt.setInt(1, id);
            resultSet = stmt.executeQuery();


            if (resultSet.next()) {
                return toEntity(resultSet);
            }

            throw new RepositoryException("Erreur lors de l'execution de la requête:" + SELECT_COMMANDE_BY_ID);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requête:" + SELECT_COMMANDE_BY_ID, e);
        } finally {
            JdbcTool.close(resultSet, stmt, conn);
        }
    }



    private CommandeEntity toEntityEtat(ResultSet resultSet) throws SQLException {
        CommandeEntity entity = new CommandeEntity();
        entity.setId(resultSet.getInt("_commande_id"));

        return entity;
    }
    private CommandeEntity toEntity(ResultSet resultSet) throws SQLException {
        CommandeEntity entity = new CommandeEntity();
        entity.setId(resultSet.getInt("_commande_id"));
        entity.setUserID(resultSet.getInt("_user_id"));
        entity.setStartDatePrep(resultSet.getTimestamp("_startdateprep"));
        entity.setEndDatePrep(resultSet.getTimestamp("_enddateprep"));
        entity.setEtatCommande(resultSet.getString("_etatcommande"));

        return entity;
    }

}
