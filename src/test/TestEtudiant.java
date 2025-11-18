package test;

import model.*;

public class TestEtudiant {

    public static void main(String[] args) {

        try {

            Etudiant e = new Etudiant("Reyane Ahmed");
            e.addNotation(new Notation(4, 12, "Maths"));
            e.addNotation(new Notation(5, 9, "Physique"));
            e.addNotation(new Notation(1, 16, "Histoire"));

            e.calculerMoyenne();
            e.save(); // insertion BD


            Etudiant e2 = Etudiant.load(e.getId());
            e2.afficher();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}