package br.edu.cesarschool.next.oo.negocio;

import br.edu.cesarschool.next.oo.dao.DAOContaCorrente;
import br.edu.cesarschool.next.oo.entidade.Conta;
import br.edu.cesarschool.next.oo.entidade.ContaCorrente;
import br.edu.cesarschool.next.oo.entidade.ContaPoupanca;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MediatorContaCorrente {

    private DAOContaCorrente daoCC = new DAOContaCorrente();

    public MediatorContaCorrente() {
    }

    public String incluir(ContaCorrente conta){
        if(conta == null){
            return "Conta inexistente!";
        }
        else if(stringNulaOuVazia(conta.getNumero())){
            return "Número da conta não informada!";
        }
        else if(conta.getNumero().length() < 5 || conta.getNumero().length() > 8){
            return "Numero da conta precisa ter entre 5 e 8 dígitos";
        }
        else if(conta.getSaldo() < 0){
            return "Saldo deve ser maior ou igual a zero!";
        }
        else if(stringNulaOuVazia(conta.getNomeCorrentista())){
            return "Nome do correntista não informado!";
        }
        else if(conta.getNomeCorrentista().length() > 60){
            return "Nome do correntista precisar ter no máximo 60 caracteres";
        }
        else if(conta instanceof ContaPoupanca) {
            ContaPoupanca contaPoupanca = (ContaPoupanca) conta;
            if (contaPoupanca.getPercentualBonus() < 0) {
                return "Percentual de bônus precisa ser igual ou maior que zero!";
            }
        }
        conta.setDataHoraCriacao(LocalDateTime.now());
        boolean retorno = daoCC.incluir(conta);
        if(!retorno){
            return "Conta ja existente!";
        }
        else{
            return null;
        }
    }

    public String creditar(double valor, String numero){
        if(valor < 0){
            return "Valor a ser creditado precisa ser maior ou igual a zero!";
        }
        else if(stringNulaOuVazia(numero)){
            return "Número da conta não informado!";
        }
        else{
            ContaCorrente conta = daoCC.buscar(numero);
            if(conta == null){
                return "Conta não existente!";
            }
            else{
                conta.creditar(valor);
                daoCC.alterar(conta);
                return null;
            }
        }

    }

    public String debitar(double valor, String numero){
        if(valor < 0){
            return "Valor a ser debitado precisa ser maior ou igual a zero!";
        }
        else if(stringNulaOuVazia(numero)){
            return "Número da conta não informado!";
        }
        else {
            ContaCorrente conta = daoCC.buscar(numero);
            if(conta == null){
                return "Conta não existente";
            }
            else if(conta.getSaldo() < valor*(1+conta.obterAliquotaCpmf())){
                return "Saldo insuficiente";
            }
            else{
                conta.debitar(valor);
                daoCC.alterar(conta);
                return null;
            }
        }
    }

    public String excluir(String numero){
        ContaCorrente conta = daoCC.buscar(numero);
        if(conta == null){
            return "Conta não existente!";
        }
        else{
            daoCC.excluir(conta.obterChave());
            return null;
        }
    }

    public ContaCorrente buscar(String numero){
        if(stringNulaOuVazia(numero)){
            return null;
        }
        else{
            return daoCC.buscar(numero);
        }
    }

    public List<ContaCorrente> gerarRelatorioGeral(){
        ContaCorrente[] contas = daoCC.buscarTodos();
        List<ContaCorrente> listaContas = Arrays.asList(contas);

        Collections.sort(listaContas, new ComparadorContaCorrenteSaldo());

        return listaContas;
    }

    public String excluirZeradas(){
        ContaCorrente[] contas = daoCC.buscarTodos();
        List<ContaCorrente> listaContas = Arrays.asList(contas);

        List<ContaCorrente> excluir = new ArrayList<>();


        for(ContaCorrente conta : listaContas ){
            if(conta.getSaldo() == 0){
                excluir.add(conta);
                daoCC.excluir(conta.obterChave());
            }
        }

        if(excluir.size() == 0){
            return "Não haviam contas com saldo zerado!";
        }
        else{
            String retorno = null;

            if(excluir.size() == 1){
                retorno = String.format("%d conta com saldo zerado foi excluída.", (excluir.size()));
            }
            else{
                retorno = String.format("%d contas com saldo zerado foram excluídas.", (excluir.size()));
            }
            return retorno;
        }
    }

    public boolean stringNulaOuVazia(String numero){ return numero == null || numero.trim().equals(""); }
 }

