package fr.greta.java.commande.domain;

import fr.greta.java.burger.domain.Burger;
import fr.greta.java.commande.persistence.CommandeEntity;
import fr.greta.java.commande.persistence.CommandeRepository;
import fr.greta.java.commandeItems.domain.CommandeItemsService;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.generic.exception.ServiceException;
import fr.greta.java.user.domain.UserService;

import java.util.List;

import java.time.LocalDateTime;
import java.util.List;

public class CommandeService {

    private CommandeWrapper wrapper = new CommandeWrapper();
    private CommandeRepository repository = new CommandeRepository();
    private UserService userService = new UserService();

    public Commande create(Commande commande) throws ServiceException {

            try {
                Commande commandeCreer = wrapper.fromEntity(repository.create(wrapper.toEntity(commande)));
                return commandeCreer;
            } catch (RepositoryException e) {
                throw new ServiceException(e);
            }
    }

    public List<Commande> findAllCommandes() throws ServiceException {

        try {
            List<Commande> models = wrapper.fromEntities(repository.findAllCommandes());
            for(Commande commande : models) {
                if(commande.getUser() != null) {
                    commande.setUser(userService.findById(commande.getUser().getId()));
                }
            }
            return models;

        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public Commande findById(int id) throws ServiceException {
        try {
            return wrapper.fromEntity(repository.findById(id));
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }




    public Commande calculDateFin(Commande commande) {

        LocalDateTime dateCommande = commande.getStartDatePrep();
        if (verifSiCommandeEnCours()) {
            commande.setEndDatePrep(dateCommande.plusMinutes(30));
        } else {
            commande.setEndDatePrep(dateCommande.plusMinutes(20));
        }
        return commande;
    }

    private boolean verifSiCommandeEnCours() {

            List<CommandeEntity> entityList = null;
        try {
           entityList = repository.chercheByEtat();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
            return !entityList.isEmpty();
    }



}
