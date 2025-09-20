package View;

import java.util.concurrent.Semaphore;
import Controller.Carro;
public class Main {
	public static void main (String args [] ){
		
		Semaphore[] equipes = new Semaphore [7];
		Carro carros [] = new Carro [14]; 
		
		 for (int i = 0; i < 7; i++) {
	            equipes[i] = new Semaphore(1);
	        }

	        for (int i = 0; i < 14; i++) {
	            int equipeId = i / 2; 
	            Carro c = new Carro(i, equipes, equipeId);
	            carros[i] = c;
	            c.start();
	        }	        
	        
	        for (int i = 0; i < 14; i++) {        	
	        	try {
					carros[i].join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	        }
	        
	        for (int i = 0; i < 14; i++) {
	        	for (int j = i; j < 14; j++) {
	        		if (carros[j].getmnTempo() < (carros[i].getmnTempo())){
	        			Carro temp = carros[j];
	        			carros[j] = carros[i];
	        			carros [i] = temp;
	        		}
	        	}
	        }
	        System.out.println ("RANKING -------------");
	        for (int i = 0; i < 14; i++) {
	        	System.out.printf ("%d ° LUGAR | CARRO: %d | EQUIPE: %d | TEMPO: %d ms %n", i + 1, carros[i].getncarro(), carros[i].getidequipe(),carros[i].getmnTempo()); 
	        }
	}
}
