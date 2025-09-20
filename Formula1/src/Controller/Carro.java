package Controller;

import java.util.concurrent.Semaphore;
public class Carro extends Thread{
	private int ncarro;
	private Semaphore[] equipes;
	private int idequipe;
	private static Semaphore pista = new Semaphore (5);
	private int mnTempo;

	public Carro (int ncarro, Semaphore [] equipes, int idequipe) {
		this.ncarro = ncarro;
		this.equipes = equipes;
		this.idequipe = idequipe;
	}
	
	public void run () {
		pista();
	}

	private void pista() {
		
		try {
			pista.acquire();
			equipes[idequipe].acquire();
			
			for (int i = 1; i <= 3; i++) {
				int tempo = (int)(Math.random() * (1000 - 100 + 1) + 500);
				Thread.sleep(tempo);
				if (i == 1) {mnTempo = tempo;} else
					if (tempo < mnTempo) {
						mnTempo = tempo;
					}
				System.out.printf ("CARRO: %d | EQUIPE: %d | %d ° volta | Tempo: %d ms %n", ncarro, idequipe, i, tempo);
			}
		} catch (InterruptedException e) { e.printStackTrace();}
		finally {pista.release(); equipes[idequipe].release();}
	}
	
	public int getmnTempo() { return mnTempo; }
	public int getncarro() { return ncarro; }
	public int getidequipe() { return idequipe; }
}
