package fr.greta.java.commande.domain;

import fr.greta.java.commande.persistence.CommandeEntity;
import fr.greta.java.commande.persistence.CommandeRepository;
import fr.greta.java.cuisto.persistence.CuistoEntity;
import fr.greta.java.cuisto.persistence.CuistoRepository;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.generic.exception.ServiceException;
import fr.greta.java.livreur.domain.LivreurService;
import fr.greta.java.livreur.domain.LivreurWrapper;
import fr.greta.java.livreur.persistence.LivreurEntity;
import fr.greta.java.livreur.persistence.LivreurRepository;
import fr.greta.java.user.domain.User;
import fr.greta.java.user.domain.UserService;
import fr.greta.java.user.domain.UserWrapper;
import fr.greta.java.user.persistence.UserEntity;
import fr.greta.java.user.persistence.UserRepository;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import static fr.greta.java.commande.domain.CommandeEtat.*;

public class CommandeService {

    private CommandeWrapper wrapper = new CommandeWrapper();
    private CommandeRepository repository = new CommandeRepository();
    private UserService userService = new UserService();
    private CuistoRepository cuistoRepository = new CuistoRepository();
   private LivreurRepository livreurRepository = new LivreurRepository();
   private UserRepository userRepository = new UserRepository();
   private UserWrapper userWrapper = new UserWrapper();

    public Commande createEmporter(Commande commande) throws ServiceException {

            try {
                Commande commandeCreer = wrapper.fromEntity(repository.createEmporter(wrapper.toEntity(commande)));
               // commandeCreer.setUser(userService.findById(commandeCreer.getUser().getId()));

                return commandeCreer;
            } catch (RepositoryException e) {
                throw new ServiceException(e);
            }
    }


    public Commande createLivraison(Commande commande) throws ServiceException {

        try {
            Commande commandeCreer = wrapper.fromEntity(repository.createLivraison(wrapper.toEntity(commande)));
            return commandeCreer;
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public List<Commande> findAllCommandes() throws ServiceException {

        try {
            List<Commande> models = wrapper.fromEntities(repository.findAllCommandes());
           /* for(Commande commande : models) {
                if(commande.getUser() != null) {
                    commande.setUser(userService.findById(commande.getUser().getId()));
                }
            } */
            return models;

        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
    public List<Commande> findAllCommandesEnCours() throws ServiceException {

        try {
            List<Commande> models = wrapper.fromEntities(repository.findAllCommandesEnCours());
           /* for(Commande commande : models) {
                if(commande.getUser() != null) {
                    commande.setUser(userService.findById(commande.getUser().getId()));
                }
            } */
            return models;

        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public List<Commande> findAllCommandesPretePourLivraison() throws ServiceException {

        try {
            List<Commande> models = wrapper.fromEntities(repository.findAllCommandesEnCours());

            return models;

        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public List<Commande> findCommandeByUserID(int userID) throws ServiceException {

        try {
            List<Commande> models = wrapper.fromEntities(repository.findAllCommandesByUserID(userID));

            return models;

        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public List<Commande> findAllCommandesByLivreurID(int livreurID) throws ServiceException {

        try {
            List<Commande> models = wrapper.fromEntities(repository.findAllCommandesByLivreurID(livreurID));

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

    public List <Commande> findAllCommandesByUserID (int userID) throws RepositoryException, ServiceException {
        List <Commande> commandes = wrapper.fromEntities(repository.findAllCommandesByUserID(userID));
        return commandes;

    }

    public Commande findLastCommandeByUserID(int id) throws RepositoryException, ServiceException {

           Commande commande =wrapper.fromEntity(repository.findLastCommandeByUserID(id));
           User user = userWrapper.fromEntity(userRepository.findById(commande.getUser().getId()));
           commande.setUser(user);
           return commande;

    }

    public Commande calculDateFinEmporter(Commande commande) throws RepositoryException {

        LocalDateTime dateCommande = commande.getStartDatePrep();

          if (verifSiCommandeEnCours() && !verifSiCuisto()){
            commande.setEndDatePrep(dateCommande.plusMinutes(30));
        }
        else if (!verifSiCommandeEnCours() && verifSiCuisto()) {
            commande.setEndDatePrep(dateCommande.plusMinutes(10));
        }
        else {
            commande.setEndDatePrep(dateCommande.plusMinutes(20));
        }
        return commande;
    }


    public Commande calculDateEstimationLivraison(Commande commande) throws RepositoryException {

        if (verifSiLivreurDispo()) {

           commande.setEstimationEndDateLivraison(commande.getEndDatePrep().plusMinutes(10));
        } else {

            commande.setEstimationEndDateLivraison(commande.getEndDatePrep().plusMinutes(20));
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

    private boolean verifSiCuisto() throws RepositoryException {


        List<CuistoEntity> cuistoEntity  = cuistoRepository.findAllCuisto();

       for (CuistoEntity listCuistoEntity : cuistoEntity) {

           if (listCuistoEntity.getPresence().equalsIgnoreCase("present")) {
               return true;
           }
       }
        return false;
    }

    public boolean verifSiLivreurDispo() throws RepositoryException {

        List<LivreurEntity> livreurEntityList = livreurRepository.findAllLivreursAvailable();

        for (LivreurEntity livreur : livreurEntityList) {

            if (livreur.getId() > 0) {
                return true;
            }
        }
        return false;
    }

    public void updateEtatCommande(User userConnected, Commande commande) throws ServiceException, RepositoryException {

        if (userConnected.getRole().equalsIgnoreCase("admin")) {
            CommandeEntity commandeEntity = wrapper.toEntity(commande);

            if (commande.getTypeLivraison() == CommandeTypeLivraison.LIVRAISON) {

                switch (commande.getEtatCommande()) {

                    case EN_COURS_DE_TRAITEMENT:
                        repository.updateEtatCommande(commandeEntity, EN_COURS_DE_PREPARATION);
                        break;
                    case EN_COURS_DE_PREPARATION:
                        repository.updateEtatCommande(commandeEntity, EN_COURS_DE_LIVRAISON);
                        repository.updateHeureLivraison(commandeEntity.getId());
                        livreurRepository.setPresenceLivraison(commande.getId());
                        break;
                    case EN_COURS_DE_LIVRAISON:
                        repository.updateEtatCommande(commandeEntity, LIVRE);
                        livreurRepository.initCommandeLivreur(commandeEntity.getId());
                        break;

                }
            }

            if (commande.getTypeLivraison() == CommandeTypeLivraison.EMPORTER) {

                switch (commande.getEtatCommande()) {
                    case EN_COURS_DE_TRAITEMENT:
                        repository.updateEtatCommande(commandeEntity, EN_COURS_DE_PREPARATION);
                        break;
                    case EN_COURS_DE_PREPARATION:
                        repository.updateEtatCommande(commandeEntity, PRETE);
                        break;
                }
            }
        }
    }

    public String formatDate(LocalDateTime localDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formatDateTime = localDate.format(formatter);

        return formatDateTime;
    }

    public List<Commande> findAllCommandesTermines() throws ServiceException {

        try {
            List<Commande> models = wrapper.fromEntities(repository.findAllCommandesTermines());
           /* for(Commande commande : models) {
                if(commande.getUser() != null) {
                    commande.setUser(userService.findById(commande.getUser().getId()));
                }
            } */
            return models;

        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}
