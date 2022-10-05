package bancario.app.model;

import java.util.ArrayList;
import java.util.Scanner;

import persistencia.Persistenciaarquivo;

public class Programa {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Persistenciaarquivo pa = new Persistenciaarquivo();
		Scanner sc = new Scanner(System.in);

		boolean sair = true;
		int opcao = 0;

		while (sair) {
			System.out.println("Escolha uma opção:\n1) Cadastrar um cliente;\n2) Conta;\n3) Listar Clientes;\n4) Buscar Cliente por CPF;\n5) Remover Cliente;\n6) Sair");
			opcao = sc.nextInt();
			switch (opcao) {
			case 1:
				String nome = "";
				String cpf = "";
				System.out.println("Insira o nome do cliente: ");
				nome = sc.next();
				System.out.println("Insira o CPF: ");
				cpf = sc.next();
				Cliente cli = new Cliente(cpf, nome);
				pa.cadastrarCliente(cli);
				break;
			case 2:
				System.out.println("Insira o CPF do cliente: ");
				String cpfConsulta = "";
				int segundaOpcao = 0;
				boolean segundoSair = true;
				cpfConsulta = sc.next();
				Cliente cliConsulta = pa.buscarClienteCPF(cpfConsulta);
				if (cliConsulta != null) {
					while (segundoSair) {
						System.out.println(
								"\n\n\n\nEscolha as opções para CONTA: \n1) Cadastrar uma conta;\n2) Remover uma conta;\n3) Listar contas;\n4) Consultar Saldo;\n5) Depositar;\n6) Sacar;\n7) Sair.");
						segundaOpcao = sc.nextInt();
						switch (segundaOpcao) {
						case 1:
							String numeroConta = "";
							System.out.println("Insira o número da conta");
							numeroConta = sc.next();
							Conta c1 = new Conta(numeroConta);
							cliConsulta.adicionarConta(c1);
							pa.atualizarCliente(cliConsulta);
							break;
						case 2:
							System.out.println("Insira o número da conta");
							numeroConta = sc.next();
							Conta c2 = new Conta(numeroConta);
							cliConsulta.removerConta(c2);
							pa.atualizarCliente(cliConsulta);
						break;
						case 3:
							System.out.println(cliConsulta.getContas());
							break;
						case 4:
							System.out.println("Insira o número da conta");
							numeroConta = sc.next();
							Conta c3 = new Conta(numeroConta);
							if(cliConsulta.localizarConta(c3)!=null) {
								c3 = cliConsulta.localizarConta(c3);
								c3.consultarSaldo(numeroConta);
							}
							break;
						case 5:
							System.out.println("Insira o número da conta");
							numeroConta = sc.next();
							
							float quantia = 0f;
							System.out.println("Insira a quantia a depositar: ");
							quantia = sc.nextFloat();
							
							
							Conta c4 = new Conta(numeroConta);
							if(cliConsulta.localizarConta(c4)!=null) {
								c4 = cliConsulta.localizarConta(c4);
								c4.realizarDeposito(quantia);
								cliConsulta.localizarConta(c4);
								pa.atualizarCliente(cliConsulta);
							}
							break;
						case 6:
							
							System.out.println("Insira o número da conta");
							numeroConta = sc.next();
				
							System.out.println("Insira a quantia a sacar: ");
							quantia = sc.nextFloat();
							
							
							Conta c5 = new Conta(numeroConta);
							if(cliConsulta.localizarConta(c5)!=null) {
								c5 = cliConsulta.localizarConta(c5);
								c5.realizarSaque(quantia);
								cliConsulta.localizarConta(c5);
								pa.atualizarCliente(cliConsulta);
							}
							break;
						case 7:
							segundoSair = false;
							System.out.println("\n\n\n");
							break;
						
						default:

							break;
						}
					}
				} else
					System.err.println("Cliente não encontrado!");
				break;
			case 3:
				Persistenciaarquivo listaClientes = new Persistenciaarquivo();
				System.out.println(listaClientes.getClientes());
				break;
				
			case 4:
				System.out.println("Insira o CPF: ");
				cpf = sc.next();
				Cliente cli3 = new Cliente(cpf);
				if(cli3!=null) {
					System.out.println(pa.buscarClienteCPF(cpf)); 					
				}
				
				
				break;
				
			case 5:
				System.out.println("Insira o CPF: ");
				cpf = sc.next();
				Cliente cli2 = pa.buscarClienteCPF(cpf);
				if(cli2!=null) {
					pa.removerCliente(cli2);
				}
				
				break;
			case 6:
				sair = false;
				break;
			default:
				break;
			}
		}
		
		sc.close();
	}

}