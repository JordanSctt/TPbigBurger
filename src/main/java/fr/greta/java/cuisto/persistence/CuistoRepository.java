package fr.greta.java.cuisto.persistence;

import fr.greta.java.cuisto.domain.Cuisto;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.generic.tools.ConnectionFactory;
import fr.greta.java.generic.tools.JdbcTool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CuistoRepository {

    private final String SEARCH_REQUEST_PRESENCE_CUISTO = "SELECT * FROM _cuisto";
    private ConnectionFactory connectionFactory = new ConnectionFactory();

    public List<CuistoEntity> searchPresenceCuisto() throws RepositoryException {

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

    private CuistoEntity toEntity(ResultSet resultSet) throws SQLException {
        CuistoEntity entity = new CuistoEntity();
        entity.setId(resultSet.getInt("_cuisto_id"));
        entity.setName(resultSet.getString("_name"));
        entity.setPresence(resultSet.getString("_presence"));
        return entity;
    }
}
