import java.util.Scanner;

public class Main {
	
	public static Banco banco = new Banco();
	static Scanner scan = new Scanner(System.in);
	public static Boolean status = true;

	public static void main(String[] args) {
		System.out.println("Informe o nome do Banco:");
		String nome = scan.next();
		banco.setNome(nome);
		render();	
	}

	private static void render() {
		
		int opcao;
		
		while(status) {
			System.out.println("\n\nBanco " + banco.getNome());
			System.out.println("1 - Criar conta     >>>");
			System.out.println("2 - Acessar conta   >>>");
			System.out.println("3 - Listar Clientes >>>");
			System.out.println("0 - Sair            >>>");
			opcao = scan.nextInt();
			switch(opcao){
				case 0: 
					status = false;
					break;
				case 1:
					if(banco.criarConta()) {
						banco.acessarConta();
						break;
					}else {
						System.out.println("Já existe uma conta com esse CPF!");
						break;
					}
				case 2:
					banco.acessarConta();
					break;
				case 3:
					banco.listarClientes();
					break;
				default:
					System.out.println("Operação indevida!!!");
					status = false;
					break;
			}
			
		}		
	}
}
