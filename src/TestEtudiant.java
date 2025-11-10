package test;

import model.Etudiant;
import model.Notation;

public class TestEtudiant {
    public static void main(String[] args) {
        Etudiant e1 = new Etudiant("Reyane Ahmed");

        e1.ajouterNotation(new Notation(4, 12, "Maths"));
        e1.ajouterNotation(new Notation(5, 9, "Physique-Chimie"));
        e1.ajouterNotation(new Notation(1, 16, "Histoire-Géo"));

        e1.calculerMoyenne();
        e1.genererAvis();
        e1.afficher();
    }
}
