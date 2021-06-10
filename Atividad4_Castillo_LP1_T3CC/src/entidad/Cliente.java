package entidad;

import java.sql.Date;



public class Cliente {
	private String idCli;
	private String nom;
	private String ape;
	private String dni;
	private Date fecNac;
	private String idTipCli;
	private String nomTip;
	public String getIdCli() {
		return idCli;
	}
	public void setIdCli(String idCli) {
		this.idCli = idCli;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getApe() {
		return ape;
	}
	public void setApe(String ape) {
		this.ape = ape;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public Date getFecNac() {
		return fecNac;
	}
	public void setFecNac(Date fecNac) {
		this.fecNac = fecNac;
	}
	public String getIdTipCli() {
		return idTipCli;
	}
	public void setIdTipCli(String idTipCli) {
		this.idTipCli = idTipCli;
	}
	public String getNomTip() {
		return nomTip;
	}
	public void setNomTip(String nomTip) {
		this.nomTip = nomTip;
	}
	
	
}
