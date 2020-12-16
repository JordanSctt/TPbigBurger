package fr.greta.java.commandeItems.facade;

import fr.greta.java.burger.persistence.ProduitEntity;
import fr.greta.java.burger.persistence.ProduitRepository;
import fr.greta.java.commande.persistence.CommandeEntity;
import fr.greta.java.commande.persistence.CommandeRepository;
import fr.greta.java.commandeItems.domain.CommandeItems;
import fr.greta.java.commandeItems.persistence.CommandeItemsEntity;
import fr.greta.java.generic.exception.RepositoryException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CommandeItemsDTOWrapper {



    public List<CommandeItemsDTO> toListDTO (List <CommandeItemsEntity> commandeItemsEntityList) throws RepositoryException {

        List <CommandeItemsDTO> listDTO = new ArrayList<>();
        for (CommandeItemsEntity commandeItemsEntity : commandeItemsEntityList) {
            listDTO.add(toDTO(commandeItemsEntity));
        }
        return listDTO;
    }

    public List<CommandeItemsDTO> toListDTOByModel (List <CommandeItems> models) throws RepositoryException {

        List <CommandeItemsDTO> listDTO = new ArrayList<>();
        for (CommandeItems model : models) {
            listDTO.add(toDTOByModel(model));
        }
        return listDTO;
    }

    public CommandeItemsDTO toDTO(CommandeItemsEntity commandeItemsEntity) throws RepositoryException {

        CommandeItemsDTO DTO = new CommandeItemsDTO();
        DTO.setQuantity(commandeItemsEntity.getQuantity());

        return DTO;
    }

    public CommandeItemsDTO toDTOByModel(CommandeItems model) throws RepositoryException {

        CommandeItemsDTO dto = new CommandeItemsDTO();

        dto.setLabel(model.getProduit().getLabel());
        dto.setPrice(model.getProduit().getPrice());
        dto.setType(model.getProduit().getProduitType().name());
        dto.setStartDateFormat(String.valueOf(model.getCommande().getStartDatePrep()));
        dto.setEndDateFormat(String.valueOf(model.getCommande().getEndDatePrep()));
        dto.setQuantity(model.getQuantity());
        dto.setTotalPrixLigne(model.getProduit().getPrice()* model.getQuantity());

        return dto;
    }



}
