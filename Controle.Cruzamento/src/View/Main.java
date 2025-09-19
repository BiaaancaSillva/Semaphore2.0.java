//Fazer uma aplicação, console, que gerencie um cruzamento entre duas ruas:
//Para tal, usar uma variável sentido, que será alterado pela Thread que controla cada carro
//com a movimentação do carro. Quando a Thread tiver a possibilidade de ser executada, ela
//deve imprimir em console o sentido que o carro está passando. Só pode passar um carro por
//vez no cruzamento. Usar threadId() ou getId() para identificar os carros.

package View;

import Controller.Carros;
public class Main {
	public static void main (String args[ ] ) {
		
		for (int i = 0; i < 4; i++) {
			Carros a = new Carros (i);
			a.start();
		}
	}
}
