package model;

import java.sql.*;
import java.util.ArrayList;

public class Etudiant {

    private int id;
    private String nom;
    private float moyenne;
    private String avis;
    private ArrayList<Notation> notations;

    public Etudiant(String nom) {
        this.nom = nom;
        this.notations = new ArrayList<>();
    }

    public Etudiant(int id, String nom, float moyenne, String avis) {
        this.id = id;
        this.nom = nom;
        this.moyenne = moyenne;
        this.avis = avis;
        this.notations = new ArrayList<>();
    }

    public void save() throws SQLException {
        Connection conn = DBManager.getConnection();

        String sql = "INSERT INTO etudiants(nom, moyenne, avis) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, nom);
        ps.setFloat(2, moyenne);
        ps.setString(3, avis);

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) this.id = rs.getInt(1);

        // ajout des notes
        for (Notation n : notations) {
            addNotationToDB(n);
        }
    }

    private void addNotationToDB(Notation n) throws SQLException {
        Connection conn = DBManager.getConnection();
        String sql = "INSERT INTO notations(id_etudiant, coef, note, matiere) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.setInt(2, n.getCoef());
        ps.setInt(3, n.getNote());
        ps.setString(4, n.getMatiere());
        ps.executeUpdate();
    }

    public void addNotation(Notation n) {
        notations.add(n);
    }

    public float calculerMoyenne() {
        float sc = 0;
        float scn = 0;

        for (Notation n : notations) {
            sc += n.getCoef();
            scn += n.getCoef() * n.getNote();
        }
        this.moyenne = scn / sc;

        this.avis = (moyenne >= 10) ? "Admis" : (moyenne >= 8) ? "Rattrapage" : "Ajourné";

        return moyenne;
    }

    public static Etudiant load(int idEtudiant) throws SQLException {

        Connection conn = DBManager.getConnection();


        String sql1 = "SELECT * FROM etudiants WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql1);
        ps.setInt(1, idEtudiant);
        ResultSet rs = ps.executeQuery();

        if (!rs.next()) return null;

        Etudiant e = new Etudiant(
                rs.getInt("id"),
                rs.getString("nom"),
                rs.getFloat("moyenne"),
                rs.getString("avis")
        );


        String sql2 = "SELECT * FROM notations WHERE id_etudiant = ?";
        PreparedStatement ps2 = conn.prepareStatement(sql2);
        ps2.setInt(1, idEtudiant);
        ResultSet rs2 = ps2.executeQuery();

        while (rs2.next()) {
            e.notations.add(new Notation(
                    rs2.getInt("id"),
                    rs2.getInt("coef"),
                    rs2.getInt("note"),
                    rs2.getString("matiere")
            ));
        }

        return e;
    }

    public int getId() {
        return id;
    }

    public void afficher() {
        System.out.println("=== Bulletin de " + nom + " ===");
        for (Notation n : notations) {
            System.out.println(n.getMatiere() + " | Coef : " + n.getCoef() + " | Note : " + n.getNote());
        }
        System.out.println("-------------------");
        System.out.println("Moyenne: " + moyenne);
        System.out.println("Avis: " + avis);
        System.out.println("===================\n");
    }
}
