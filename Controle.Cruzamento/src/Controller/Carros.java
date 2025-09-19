
package Controller;

import java.util.concurrent.Semaphore;
public class Carros extends Thread {
	private int id,i;
	private String sentido;
	private static Semaphore sm = new Semaphore (1);
	
	public Carros (int i) {
		this.i = i;
	}
	
	public void run ( ) {
		id = (int) threadId();
		switch (i) {
		case (0): sentido = " cima "; break;
		case (1): sentido = " direita "; break;
		case (2): sentido = " baixo "; break;
		case (3): sentido = " esquerda "; break;
		}
		cruzamento () ;
	}
	
	public void cruzamento () {
		
		try {
			sm.acquire();
			System.out.println ("ID: " + id + " está se movimentando para " + sentido);
			Thread.sleep (2000);
			System.out.println ("ID: " + id + "movimentou-se para " + sentido);
		} catch (InterruptedException e ) { e.printStackTrace();}
		finally {sm.release();}
		
	}
}
