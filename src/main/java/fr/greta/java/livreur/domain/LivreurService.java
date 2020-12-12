package fr.greta.java.livreur.domain;

import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.generic.exception.ServiceException;
import fr.greta.java.livreur.persistence.LivreurEntity;
import fr.greta.java.livreur.persistence.LivreurRepository;

import java.util.List;


public class LivreurService {

    private LivreurWrapper wrapper = new LivreurWrapper();
    private LivreurRepository repository = new LivreurRepository();

    public List<Livreur> findAllLivreurs() throws ServiceException {
        try {
            List<Livreur> models = wrapper.fromEntities(repository.findAllLivreurs());
            return models;

        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public List<Livreur> findAllLivreursAvailable() throws ServiceException {
        try {
            List<Livreur> models = wrapper.fromEntities(repository.findAllLivreursAvailable());
            return models;

        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public void updatePresence(Livreur livreur) throws ServiceException {
            try {
                LivreurEntity livreurPresence = wrapper.toEntity(livreur);
                repository.updatePresence(livreurPresence);

            } catch (RepositoryException e) {
            }

    }
}
