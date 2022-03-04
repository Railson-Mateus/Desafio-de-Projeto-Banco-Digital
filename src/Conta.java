import java.util.Scanner;

public abstract class Conta implements IConta {

	private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;

	protected int agencia;
	protected int numero;
	protected double saldo;
	protected Cliente cliente;

	public Conta(Cliente cliente) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
	}

	@Override
	public void sacar(double valor) {
		this.saldo -= valor;
	}

	@Override
	public void depositar(double valor) {
		this.saldo += valor;
	}

	@Override
	public void transferir(double valor, Conta contaDestino) {
		this.sacar(valor);
		contaDestino.depositar(valor);

	}

	public int getAgencia() {
		return agencia;
	}

	public int getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}

	protected void imprimirInformacoes() {
		System.out.println(String.format("Titular: %s", this.getCliente().getNome()));
		System.out.println(String.format("CPF: %s", this.getCliente().getCpf()));
		System.out.println(String.format("Agencia: %d", this.agencia));
		System.out.println(String.format("Numero: %d", this.numero));
		System.out.println(String.format("Saldo: %.2f", this.saldo));
	}

	protected void menuConta() {
		Scanner scan = new Scanner(System.in);
		int opcao;
		
		System.out.println("|############################|");
		System.out.println("|         Menu Conta:        |");
		System.out.println("|                            |");
		System.out.println("|    1 - Deposito            |");
		System.out.println("|    2 - Saque               |");
		System.out.println("|    3 - Transferencia       |");
		System.out.println("|    4 - Extrato             |");
		System.out.println("|                            |");
		System.out.println("|############################|");
		opcao = scan.nextInt();
		
		double valor;
		int numContadestino;
		switch(opcao) {
			case 1:
				System.out.println("Informe o valor a ser depositado:");
				valor = scan.nextDouble();
				this.depositar(valor);
				break;
			case 2:
				System.out.println("Informe o valor que deseja sacar:");
				valor = scan.nextDouble();
				this.sacar(valor);
				break;
			case 3:
				System.out.println("Informe o valor que deseja Transferir:");
				valor = scan.nextDouble();
				
				System.out.println("Informe o numero da conta destino:");
				numContadestino = scan.nextInt();
				Conta contaDestino = Main.banco.getConta(numContadestino);
				if(contaDestino != null) {
					this.transferir(valor, contaDestino);
					System.out.println("Deposito realizado com sucesso!");
				}else {
					System.out.println("O valor não foi depoisitado!");
				}
				break;
			case 4:
				this.imprimirExtrato();
				break;
			default:
				System.out.println("Opção invalida!!!");
		}
		
	}
}
