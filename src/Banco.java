import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Banco {

	static Scanner scan = new Scanner(System.in);

	private String nome;
	private static List<Conta> contas = new ArrayList<Conta>();
	private static List<Cliente> clientes = new ArrayList<Cliente>();

	public Banco() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(Conta contas) {
		this.contas.add(contas);
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(Cliente cliente) {
		this.clientes.add(cliente);
	}

	public static void acessarMenuConta(int cpf) {
		for (Conta conta : contas) {
			if (conta.getCliente().getCpf() == cpf) {
				conta.menuConta();
			}
		}
	}
	
	public Conta getConta(int numConta) {
		for (Conta conta : contas) {
			if (conta.getNumero() == numConta) {
				return conta;
			}
		}
		return null;
	}
	
	public void listarClientes() {
		if(clientes.isEmpty()) {
			System.out.println("Nenhum Cliente cadastrado.");
		}else {
			for (Cliente cliente : clientes) {
				cliente.imprimirInformacoes();
			}			
		}
	}

	public static boolean criarConta() {

		int opcao;
		boolean status = false;
		
		System.out.println("|############################|");
		System.out.println("| Informe o tipo de Conta:   |");
		System.out.println("|                            |");
		System.out.println("|   1 - Conta Corrente       |");
		System.out.println("|   2 - Conta Poupança       |");
		System.out.println("|   0 - Retornar ao menu     |");
		System.out.println("|                            |");
		System.out.println("|############################|");
		opcao = scan.nextInt();

		if (opcao == 1) {
			System.out.println("Informe seu Nome:");
			String nome = scan.next();
			System.out.println("Informe seu CPF:");
			int cpf = scan.nextInt();
			
			if (!verificarCpf(cpf)) {
				Cliente cliente = new Cliente(nome, cpf);
				
				clientes.add(cliente);
				
				Conta contaCorrente = new ContaCorrente(cliente);
				contas.add(contaCorrente);
				status = true;
			}else {
				status = false;
			}
		} else if (opcao == 2) {
			System.out.println("Informe seu Nome:");
			String nome = scan.next();
			System.out.println("Informe seu CPF:");
			int cpf = scan.nextInt();

			if (!verificarCpf(cpf)) {
				Cliente cliente = new Cliente(nome, cpf);

				clientes.add(cliente);

				Conta contaPoupanca = new ContaPoupanca(cliente);
				contas.add(contaPoupanca);
				status = true;
			} else {
				status = false;
			}
		} else if (opcao == 0) {

		} else {
			System.out.println("Opção escolhida invalida!!!");
			status = false;
		}
		return status;
	}

	public static boolean verificarCpf(int cpf) {
		boolean existe = false;
		for (Cliente cliente : Main.banco.getClientes()) {
			if (cliente.getCpf() == cpf) {
				existe = true;
			} else {
				existe = false;
			}
		}
		return existe;
	}

	public static void acessarConta() {

		System.out.println("\n\nInforme seu CPF:");
		int cpf = scan.nextInt();

		acessarMenuConta(cpf);
	}
}
