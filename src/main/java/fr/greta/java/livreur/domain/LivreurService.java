package fr.greta.java.livreur.domain;

import fr.greta.java.commande.domain.Commande;
import fr.greta.java.commande.domain.CommandeEtat;
import fr.greta.java.commande.domain.CommandeWrapper;
import fr.greta.java.commande.persistence.CommandeEntity;
import fr.greta.java.commande.persistence.CommandeRepository;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.generic.exception.ServiceException;
import fr.greta.java.livreur.persistence.LivreurEntity;
import fr.greta.java.livreur.persistence.LivreurRepository;
import fr.greta.java.user.domain.User;

import java.util.List;


public class LivreurService {

    private LivreurWrapper wrapperLivreur = new LivreurWrapper();
    private LivreurRepository repository = new LivreurRepository();
    private CommandeWrapper wrapperCommande = new CommandeWrapper();


    public Livreur findByNameAndPassword(String name, String password) throws ServiceException {
        try {
            Livreur livreurModel = wrapperLivreur.fromEntity(repository.findByNameAndPassword(name, password));
            return livreurModel;

        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public Livreur findByiD(int id) throws RepositoryException, ServiceException {
        Livreur livreur = wrapperLivreur.fromEntity(repository.findById(id));

        return livreur;
    }

    public List<Livreur> findAllLivreurs() throws ServiceException {
        try {
            List<Livreur> models = wrapperLivreur.fromEntities(repository.findAllLivreurs());
            return models;

        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public List<Livreur> findAllLivreursAvailable() throws ServiceException {
        try {
            List<Livreur> models = wrapperLivreur.fromEntities(repository.findAllLivreursAvailable());
            return models;

        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public void updatePresence(Livreur livreur) throws ServiceException {
        try {
            LivreurEntity livreurPresence = wrapperLivreur.toEntity(livreur);
            repository.updatePresence(livreurPresence);

        } catch (RepositoryException e) {
        }

    }
    
    

    public void setCommande(Livreur livreur, Commande commande) throws ServiceException, RepositoryException {
        LivreurEntity livreurEntity = wrapperLivreur.toEntity(livreur);
        CommandeEntity commandeEntity = wrapperCommande.toEntity(commande);
        repository.setCommande(livreurEntity, commandeEntity);

    }

    public void setPresencePresent(int livreurID) throws RepositoryException {

        repository.setPresencePresent(livreurID);

    }
}
