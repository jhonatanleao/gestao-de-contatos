package com.mvp.gestaodecontatos.persistence;

import com.mvp.gestaodecontatos.collection.ContatoCollection;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serialization {

    public Serialization() {
    }

    public void criarArquivo(ContatoCollection contatos) {
        FileOutputStream fos = null;
        ObjectOutputStream oss = null;

        try {
            fos = new FileOutputStream("arquivo.txt");
            oss = new ObjectOutputStream(fos);
            oss.writeObject(contatos);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não enontrado");
        } catch (IOException e) {
            System.out.println("Erro ao criar arquivo");
        } finally {
            if (oss != null) {
                try {
                    oss.close();
                } catch (IOException e) {
                    System.out.println("Erro ao fechar o arquivo");
                }
            }
        }
    }

    public ContatoCollection lerArquivo() {
        ContatoCollection contatos2 = new ContatoCollection();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        contatos2 = null;
        try {
            fis = new FileInputStream("arquivo.txt");
            ois = new ObjectInputStream(fis);
            contatos2 = (ContatoCollection)ois.readObject();
            return contatos2; 
        } catch (ClassNotFoundException e) {
            System.out.println("Classe não enontrado");
        } catch (IOException e) {
            System.out.println("Erro ao criar arquivo");
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    System.out.println("Erro ao fechar o arquivo");
                }
            }
        }
        return null;
    }

}
