package model;

public class Notation {
    private int id;
    private int coef;
    private int note;
    private String matiere;

    public Notation(int coef, int note, String matiere) {
        this.coef = coef;
        this.note = note;
        this.matiere = matiere;
    }

    public Notation(int id, int coef, int note, String matiere) {
        this.id = id;
        this.coef = coef;
        this.note = note;
        this.matiere = matiere;
    }

    public int getCoef() { return coef; }
    public int getNote() { return note; }
    public String getMatiere() { return matiere; }
}
