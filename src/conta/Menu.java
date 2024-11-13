package conta;

import java.util.InputMismatchException;
import java.util.Scanner;

//import conta.model.Conta;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;
import java.io.IOException;
import conta.controller.ContaController;

public class Menu {

	public static Scanner leia = new Scanner(System.in);

	public static void main(String[] args) {

		ContaController contas = new ContaController();

		/*
		 * Teste da Classe Conta Conta c1 = new Conta(1,123,1,"Adiana",10000.0f);
		 * c1.visualizar(); c1.sacar(12000.0f); c1.visualizar(); c1.depositar(5000.0f);
		 * c1.visualizar();
		 */

		int opcao, numero, agencia, tipo, aniversario, numeroCelular = 0, numeroDestino;
		String titular;
		float saldo, limite, valor;

		System.out.println("\n Criar contas: \n");
		// Teste da Classe Conta Poupança
		// Teste da Classe Conta Corrente
		ContaCorrente cc2 = new ContaCorrente(contas.gerarNumero(), 124, 1, "Maria da Silva", 2000f, 100.0f, 99999999);
		contas.cadastrar(cc2);

		ContaPoupanca cp2 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Juliana Ramos", 8000f, 15, 88888888);
		contas.cadastrar(cp2);

		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, "José da Silva", 10.0f, 1000.0f, 77777777);
		contas.cadastrar(cc1);
		/*cc1.visualizar();
		cc1.sacar(12000.0f);
		cc1.visualizar();
		cc1.depositar(5000.0f);
		cc1.visualizar();*/

		ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 123, 2, "Maria dos Santos", 100000.0f, 15, 66666666);
		contas.cadastrar(cp1);
		/*cp1.visualizar();
		cp1.sacar(1000.0f);
		 cp1.visualizar();
		cp1.depositar(5000.0f);
		cp1.visualizar();*/

		//contas.listarTodas();

		while (true) {

			System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND
					+ "*****************************************************");
			System.out.println("                                                     ");
			System.out.println("                     CUMAN's Bank                    ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("            1 - Criar Conta                          ");
			System.out.println("            2 - Listar todas as Contas               ");
			System.out.println("            3 - Buscar Conta por Numero              ");
			System.out.println("            4 - Atualizar Dados da Conta             ");
			System.out.println("            5 - Apagar Conta                         ");
			System.out.println("            6 - Sacar                                ");
			System.out.println("            7 - Depositar                            ");
			System.out.println("            8 - Transferir valores entre Contas      ");
			System.out.println("            9 - Sair                                 ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("Entre com a opção desejada:                          ");
			System.out.println("                                                     " + Cores.TEXT_RESET);

			try {
				opcao = leia.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("\n Digite valores inteiros!");
				leia.nextLine();
				opcao = 0;
			}

			if (opcao == 9) {
				System.out.println(Cores.TEXT_WHITE_BOLD + "\nBanco do Brazil com Z - O seu Futuro começa aqui!");
				sobre();
				leia.close();
				System.exit(0);
			}

			switch (opcao) {
			case 1:
				System.out.println(Cores.TEXT_WHITE + "Criar Conta\n\n");

				System.out.println(" Digite o número da agência: ");
				agencia = leia.nextInt();
				System.out.println(" Digite o nome do titular: ");
				leia.skip("\\R?");
				titular = leia.nextLine();
				System.out.println(" Digite o número do celular do titular: ");
				numeroCelular = leia.nextInt();

				do {
					System.out.println(" Digite o número da conta (1-CC ou 2-CP): ");
					tipo = leia.nextInt();
				} while (tipo < 1 && tipo > 2);

				System.out.println(" Digite o saldo da conta (RS): ");
				saldo = leia.nextFloat();

				switch (tipo) {
				case 1 -> {
					System.out.println("Digite o limite de crédito: ");
					limite = leia.nextFloat();

					contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite, numeroCelular));
				}
				case 2 -> {
					System.out.println(" Digite o dia do aniversario da conta: ");
					aniversario = leia.nextInt();

					contas.cadastrar(
							new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario, numeroCelular));
				}
				}

				keyPress();
				break;

			case 2:
				System.out.println(Cores.TEXT_WHITE + "Listar todas as Contas\n\n");
				contas.listarTodas();

				keyPress();
				break;
			case 3:
				System.out.println(Cores.TEXT_WHITE + "Consultar dados da Conta - por número\n\n");

				System.out.println("Digite o numero da conta: ");
				numero = leia.nextInt();

				contas.procurarPorNumero(numero);

				keyPress();
				break;
			case 4:
				System.out.println(Cores.TEXT_WHITE + "Atualizar dados da Conta\n\n");
				System.out.println(" Atualizar os dados da conta \n\n");
				System.out.println(" Digite o número da conta: ");
				numero = leia.nextInt();

				var buscaConta = contas.buscarNaCollection(numero);

				if (buscaConta != null) {
					tipo = buscaConta.getTipo();

					System.out.println(" digite o numero da Agência: ");
					agencia = leia.nextInt();

					System.out.println(" Digite o nome do titular: ");
					leia.skip("\\R?");
					titular = leia.nextLine();

					System.out.println(" Digite o saldo da Conta (RS): ");
					saldo = leia.nextFloat();

					switch (tipo) {

						case 1 -> {
							System.out.println(" Digite o limite de credito (RS): ");
							limite = leia.nextFloat();
	
							contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite, numeroCelular));
						}
						case 2 -> {
							System.out.println(" Digite o dia do aniversario da conta: ");
							aniversario = leia.nextInt();
							
							contas.atualizar(new ContaPoupanca(numero, agencia, tipo,titular,saldo,aniversario, numeroCelular));
						}
						default -> {
							System.out.println(" Tipo de conta invalido!: ");
						}
					}
				}else {
					System.out.println(" A conta não foi encontrada!: ");
				}
				
				keyPress();
				break;
				
			case 5:
				System.out.println(Cores.TEXT_WHITE + "Apagar a Conta\n\n");
				
				System.out.println(" Digite o número da conta: ");
				numero = leia.nextInt();
				
				contas.deletar(numero);
				numero = leia.nextInt();
				
				keyPress();
				break;
				
			case 6:
				System.out.println(Cores.TEXT_WHITE + "Saque\n\n");
				
				System.out.println(" Digite o número da conta: ");
				numero = leia.nextInt();
				
				do {
					System.out.println(" Digite o valor do saque (RS): ");
					valor = leia.nextFloat();
				}while(valor <= 0);
				
				contas.sacar(numero, valor);
				
				keyPress();
				break;
				
			case 7:
				System.out.println(Cores.TEXT_WHITE + "Depósito\n\n");
				
				System.out.println(" Digite o numero da conta: ");
				numero = leia.nextInt();
				
				do {
					System.out.println(" Digite o valor do deposito (RS): ");
					valor = leia.nextFloat();
				}while(valor <= 0);
				
				contas.depositar(numero, valor);
				
				keyPress();
				break;
			case 8:
				System.out.println(Cores.TEXT_WHITE + "Transferência entre Contas\n\n");
				
				System.out.println(" Digite o número da conta de Origem: ");
				numero = leia.nextInt();

				System.out.println(" Digite o número da conta de destino: ");
				numeroDestino = leia.nextInt();
				
				do {
					System.out.println(" Digite o valor da Tranferência (RS): ");
					valor = leia.nextFloat();
				}while(valor <= 0);
				
				contas.transferir(numero, numeroDestino, valor);
				
				keyPress();
				break;
			default:
				System.out.println(Cores.TEXT_RED_BOLD + "\nOpção Inválida!\n" + Cores.TEXT_RESET);
				keyPress();
				break;
			}
		}

	}

	public static void sobre() {
		System.out.println("\n*********************************************************");
		System.out.println("Projeto Desenvolvido por:");
		System.out.println(" Mozart Cuman Albuquerque - mozartcumana@gmail.com");
		System.out.println("https://github.com/mozartCuman/contabancaria");
		System.out.println("*********************************************************");
	}

	public static void keyPress() {

		try {

			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
			System.in.read();

		} catch (IOException e) {

			System.out.println("Você pressionou uma tecla diferente de enter!");

		}
	}
}