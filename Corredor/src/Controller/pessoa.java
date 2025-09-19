package Controller;

import java.util.concurrent.Semaphore;

public class pessoa extends Thread{
	private static Semaphore porta = new Semaphore (1);
	private int i;
	
	public pessoa (int i) {
		this.i = i;
	}
	
	public void run () {
		caminhar ();
		porta ();
	}
	
	public void caminhar () {
		for (double dpercorrida = 0; dpercorrida < 200;) {
			double passo = (double) (Math.random () * (6 - 4 + 0.000001) + 4);
			dpercorrida += passo;
			try {
				Thread.sleep (1000);
			} catch (InterruptedException e) { e.printStackTrace();}
			System.out.printf ("pessoa %d percorreu mais %f metros | total: %f %n", i, passo, dpercorrida);
		}
		System.out.printf ("Pessoa %d chegou na porta! %n" , i);
	}
	
	public void porta () {
		try {
			porta.acquire();
			System.out.printf ("pessoa %d atravessando a porta... %n" , i);
			double tempo = (double)(Math.random () * (2 - 1 + 0.000001) + 1);
			Thread.sleep ((long) (tempo * 1000));
		} catch (InterruptedException e) { e.printStackTrace();}
		finally {
			System.out.printf ("pessoa %d atravessou a porta!" , i);
			porta.release();
		}
	}
}
