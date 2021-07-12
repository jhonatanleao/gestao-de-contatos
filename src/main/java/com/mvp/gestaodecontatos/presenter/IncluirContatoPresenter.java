package com.mvp.gestaodecontatos.presenter;

import com.mvp.gestaodecontatos.collection.ContatoCollection;
import com.mvp.gestaodecontatos.model.Contato;
import com.mvp.gestaodecontatos.view.IncluirContatoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class IncluirContatoPresenter {

    private IncluirContatoView view;
    private ContatoCollection Arraycontatos;

    public IncluirContatoPresenter(ContatoCollection contatos) {
        this.Arraycontatos = contatos;
        view = new IncluirContatoView();

        JButton fecha = view.getBtnFechar();
        fecha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent clicado) {
                fechar();
            }
        });

        JButton salva = view.getBtnSalvar();
        salva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent clicado) {
                try {
                    salvar();
                    fechar();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(view,
                            "Informe apenas numeros",
                            "Caracteres invalidos",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        view.setVisible(true);
    }

    public IncluirContatoPresenter(ContatoCollection contatos, int posicao) {
        this.Arraycontatos = contatos;
        view = new IncluirContatoView();

        JButton fecha = view.getBtnFechar();
        fecha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent clicado) {
                fechar();
            }
        });

        JButton salva = view.getBtnSalvar();
        salva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent clicado) {
                try {
                    editar(posicao);
                    fechar();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(view,
                            "Informe apenas numeros",
                            "Caracteres invalidos",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        view.setVisible(true);
    }

    private void editar(int posicao) {
        String nome = view.getTxtNome().getText();
        String telefone = view.getTxtTelefone().getText();
        verificaTipoTelefone(telefone);
        Contato contato = new Contato(nome, telefone);

        Arraycontatos.edit(posicao, contato);
    }

    private void fechar() {
        view.dispose();
    }

    private void salvar() {
        String nome = view.getTxtNome().getText();
        String telefone = view.getTxtTelefone().getText();
        verificaTipoTelefone(telefone);
        Contato contato = new Contato(nome, telefone);

        Arraycontatos.add(contato);

        JOptionPane.showMessageDialog(view,
                "Contato " + contato.getNome() + " salvo com sucesso!",
                "Salvo com sucesso",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void verificaTipoTelefone(String telefone) {
        int primeiroContato = Integer.parseInt(telefone);
    }
}
