package com.ibam.soap.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "compte")
public class Compte extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nom_client")
    private String nomClient;
    @Column(name = "prenom_client")
    private String prenomClient;
    @Column(name = "numero_compte")
    private String numeroCompte;
    @Column(name = "solde")
    private BigDecimal soldeClient;
    @Column(name = "date_solde")
    private LocalDate dateSolde;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public BigDecimal getSoldeClient() {
        return soldeClient;
    }

    public void setSoldeClient(BigDecimal soldeClient) {
        this.soldeClient = soldeClient;
    }

    public LocalDate getDateSolde() {
        return dateSolde;
    }

    public void setDateSolde(LocalDate dateSolde) {
        this.dateSolde = dateSolde;
    }

    @Override
    public String toString() {
        return "Compte{" +
            "id=" + id +
            ", nomClient='" + nomClient + '\'' +
            ", prenomClient='" + prenomClient + '\'' +
            ", numeroCompte='" + numeroCompte + '\'' +
            ", soldeClient=" + soldeClient +
            ", dateSolde=" + dateSolde +
            '}';
    }
}
