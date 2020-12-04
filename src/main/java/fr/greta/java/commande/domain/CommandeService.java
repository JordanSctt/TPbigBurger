package fr.greta.java.commande.domain;

import fr.greta.java.commande.persistence.CommandeEntity;
import fr.greta.java.commande.persistence.CommandeRepository;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.generic.exception.ServiceException;

import java.time.LocalDateTime;
import java.util.List;

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
