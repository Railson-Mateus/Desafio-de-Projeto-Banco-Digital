
public class Cliente{

	private String nome;
	private int cpf;

	public Cliente(String nome, int cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}

	public Cliente() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	
	public Conta getConta(int cpf) {
		for(Conta conta : Main.banco.getContas()) {
			conta.imprimirInformacoes();
			if(conta.getCliente().getCpf() == cpf) {
				return conta;
			}
		}
		return null;
	}

	public void imprimirInformacoes() {
		System.out.println(String.format("Titular: %s", this.getNome()));
		System.out.println(String.format("CPF: %s", this.getCpf()));
	}
}
