/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.aprendercrescer.controller;

import br.com.senai.aprendercrescer.dao.ContaDao;
import br.com.senai.aprendercrescer.model.Conta;
import java.util.ArrayList;

/**
 *
 * @author Senai
 */
public class ContaController {
   ContaDao contaDao;

    public ContaController() {
        if (contaDao == null) {
            contaDao = new ContaDao();
        }
    }
    
    public boolean insereConta(Conta conta) {
        return contaDao.insereConta(conta);
    }

    public ArrayList<Conta> getConta() {
        return contaDao.getConta();
    }
    
    public boolean deleteConta(int id){ 
        return contaDao.deleteConta(id);
    }

}


 

