package fr.greta.java.livreur.persistence;

import fr.greta.java.commande.persistence.CommandeEntity;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.generic.tools.ConnectionFactory;
import fr.greta.java.generic.tools.JdbcTool;
import fr.greta.java.livreur.domain.LivreurPresence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivreurRepository {


    private final String SELECT_REQUEST = "SELECT _livreur_id, _name, _presence FROM _livreur";

    private ConnectionFactory connectionFactory = new ConnectionFactory();

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

    private LivreurEntity toEntity(ResultSet resultSet) throws SQLException {
        LivreurEntity entity = new LivreurEntity();
        entity.setId(resultSet.getInt("_livreur_id"));
        if (entity.getCommandeID() > 0) {
            entity.setCommandeID(resultSet.getInt("_commande_id"));
        }
        entity.setName(resultSet.getString("_name"));
        entity.setPresence(LivreurPresence.valueOf(resultSet.getString("_presence")));

        return entity;
    }
}
