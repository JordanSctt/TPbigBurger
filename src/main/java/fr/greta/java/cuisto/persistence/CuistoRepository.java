package fr.greta.java.cuisto.persistence;

import fr.greta.java.cuisto.domain.Cuisto;
import fr.greta.java.cuisto.domain.CuistoPresence;
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

public class CuistoRepository {

    private final String SEARCH_REQUEST_PRESENCE_CUISTO = "SELECT * FROM _cuisto ORDER BY _cuisto_id";
    private final String UPDATE_PRESENCE_CUISTO_WHERE_ID = "UPDATE _cuisto SET _presence = ? WHERE _cuisto_id = ?";
    private final String SEARCH_BY_ID = "SELECT * FROM _cuisto WHERE _cuisto_id = ?";

    private ConnectionFactory connectionFactory = new ConnectionFactory();

    public List<CuistoEntity> findAllCuisto() throws RepositoryException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            conn = connectionFactory.create();
            stmt = conn.prepareStatement(SEARCH_REQUEST_PRESENCE_CUISTO);
            resultSet = stmt.executeQuery();
            List<CuistoEntity> list = new ArrayList<>();

            while (resultSet.next()) {
                list.add(toEntity(resultSet));
            }
            return list;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requête:" + SEARCH_REQUEST_PRESENCE_CUISTO, e);
        } finally {
            JdbcTool.close(resultSet, stmt, conn);
        }
    }

    public CuistoEntity findById(int id) throws RepositoryException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            conn = connectionFactory.create();
            stmt = conn.prepareStatement(SEARCH_BY_ID);
            stmt.setInt(1, id);
            resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return toEntity(resultSet);
            }
            throw new RepositoryException("Erreur lors de l'execution de la requête:" + SEARCH_BY_ID);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requête:" + SEARCH_BY_ID, e);
        } finally {
            JdbcTool.close(resultSet, stmt, conn);
        }
    }

    public void updatePresencePresentCuisto(int id) throws RepositoryException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = connectionFactory.create();
            stmt = conn.prepareStatement(UPDATE_PRESENCE_CUISTO_WHERE_ID);
            stmt.setString(1, CuistoPresence.PRESENT.name());
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requete:" + UPDATE_PRESENCE_CUISTO_WHERE_ID, e);
        } finally {
            JdbcTool.close(stmt, conn);
        }
    }
    public void updatePresenceAbsentCuisto(int id) throws RepositoryException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = connectionFactory.create();
            stmt = conn.prepareStatement(UPDATE_PRESENCE_CUISTO_WHERE_ID);
            stmt.setString(1, CuistoPresence.ABSENT.name());
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requete:" + UPDATE_PRESENCE_CUISTO_WHERE_ID, e);
        } finally {
            JdbcTool.close(stmt, conn);
        }
    }


    private CuistoEntity toEntity(ResultSet resultSet) throws SQLException {
        CuistoEntity entity = new CuistoEntity();
        entity.setId(resultSet.getInt("_cuisto_id"));
        entity.setName(resultSet.getString("_name"));
        entity.setPresence(resultSet.getString("_presence"));
        return entity;
    }


}
