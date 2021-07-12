package com.mvp.gestaodecontatos.collection;

import com.mvp.gestaodecontatos.model.Contato;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContatoCollection implements Serializable{

    private ArrayList<Contato> contatos;

    public ContatoCollection() {
        contatos = new ArrayList<>();
    }

    public void add(Contato contato) {
        if (contatos.contains(contato)) {
            throw new RuntimeException("Contato já exite");
        }
        if (contato != null) {
            contatos.add(contato);
        } else {
            throw new RuntimeException("Forneça uma instância válida de um contato");
        }
    }
    
    public void remove(int posicao){
        contatos.remove(posicao);
    }

    public String toString(int posicao) {
        return contatos.get(posicao).getNome();
    }
    
    public void edit(int posicao, Contato novoContato){
        contatos.set(posicao, novoContato);
    }
    

    public List<Contato> getContatos() {
        return contatos;
    }
}
