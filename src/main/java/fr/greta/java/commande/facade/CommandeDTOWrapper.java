package fr.greta.java.commande.facade;

import fr.greta.java.burger.persistence.BurgerEntity;
import fr.greta.java.commande.domain.Commande;
import fr.greta.java.commande.persistence.CommandeEntity;
import fr.greta.java.commande.persistence.CommandeRepository;
import fr.greta.java.commandeItems.domain.CommandeItems;
import fr.greta.java.commandeItems.facade.CommandeItemsDTO;
import fr.greta.java.commandeItems.persistence.CommandeItemsEntity;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.user.persistence.UserEntity;
import fr.greta.java.user.persistence.UserRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CommandeDTOWrapper {

    private UserRepository userRepository = new UserRepository();

    public List<CommandeDTO> toDTOS(List<Commande> models) {
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


    public CommandeDTO toDTO(Commande model) {
        CommandeDTO dto = new CommandeDTO();
        dto.setId(model.getId());
        dto.setStartDatePrep(model.getStartDatePrep());
        dto.setEndDatePrep(model.getEndDatePrep());
        dto.setName(model.getUser().getName());
        dto.setPhone(model.getUser().getPhone());
        dto.setEtatCommande(model.getEtatCommande().name());

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

        return dto;
    }

    public String formatDate(LocalDateTime localDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formatDateTime = localDate.format(formatter);

        return formatDateTime;
    }

}
