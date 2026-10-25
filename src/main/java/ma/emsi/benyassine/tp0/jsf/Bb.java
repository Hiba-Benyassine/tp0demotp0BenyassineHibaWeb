package ma.emsi.benyassine.tp0.jsf;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import ma.emsi.benyassine.tp0.service.GenerateurReponse;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Named("bb")
@ViewScoped
public class Bb implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private GenerateurReponse generateurReponse;

    private String question;
    private String reponse = "";
    private String role;
    private boolean roleSystemeAffiche;
    private StringBuilder historique = new StringBuilder();
    private final List<String> roles = Arrays.asList("Assistant", "Traducteur français-anglais", "Guide touristique");

    public void envoyer() {
        FacesContext contexte = FacesContext.getCurrentInstance();
        if (question == null || question.trim().isEmpty()) {
            contexte.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Question vide", "Veuillez saisir une question avant l’envoi."));
            return;
        }
        if (role == null || role.trim().isEmpty()) {
            contexte.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Rôle manquant", "Veuillez renseigner le rôle de l’assistant."));
            return;
        }
        reponse = generateurReponse.generer(question, role);
        historique.append("Vous : ").append(question.trim()).append(System.lineSeparator())
                .append("Réponse : ").append(reponse).append(System.lineSeparator()).append(System.lineSeparator());
        question = "";
        this.roleSystemeAffiche = true;
    }

    public void nouveauChat() {
        historique.setLength(0);
        question = "";
        reponse = "";
        role = null;
        roleSystemeAffiche = false;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nouveau chat", "La conversation a été réinitialisée."));
    }

    public void effacerHistorique() {
        historique.setLength(0);
    }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }
    public String getReponse() { return reponse; }
    public void setReponse(String reponse) { this.reponse = reponse; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public boolean isRoleSystemeAffiche() { return roleSystemeAffiche; }
    public String getHistorique() { return historique.toString(); }
    public void setHistorique(String historique) {
        this.historique = new StringBuilder(historique == null ? "" : historique);
    }
    public List<String> getRoles() { return roles; }
}
