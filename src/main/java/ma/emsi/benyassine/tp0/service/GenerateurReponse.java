package ma.emsi.benyassine.tp0.service;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GenerateurReponse {
    /** Traitement personnel : reformule la question en réponse miroir contextualisée. */
    public String generer(String question, String role) {
        String texte = question == null ? "" : question.trim().replaceAll("\\s+", " ");
        if (texte.isEmpty()) return "";
        String premiere = texte.substring(0, 1).toUpperCase() + texte.substring(1);
        if (!premiere.matches(".*[.!?]$")) premiere += ".";
        return "En tant que " + role + ", je retiens votre demande : « " + premiere
                + " » Voici une piste : précisez votre objectif et les contraintes importantes pour obtenir une réponse plus ciblée.";
    }
}
