package fr.greta.java.commandeItems.domain;

import fr.greta.java.burger.domain.BurgerService;
import fr.greta.java.commande.domain.Commande;
import fr.greta.java.commande.domain.CommandeService;
import fr.greta.java.commandeItems.persistence.CommandeItemsRepository;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.generic.exception.ServiceException;
import fr.greta.java.user.domain.UserService;

import java.util.List;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommandeItemsService {


    private CommandeItemsWrapper wrapper = new CommandeItemsWrapper();
    private CommandeItemsRepository repository = new CommandeItemsRepository();
    private BurgerService burgerService = new BurgerService();
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
                if(commandeItems.getBurger() != null && commandeItems.getCommande() != null) {
                    commandeItems.setBurger(burgerService.findById(commandeItems.getBurger().getId()));
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

}
