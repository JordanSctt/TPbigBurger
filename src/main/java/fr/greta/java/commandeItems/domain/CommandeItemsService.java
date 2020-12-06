package fr.greta.java.commandeItems.domain;

import fr.greta.java.commande.domain.Commande;
import fr.greta.java.commandeItems.persistence.CommandeItemsRepository;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.generic.exception.ServiceException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommandeItemsService {


    private CommandeItemsWrapper wrapper = new CommandeItemsWrapper();
    private CommandeItemsRepository repository = new CommandeItemsRepository();

        public CommandeItems create(CommandeItems commandeItems) throws ServiceException {

            try {
                CommandeItems commandeItemsCreer = wrapper.fromEntity(repository.create(wrapper.toEntity(commandeItems)));
                return commandeItemsCreer;
            } catch (RepositoryException e) {
                throw new ServiceException(e);
            }

        }


}
