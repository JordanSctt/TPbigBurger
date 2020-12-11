package fr.greta.java.commande.facade;

import fr.greta.java.commande.domain.Commande;
import fr.greta.java.commande.persistence.CommandeEntity;
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

    private UserRepository userRepository = new UserRepository();
    private CommandeItemsRepository commandeItemsRepository = new CommandeItemsRepository();
    private CommandeItemsDTOWrapper commandeItemsDTOWrapper = new CommandeItemsDTOWrapper();

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
        dto.setName(model.getUser().getName());
        dto.setPhone(model.getUser().getPhone());
        dto.setEtatCommande(model.getEtatCommande().name());
        dto.setHeureRecup(dto.getEndDatePrep());
        List <CommandeItemsEntity> commandesItemsEntities = commandeItemsRepository.findAllCommandeItemsByCommandeID(model.getId());
        dto.setCommandeItemsDTOList(commandeItemsDTOWrapper.toListDTO(commandesItemsEntities));
        dto.calculPrixTotal(dto.getCommandeItemsDTOList());

        return dto;
    }

    public CommandeDTO toDTOByEntity(CommandeEntity commandeEntity) throws RepositoryException {

        CommandeDTO dto = new CommandeDTO();

        dto.setId(commandeEntity.getId());
        UserEntity userEntity = userRepository.findById(commandeEntity.getUserID());
        dto.setName(userEntity.getName());
        dto.setPhone(userEntity.getPhone());
        dto.setStartDatePrep(commandeEntity.getStartDatePrep().toLocalDateTime());
        dto.setEndDatePrep(commandeEntity.getEndDatePrep().toLocalDateTime());
        dto.setEtatCommande(commandeEntity.getEtatCommande());
        dto.setHeureRecup(dto.getEndDatePrep());

        List <CommandeItemsEntity> commandesItemsEntities = commandeItemsRepository.findAllCommandeItemsByCommandeID(commandeEntity.getId());
        dto.setCommandeItemsDTOList(commandeItemsDTOWrapper.toListDTO(commandesItemsEntities));
        dto.calculPrixTotal(dto.getCommandeItemsDTOList());

        return dto;
    }

    public String formatDate(LocalDateTime localDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formatDateTime = localDate.format(formatter);

        return formatDateTime;
    }

}
