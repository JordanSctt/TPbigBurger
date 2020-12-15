package fr.greta.java.livreur.persistence;

import fr.greta.java.commande.persistence.CommandeEntity;
import fr.greta.java.commande.persistence.CommandeRepository;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.generic.tools.ConnectionFactory;
import fr.greta.java.generic.tools.JdbcTool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivreurRepository {


    private final String SELECT_REQUEST = "SELECT * FROM _livreur ORDER BY _livreur_id";
    private final String UPDATE_REQUEST = "UPDATE _livreur SET _presence = ? WHERE _livreur_id = ?";
    private final String SEARCH_AVAILABLE_LIVREUR = "SELECT * FROM _livreur WHERE _commande_id IS null AND _presence = 'PRESENT'";
    private final String SET_COMMANDE = "UPDATE _livreur SET _commande_id = ? WHERE _livreur_id = ?";
    private final String INIT_COMMANDE = "UPDATE _livreur SET _commande_id = null WHERE _commande_id = ?";
    private final String SET_PRESENCE_LIVRAISON = "UPDATE _livreur SET _presence = 'EN_LIVRAISON' WHERE _commande_id = ?";
    private final String SET_PRESENCE_PRESENT = "UPDATE _livreur SET _presence = 'PRESENT' WHERE _livreur_id = ?";
    

    private ConnectionFactory connectionFactory = new ConnectionFactory();
    private CommandeRepository commandeRepository = new CommandeRepository();

    public List<LivreurEntity> findAllLivreurs() throws RepositoryException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            conn = connectionFactory.create();
            stmt = conn.prepareStatement(SELECT_REQUEST);
            resultSet = stmt.executeQuery();

            List<LivreurEntity> list = new ArrayList<>();
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

    public List<LivreurEntity> findAllLivreursAvailable() throws RepositoryException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            conn = connectionFactory.create();
            stmt = conn.prepareStatement(SEARCH_AVAILABLE_LIVREUR);
            resultSet = stmt.executeQuery();

            List<LivreurEntity> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(toEntity(resultSet));
            }
            return list;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requête:" + SEARCH_AVAILABLE_LIVREUR, e);
        } finally {
            JdbcTool.close(resultSet, stmt, conn);
        }
    }



    public void updatePresence(LivreurEntity entity) throws RepositoryException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            conn = connectionFactory.create();
            preparedStatement = conn.prepareStatement(UPDATE_REQUEST);
            preparedStatement.setString(1, String.valueOf(entity.getPresence()));
            preparedStatement.setInt(2, entity.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requête:" + UPDATE_REQUEST, e);
        } finally {
            JdbcTool.close(rs, preparedStatement, conn);
        }
    }

    public void setCommande(LivreurEntity livreurEntity, CommandeEntity commandeEntity) throws RepositoryException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            conn = connectionFactory.create();
            preparedStatement = conn.prepareStatement(SET_COMMANDE);
            preparedStatement.setInt(1, commandeEntity.getId());
            preparedStatement.setInt(2, livreurEntity.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requête:" + UPDATE_REQUEST, e);
        } finally {
            JdbcTool.close(rs, preparedStatement, conn);
        }
    }

    public void initCommandeLivreur(int commandeID) throws RepositoryException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            conn = connectionFactory.create();
            preparedStatement = conn.prepareStatement(INIT_COMMANDE);
            preparedStatement.setInt(1, commandeID);
            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requête:" + INIT_COMMANDE, e);
        } finally {
            JdbcTool.close(resultSet, preparedStatement, conn);
        }

    }
    public void setPresenceLivraison(int commandeID) throws RepositoryException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            conn = connectionFactory.create();
            preparedStatement = conn.prepareStatement(SET_PRESENCE_LIVRAISON);
            preparedStatement.setInt(1, commandeID);
            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requête:" + SET_PRESENCE_LIVRAISON, e);
        } finally {
            JdbcTool.close(rs, preparedStatement, conn);
        }
    }

    public void setPresencePresent(int livreurID) throws RepositoryException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            conn = connectionFactory.create();
            preparedStatement = conn.prepareStatement(SET_PRESENCE_PRESENT);
            preparedStatement.setInt(1, livreurID);
            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requête:" + SET_PRESENCE_PRESENT, e);
        } finally {
            JdbcTool.close(rs, preparedStatement, conn);
        }
    }

  
    private LivreurEntity toEntity(ResultSet resultSet) throws SQLException, RepositoryException {
        LivreurEntity entity = new LivreurEntity();
        entity.setId(resultSet.getInt("_livreur_id"));
        entity.setCommandeIdEnCours(resultSet.getInt("_commande_id"));
        entity.setName(resultSet.getString("_name"));
        entity.setPresence(resultSet.getString("_presence"));
        return entity;
    }


}
