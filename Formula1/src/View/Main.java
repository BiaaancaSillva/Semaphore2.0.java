/*Você foi contratado para automatizar um treino de Fórmula 1. As regras estabelecidas pela  direção da provas 
 * são simples:  No máximo 5 carros das 7 escuderias[equipe] (Cada escuderia tem 2 carros diferentes,  portanto, 
 * 14 carros no total) presentes podem entrar na pista simultaneamente, mas apenas  um carro de cada equipe. O segundo carro 
 * deve ficar à espera, caso um companheiro de  equipe já esteja na pista. Cada piloto deve dar 3 voltas na pista. O tempo 
 * de cada volta deverá  ser exibido e a volta mais rápida de cada piloto deve ser armazenada para, ao final, exibir o  
 * grid de largada, ordenado do menor tempo para o maior.” */


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
