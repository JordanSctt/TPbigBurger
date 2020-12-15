package fr.greta.java.livreur.facade;

import fr.greta.java.commande.domain.Commande;
import fr.greta.java.commande.facade.CommandeDTO;
import fr.greta.java.livreur.domain.LivreurPresence;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LivreurDTO {

    private int id;
    private String name;
    private String presence;
    private List<CommandeDTO> commandeDTOList;
    private CommandeDTO commandeEnCours;


    //-------------------------------------------


   

    public LivreurDTO() {
    }

    //-------------------------------------------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPresence() {
        return presence;
    }

    public void setPresence(String presence) {
        this.presence = presence;
    }

    public List<CommandeDTO> getCommandeDTOList() {
        if (this.commandeDTOList == null) {
            this.commandeDTOList = new ArrayList<>();
        }
        return commandeDTOList;
    }

    public void setCommandeDTOList(List<CommandeDTO> commandeDTOList) {
        this.commandeDTOList = commandeDTOList;
    }

    public CommandeDTO getCommandeEnCours() {
        return commandeEnCours;
    }

    public void setCommandeEnCours(CommandeDTO commandeEnCours) {
        this.commandeEnCours = commandeEnCours;
    }
}
