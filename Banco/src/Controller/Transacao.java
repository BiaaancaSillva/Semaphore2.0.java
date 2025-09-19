package Controller;

import java.util.concurrent.Semaphore;

public class Transacao extends Thread{
	private int cod;
	private double saldo, valor;
	private boolean saque;
	private static Semaphore saq = new Semaphore (1);
	private static Semaphore dep = new Semaphore (1);
	
	public Transacao (int cod, boolean saque, double saldo, double valor) {
		this.cod = cod;
		this.saque = saque;
		this.saldo = saldo;
		this.valor = valor;
	}
	
	public void run () {
		if (saque) {
			saque(cod, saldo, valor);
		} else {
			deposito(cod, saldo, valor);
		}
	}
	
	public void saque (int cod, double saldo, double valor) {
		try {
			saq.acquire();
			if (valor > saldo) {
				System.out.printf ("CONTA: %d | SALDO INSUFICIENTE! | %n", cod);
			} else {
				saldo -= valor;
				System.out.printf ("CONTA: %d | SAQUE: %.2f | SALDO: %.2f %n", cod, valor, saldo);
			}
		} catch (InterruptedException e) { e.printStackTrace();}
		finally {saq.release();}
	}
	
	public void deposito(int cod, double saldo, double valor) {
		try {
			dep.acquire();
			saldo += valor;
			System.out.printf ("CONTA: %d | DEPÓSITO: %.2f | SALDO: %.2f %n", cod, valor, saldo);
		} catch (InterruptedException e) { e.printStackTrace();}
		finally {dep.release();}
	}
}
