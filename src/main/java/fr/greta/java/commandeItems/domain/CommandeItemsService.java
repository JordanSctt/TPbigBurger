package fr.greta.java.commandeItems.domain;

import fr.greta.java.burger.domain.ProduitService;
import fr.greta.java.commande.domain.Commande;
import fr.greta.java.commande.domain.CommandeService;
import fr.greta.java.commandeItems.persistence.CommandeItemsRepository;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.generic.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class CommandeItemsService {


    private CommandeItemsWrapper wrapper = new CommandeItemsWrapper();
    private CommandeItemsRepository repository = new CommandeItemsRepository();
    private ProduitService produitService = new ProduitService();
    private CommandeService commandeService = new CommandeService();


        public CommandeItems create(CommandeItems commandeItems) throws ServiceException {

            try {
                CommandeItems commandeItemsCreer = wrapper.fromEntity(repository.create(wrapper.toEntity(commandeItems)));
                return commandeItemsCreer;
            } catch (RepositoryException e) {
                throw new ServiceException(e);
            }
        }

    public List<CommandeItems> findAll() throws ServiceException {
        try {
            List<CommandeItems> models = wrapper.fromEntities(repository.findAll());
            for(CommandeItems commandeItems : models) {
                if(commandeItems.getProduit() != null && commandeItems.getCommande() != null) {
                    commandeItems.setProduit(produitService.findById(commandeItems.getProduit().getId()));
                    commandeItems.setCommande(commandeService.findById(commandeItems.getCommande().getId()));
                }
            }
            return models;
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public CommandeItems findById(int id) throws ServiceException {
        try {
            return wrapper.fromEntity(repository.findById(id));
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public List <CommandeItems> findAllCommandeItemsByCommandeID(int commandeID) throws RepositoryException, ServiceException {


        List <CommandeItems> commandeItemsList = wrapper.fromEntities(repository.findAllCommandeItemsByCommandeID(commandeID));


            for (CommandeItems commandeItems : commandeItemsList) {

                    commandeItems.setProduit(produitService.findById(commandeItems.getProduit().getId()));

            }

            return commandeItemsList;
    }

}
