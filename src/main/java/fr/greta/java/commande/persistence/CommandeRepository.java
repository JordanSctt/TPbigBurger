package fr.greta.java.commande.persistence;

import fr.greta.java.commande.domain.CommandeEtat;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.generic.tools.ConnectionFactory;
import fr.greta.java.generic.tools.JdbcTool;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommandeRepository {


    private final String SEARCH_REQUEST_BYETAT = "SELECT _commande_id FROM _commande WHERE _etatcommande = ? OR _etatcommande = ?";
    private final String INSERT_REQUEST = "INSERT INTO _commande (_user_id, _startdateprep, _enddateprep, _etatcommande, _typeLivraison) VALUES (?, ?, ?, ?, ?)";
    private final String INSERT_REQUEST_LIVRAISON = "INSERT INTO _commande (_user_id, _startdateprep, _enddateprep, _etatcommande, _typeLivraison, _estimationLivraison) VALUES (?, ?, ?, ?, ?, ?)";
    private final String SELECT_COMMANDE_BY_ID = "SELECT * FROM _commande WHERE _commande_id = ?";
    private final String SELECT_REQUEST = "SELECT * FROM _commande ORDER BY _commande_id";
    private final String SEARCH_REQUEST_BY_USER_ID = "SELECT * FROM _commande WHERE _user_id = ? ORDER BY _commande_id DESC";
    private final String SEARCH_LASTCOMMANDE_BY_USER_ID = "SELECT * FROM _commande WHERE _user_id = ? AND _commande_id =( SELECT MAX(_commande_id) FROM _commande)";
    private final String UPDATE_REQUEST = "UPDATE _commande SET _etatcommande = ? WHERE _commande_id = ?";
    private final String UPDATE_DATEDEBUT_LIVRAISON = "UPDATE _commande SET _startdatelivraison = ?, _enddatelivraison = ? WHERE _commande_id = ?";
    private final String SELECT_ALL_ENCOURS = "SELECT * FROM _commande WHERE _etatcommande != 'LIVRE' AND _etatcommande != 'PRETE' ORDER BY _commande_id";
    private final String SELECT_ALL_TERMINES = "SELECT * FROM _commande WHERE _etatcommande = 'LIVRE' OR _etatcommande = 'PRETE' ORDER BY _commande_id DESC";
    private final String SEARCH_COMMANDEID_BY_LIVREURID = "SELECT _commande_id FROM _commande WHERE _livreur_id = ? ORDER BY _commande_id";

    private ConnectionFactory connectionFactory = new ConnectionFactory();


    public CommandeEntity createEmporter(CommandeEntity entity) throws RepositoryException {
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
            preparedStatement.setString(5, entity.getTypeLivraison());
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
    public CommandeEntity createLivraison(CommandeEntity entity) throws RepositoryException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            conn = connectionFactory.create();
            preparedStatement = conn.prepareStatement(INSERT_REQUEST_LIVRAISON, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, entity.getUserID());
            preparedStatement.setTimestamp(2, entity.getStartDatePrep());
            preparedStatement.setTimestamp(3, entity.getEndDatePrep());
            preparedStatement.setString(4, entity.getEtatCommande());
            preparedStatement.setString(5, entity.getTypeLivraison());
            preparedStatement.setTimestamp(6, entity.getEstimationLivraison());
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
            stmt.setString(2, CommandeEtat.EN_COURS_DE_TRAITEMENT.name());
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

    public List<CommandeEntity> findAllCommandesEnCours() throws RepositoryException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            conn = connectionFactory.create();
            stmt = conn.prepareStatement(SELECT_ALL_ENCOURS);
            resultSet = stmt.executeQuery();

            List<CommandeEntity> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(toEntity(resultSet));
            }
            return list;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requête:" + SELECT_ALL_ENCOURS, e);
        } finally {
            JdbcTool.close(resultSet, stmt, conn);
        }
    }

    public List<CommandeEntity> findAllCommandesTermines() throws RepositoryException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            conn = connectionFactory.create();
            stmt = conn.prepareStatement(SELECT_ALL_TERMINES);
            resultSet = stmt.executeQuery();

            List<CommandeEntity> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(toEntity(resultSet));
            }
            return list;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requête:" + SELECT_ALL_TERMINES, e);
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
            throw new RepositoryException("Erreur lors de l'execution de la requête:" + SEARCH_REQUEST_BY_USER_ID, e);
        } finally {
            JdbcTool.close(resultSet, stmt, conn);
        }
    }

    public List<CommandeEntity> findAllCommandesByLivreurID(int userID) throws RepositoryException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            conn = connectionFactory.create();
            stmt = conn.prepareStatement(SEARCH_COMMANDEID_BY_LIVREURID);
            stmt.setInt(1, userID);
            resultSet = stmt.executeQuery();

            List<CommandeEntity> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(toEntity(resultSet));
            }
            return list;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requête:" + SEARCH_COMMANDEID_BY_LIVREURID, e);
        } finally {
            JdbcTool.close(resultSet, stmt, conn);
        }
    }

    public CommandeEntity findLastCommandeByUserID(int userID) throws RepositoryException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            conn = connectionFactory.create();
            stmt = conn.prepareStatement(SEARCH_LASTCOMMANDE_BY_USER_ID);
            stmt.setInt(1, userID);
            resultSet = stmt.executeQuery();

            if  (resultSet.next()) {
                return toEntity(resultSet);
            }

            throw new RepositoryException("Erreur lors de l'execution de la requête:" + SEARCH_LASTCOMMANDE_BY_USER_ID);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requête:" + SEARCH_LASTCOMMANDE_BY_USER_ID, e);
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

    public void updateEtatCommande(CommandeEntity commandeEntity, CommandeEtat commandeEtat) throws RepositoryException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = connectionFactory.create();
            stmt = conn.prepareStatement(UPDATE_REQUEST);
            stmt.setString(1, commandeEtat.name());
            stmt.setInt(2, commandeEntity.getId());

            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requete:" + UPDATE_REQUEST, e);
        } finally {
            JdbcTool.close(stmt, conn);
        }
    }

    public void updateHeureLivraison(int commandeID) throws RepositoryException {

        Timestamp debut = Timestamp.valueOf(LocalDateTime.now());
        Timestamp fin = Timestamp.valueOf(LocalDateTime.now().plusMinutes(10));

        Connection conn = null;
        PreparedStatement stmt = null;


        try {
            conn = connectionFactory.create();
            stmt = conn.prepareStatement(UPDATE_DATEDEBUT_LIVRAISON);
            stmt.setTimestamp(1, debut);
            stmt.setTimestamp(2, fin);
            stmt.setInt(3, commandeID);

            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requete:" + UPDATE_DATEDEBUT_LIVRAISON, e);
        } finally {
            JdbcTool.close(stmt, conn);
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
        entity.setStartDateLivraison(resultSet.getTimestamp("_startdatelivraison"));
        entity.setEndDateLivraison(resultSet.getTimestamp("_enddatelivraison"));
        entity.setEtatCommande(resultSet.getString("_etatcommande"));
        entity.setTypeLivraison(resultSet.getString("_typelivraison"));
        entity.setEstimationLivraison(resultSet.getTimestamp("_estimationlivraison"));
        return entity;
    }



}
