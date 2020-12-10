package fr.greta.java.commandeItems.facade;

import fr.greta.java.burger.persistence.BurgerEntity;
import fr.greta.java.burger.persistence.BurgerRepository;
import fr.greta.java.commande.domain.Commande;
import fr.greta.java.commande.facade.CommandeDTO;
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

private BurgerRepository repositoryBurger = new BurgerRepository();
private CommandeRepository repositoryCommande = new CommandeRepository();


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

        BurgerEntity burgerEntity = repositoryBurger.findById(commandeItemsEntity.getBurgerId());
        DTO.setLabel(burgerEntity.getLabel());
        DTO.setPrice(burgerEntity.getPrice());

        CommandeEntity commandeEntity = repositoryCommande.findById(commandeItemsEntity.getCommandeId());
        DTO.setStartDateFormat(formatDate(commandeEntity.getStartDatePrep().toLocalDateTime()));
        DTO.setEndDateFormat(formatDate(commandeEntity.getEndDatePrep().toLocalDateTime()));
        DTO.setQuantity(commandeItemsEntity.getQuantity());
        DTO.setTotalPrixLigne(DTO.getPrice()*DTO.getQuantity());

        return DTO;
    }

    public CommandeItemsDTO toDTOByModel(CommandeItems model) throws RepositoryException {

        CommandeItemsDTO dto = new CommandeItemsDTO();

        dto.setLabel(model.getBurger().getLabel());
        dto.setPrice(model.getBurger().getPrice());
        dto.setStartDateFormat(formatDate(model.getCommande().getStartDatePrep()));
        dto.setEndDateFormat(formatDate(model.getCommande().getEndDatePrep()));
        dto.setQuantity(model.getQuantity());
        dto.setTotalPrixLigne(model.getBurger().getPrice()* model.getQuantity());

        return dto;
    }

    public String formatDate(LocalDateTime localDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formatDateTime = localDate.format(formatter);

        return formatDateTime;
    }


}
