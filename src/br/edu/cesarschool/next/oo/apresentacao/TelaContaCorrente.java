package br.edu.cesarschool.next.oo.apresentacao;

import br.edu.cesarschool.next.oo.entidade.Conta;
import br.edu.cesarschool.next.oo.entidade.ContaCorrente;
import br.edu.cesarschool.next.oo.entidade.ContaPoupanca;
import br.edu.cesarschool.next.oo.negocio.MediatorContaCorrente;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class TelaContaCorrente {
    private static final Scanner ENTRADA = new Scanner(System.in);

    private MediatorContaCorrente mediatorConta = new MediatorContaCorrente();

    public void iniciarTela(){

        int opcao = 0;

        while(opcao != 8){
            System.out.println("Escolha uma opção: ");
            System.out.println("1 - Registrar conta");
            System.out.println("2 - Creditar");
            System.out.println("3 - Debitar");
            System.out.println("4 - Excluir");
            System.out.println("5 - Buscar conta");
            System.out.println("6 - Gerar relatório geral");
            System.out.println("7 - Excluir Contas com Saldo zerado");
            System.out.println("8 - Sair");

            opcao = ENTRADA.nextInt();

            switch (opcao){
                case 1:
                    incluir();
                    break;
                case 2:
                    creditar();
                    break;
                case 3:
                    debitar();
                    break;
                case 4:
                    excluir();
                    break;
                case 5:
                    buscar();
                    break;
                case 6:
                    gerarRelatorioGeral();
                    break;
                case 7:
                    excluirZeradas();
                    break;
                case 8:
                    System.out.println("Programa finalizado!");
                    break;
                default:
                    System.out.println("Opção inválida!\n");
                    break;
            }
        }
    }

    private void incluir(){

        ContaCorrente conta = null;

        System.out.print("Digite o numero da conta: ");
        String numero = ENTRADA.next();
        System.out.println();
        System.out.print("Digite o saldo: ");
        double saldo = ENTRADA.nextDouble();
        System.out.println();
        System.out.print("Digite o nome do Titular da conta: ");
        String nome = ENTRADA.next();
        System.out.println();

        int opcao = 0;

        do{
            System.out.println("Conta corrente ou Poupança?");
            System.out.print("Digite 1 para conta corrente ou 2 para poupança: ");
            opcao = ENTRADA.nextInt();
        }while(!(opcao == 1 || opcao ==2));

        String mensagem = "";
        
        if(opcao == 1){
            conta = new ContaCorrente(numero, saldo, nome);
        }
        else if(opcao == 2){
            System.out.print("\nDigite o percentual de bônus da Conta Poupança: ");
            double bonus = ENTRADA.nextDouble();
            conta = new ContaPoupanca(numero, saldo, nome, bonus);
        }

        mensagem = mediatorConta.incluir(conta);

        if(mensagem == null){
            System.out.println();
            System.out.println("Inclusão bem sucedida!");
        }
        else{
            System.out.println(mensagem);
        }


    }

    private void creditar(){
        System.out.print("Digite o numero da conta: ");
        String numero = ENTRADA.next();
        System.out.println();
        System.out.print("Digite o valor a ser creditado: ");
        double saldo = ENTRADA.nextDouble();
        System.out.println();

        String mensagem = mediatorConta.creditar(saldo, numero);
        if(mensagem == null){
            System.out.println("O valor " + saldo + " foi creditado na conta " + numero + ".");
            System.out.println("O novo saldo é: " + mediatorConta.buscar(numero).getSaldo());
        }
        else{
            System.out.println(mensagem);
        }
    }

    private void debitar(){
        System.out.print("Digite o numero da conta: ");
        String numero = ENTRADA.next();
        System.out.println();
        System.out.print("Digite o valor a ser deditado: ");
        double saldo = ENTRADA.nextDouble();
        System.out.println();

        String mensagem = mediatorConta.debitar(saldo, numero);
        if(mensagem == null){
            System.out.println("O valor " + saldo + " foi debitado na conta " + numero + ".");
            System.out.println("O novo saldo é: " + mediatorConta.buscar(numero).getSaldo());
        }
        else{
            System.out.println(mensagem);
        }
    }

    private void excluir(){
        System.out.print("Digite o numero da conta: ");
        String numero = ENTRADA.next();
        String mensagem = mediatorConta.excluir(numero);
        if(mensagem == null){
            System.out.println("Conta excluída!");
        }
        else{
            System.out.println(mensagem);
        }
    }

    private void buscar(){
        System.out.print("Digite o numero da conta: ");
        String numero = ENTRADA.next();
        System.out.println();
        ContaCorrente conta = mediatorConta.buscar(numero);
        if(conta == null){
            System.out.println("Conta inexistente!\n");
        }
        else {
            System.out.println((conta.toString()));
        }
    }

    private void gerarRelatorioGeral(){
       System.out.println();
       List<ContaCorrente> contas = mediatorConta.gerarRelatorioGeral();
       if(contas.size() > 0){
           for(ContaCorrente conta : contas){
               System.out.println(conta.toString());
           }
       }
       else{
           System.out.println("Não existem contas cadastradas! \n");
       }
    }

    private void excluirZeradas(){
        String mensagem = mediatorConta.excluirZeradas();
        System.out.println(mensagem);

        gerarRelatorioGeral();
    }
}
