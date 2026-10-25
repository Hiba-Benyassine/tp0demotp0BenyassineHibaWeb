package ma.emsi.benyassine.tp0.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import ma.emsi.benyassine.tp0.service.GenerateurReponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named("bb")
@ViewScoped
public class Bb implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private GenerateurReponse generateurReponse;

    private String question;
    private String role;
    private boolean roleSystemeAffiche;
    private List<Echange> historique = new ArrayList<>();
    private final List<String> roles = Arrays.asList("Professeur", "Conseiller", "Assistant rédactionnel", "Développeur");

    @PostConstruct
    public void initialiser() {
        // CDI injecte le générateur avant l’utilisation du bean.
    }

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
        String reponse = generateurReponse.generer(question, role);
        historique.add(new Echange(question.trim(), role, reponse));
        question = "";
        this.roleSystemeAffiche = true;
    }

    public void nouveauChat() {
        historique.clear();
        question = "";
        role = null;
        roleSystemeAffiche = false;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nouveau chat", "La conversation a été réinitialisée."));
    }

    public void effacerHistorique() {
        historique.clear();
    }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public boolean isRoleSystemeAffiche() { return roleSystemeAffiche; }
    public List<Echange> getHistorique() { return historique; }
    public List<String> getRoles() { return roles; }

    public static class Echange implements Serializable {
        private static final long serialVersionUID = 1L;
        private final String question;
        private final String role;
        private final String reponse;

        public Echange(String question, String role, String reponse) {
            this.question = question;
            this.role = role;
            this.reponse = reponse;
        }
        public String getQuestion() { return question; }
        public String getRole() { return role; }
        public String getReponse() { return reponse; }
    }
}
