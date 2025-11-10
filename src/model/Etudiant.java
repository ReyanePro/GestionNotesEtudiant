package model;

import java.util.ArrayList;

public class Etudiant {
    private String nom;
    private float moyenne;
    private String avis;
    private ArrayList<Notation> notations;

    public Etudiant(String nom) {
        this.nom = nom;
        this.moyenne = 0;
        this.avis = "";
        this.notations = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public float getMoyenne() {
        return moyenne;
    }

    public String getAvis() {
        return avis;
    }

    public ArrayList<Notation> getNotations() {
        return notations;
    }

    public void ajouterNotation(Notation n) {
        notations.add(n);
    }

    public float calculerMoyenne() {
        int sumCoef = 0;
        int sumCoefxNote = 0;

        for (Notation n : notations) {
            sumCoef += n.getCoef();
            sumCoefxNote += n.getCoef() * n.getNote();
        }

        if (sumCoef == 0) return 0;
        this.moyenne = (float) sumCoefxNote / sumCoef;
        return moyenne;
    }


    public void genererAvis() {
        if (moyenne >= 10)
            this.avis = "Admis";
        else if (moyenne >= 8)
            this.avis = "Rattrapage";
        else
            this.avis = "Ajourné";
    }


    public void afficher() {
        System.out.println("=== Bulletin de " + nom + " ===");
        for (Notation n : notations) {
            n.afficher();
        }
        System.out.println("----------------------------");
        System.out.println("Moyenne : " + String.format("%.2f", moyenne));
        System.out.println("Avis de passage : " + avis);
        System.out.println("============================\n");
    }
}
