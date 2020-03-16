package it.polito.tdp.indovinanumero.model;

import java.security.InvalidParameterException;
import java.util.HashSet; //controlla se in un contenitore Set figura già un 
import java.util.Set; //determinato elemento del Set

public class Model {

	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;
	
	private Set<Integer> tentativi;
	
	public Model() {
		this.inGioco =false;
		this.tentativiFatti = 0;
	}
	
	public void nuovaPartita() {
		
	  	//gestione dell'inizio di una nuova partita - Logica del gioco
    	this.segreto = (int)(Math.random() * NMAX) + 1;
    	this.tentativiFatti = 0;
    	this.inGioco = true; 
    	this.tentativi = new HashSet<Integer>(); //HashSet:controlla se in un contenitore Set 
    											//figura già un determinato elemento)
   	}
	
	public int tentativo(int tentativo) {
		//controllo se la partita è in corso
		if(!inGioco) {
			throw new IllegalStateException("La partita è già terminata\n");
		}
		
		//controllo l'input
		if(!tentativoValido(tentativo)) {
					//throw new InvalidParameterException("Hai ripetuto uno dei numeri tra 1 e "+ NMAX + "\n");
			throw new InvalidParameterException("Numero o ripetuto o non compreso tra 1 e " + NMAX +"\n");
		}

		//il tentativo è valido -> possiamo "provarlo"
    	this.tentativiFatti ++;
    	
    	this.tentativi.add(tentativo);
    	
    	if(this.tentativiFatti == TMAX) {
    		this.inGioco = false;
    	}
    	
    	if(tentativo == this.segreto) {
    		this.inGioco = false;
    		return 0;
    	}
    	
    	if(tentativo < this.segreto) {
    		return -1;
    	}else {
    		return 1;
    	}
 	}
	
	private boolean tentativoValido(int tentativo) {
		if(tentativo < 1 || tentativo > NMAX) {
			return false;
		}
		else {
			if(this.tentativi.contains(tentativo)) { //vedi HashSet di sopra
				return false;// mi dice che ho ripetuto lo stesso numero
		}
			return true;
		}
	}

	public int getSegreto() {
		return segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public int getTMAX() {
		return TMAX;
	}
	
}
