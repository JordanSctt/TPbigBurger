package fr.greta.java.commande.domain;

import fr.greta.java.commande.persistence.CommandeRepository;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.generic.exception.ServiceException;

public class CommandeService {

    private CommandeWrapper wrapper = new CommandeWrapper();
    private CommandeRepository repository = new CommandeRepository();

    public Commande create(Commande commande) throws ServiceException {

            try {
                Commande commandeCreer = wrapper.fromEntity(repository.create(wrapper.toEntity(commande)));
                return commandeCreer;

            } catch (RepositoryException e) {
                throw new ServiceException(e);
            }

    }

}
