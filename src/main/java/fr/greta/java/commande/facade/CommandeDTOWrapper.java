package fr.greta.java.commande.facade;

import fr.greta.java.commande.domain.Commande;
import fr.greta.java.commande.persistence.CommandeEntity;
import fr.greta.java.commandeItems.facade.CommandeItemsDTO;
import fr.greta.java.commandeItems.facade.CommandeItemsDTOWrapper;
import fr.greta.java.commandeItems.persistence.CommandeItemsEntity;
import fr.greta.java.commandeItems.persistence.CommandeItemsRepository;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.user.persistence.UserEntity;
import fr.greta.java.user.persistence.UserRepository;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CommandeDTOWrapper {



    public List<CommandeDTO> toDTOS(List<Commande> models) throws RepositoryException {
        List<CommandeDTO> dtos = new ArrayList<>();
        for(Commande model : models) {
            dtos.add(toDTO(model));
        }
        return dtos;
    }

    public List<CommandeDTO> toListDTO (List <CommandeEntity> commandeEntityList) throws RepositoryException {

        List <CommandeDTO> listDTO = new ArrayList<>();
        for (CommandeEntity commandeEntity : commandeEntityList) {
            listDTO.add(toDTOByEntity(commandeEntity));
        }
        return listDTO;
    }


    public CommandeDTO toDTO(Commande model) throws RepositoryException {
        CommandeDTO dto = new CommandeDTO();
        dto.setId(model.getId());
        dto.setStartDatePrep(model.getStartDatePrep());
        dto.setEndDatePrep(model.getEndDatePrep());
        dto.setUserName(model.getUser().getName());
        dto.setUserPassword(model.getUser().getPassword());
        dto.setUserPhone(model.getUser().getPhone());
        dto.setUserAdresse(model.getUser().getAdresse());

        dto.setEtatCommande(model.getEtatCommande().name());
        if (model.getStartDateLivraison() != null && model.getEndDateLivraison() != null) {
            dto.setStartDateLivraison(model.getStartDateLivraison());
            dto.setEndDateLivraison(model.getEndDateLivraison());
        }
        List <CommandeItemsDTO> commandeItemsDTOList = new ArrayList<>();
        dto.setCommandeItemsDTOList(commandeItemsDTOList);
        dto.calculPrixTotal(dto.getCommandeItemsDTOList());
        dto.setTypeLivraison(model.getTypeLivraison().name());
        if (model.getEstimationEndDateLivraison() != null) {
            dto.setEstimationLivraison(model.getEstimationEndDateLivraison());
        }
        if (model.getLivreur() != null) {
            dto.setLivreurName(model.getLivreur().getName());
        }

        return dto;
    }

    public CommandeDTO toDTOByEntity(CommandeEntity commandeEntity) throws RepositoryException {
        CommandeDTO dto = new CommandeDTO();

        dto.setId(commandeEntity.getId());
        dto.setStartDatePrep(commandeEntity.getStartDatePrep().toLocalDateTime());
        dto.setEndDatePrep(commandeEntity.getEndDatePrep().toLocalDateTime());
        dto.setEtatCommande(commandeEntity.getEtatCommande());
        dto.setTypeLivraison(commandeEntity.getTypeLivraison());
        dto.setEtatCommande(commandeEntity.getEtatCommande());
        if (commandeEntity.getStartDateLivraison() != null && commandeEntity.getEndDateLivraison() != null) {
            dto.setStartDateLivraison(commandeEntity.getStartDateLivraison().toLocalDateTime());
            dto.setEndDateLivraison(commandeEntity.getEndDateLivraison().toLocalDateTime());
        }
        dto.setTypeLivraison(commandeEntity.getTypeLivraison());
        if (commandeEntity.getEstimationLivraison() != null) {
            dto.setEstimationLivraison(commandeEntity.getEstimationLivraison().toLocalDateTime());
        }

        return dto;
    }


}
