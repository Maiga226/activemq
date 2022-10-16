package com.ibam.soap.domain;


import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@XmlRootElement(name = "compte")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompteResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    @XmlElement(name = "nom-client")
    private String nomClient   ;

    @XmlElement(name = "prenom-client")
    private String prenomClient   ;

    @XmlElement(name = "compte-client")
    private String numerCompte;

    @XmlElement(name = "solde-client")
    private BigDecimal solde;

    @XmlElement(name = "date-solde")
    private String dateSolde;

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

    public String getNumerCompte() {
        return numerCompte;
    }

    public void setNumerCompte(String numerCompte) {
        this.numerCompte = numerCompte;
    }

    public BigDecimal getSolde() {
        return solde;
    }

    public void setSolde(BigDecimal solde) {
        this.solde = solde;
    }

    public String getDateSolde() {
        return dateSolde;
    }

    public void setDateSolde(String dateSolde) {
        this.dateSolde = dateSolde;
    }

    @Override
    public String toString() {
        return "CompteResponse{" +
            "nomClient='" + nomClient + '\'' +
            ", prenomClient='" + prenomClient + '\'' +
            ", numerCompte='" + numerCompte + '\'' +
            ", solde=" + solde +
            ", dateSolde=" + dateSolde +
            '}';
    }
}
