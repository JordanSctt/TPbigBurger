package fr.greta.java.commande.facade;

import fr.greta.java.commande.domain.Commande;
import fr.greta.java.commande.persistence.CommandeEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CommandeDTOWrapper {




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



}
