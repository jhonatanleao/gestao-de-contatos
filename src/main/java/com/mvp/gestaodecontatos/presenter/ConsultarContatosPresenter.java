package com.mvp.gestaodecontatos.presenter;

import com.mvp.gestaodecontatos.collection.Comparador;
import com.mvp.gestaodecontatos.collection.ContatoCollection;
import com.mvp.gestaodecontatos.model.Contato;
import com.mvp.gestaodecontatos.view.ConsultarContatoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collections;
import java.util.ListIterator;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class ConsultarContatosPresenter {

    private ConsultarContatoView view;
    private ContatoCollection contatos;
    private DefaultTableModel tmContatos;
    private IncluirContatoPresenter viewInlcuir;
    private int total;

    public ConsultarContatosPresenter(ContatoCollection contatos) {
        this.contatos = contatos;

        view = new ConsultarContatoView();

        criarTabela(tmContatos, view);
        atualizaNumeroTotal();

        view.getBntVisualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editar();
                criarTabela(tmContatos, view);
            }
        });

        view.getBtnExcluir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = view.getTblContatos().getSelectedRow();
                if (row != -1) {
                    int resposta = JOptionPane.showConfirmDialog(null, "Confirme para excluir " + contatos.toString(row));
                    if (resposta == 0) {
                        contatos.remove(row);
                        criarTabela(tmContatos, view);
                        atualizaNumeroTotal();
                    }
                }
            }
        });

        view.getBtnFechar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fechar();
            }
        });

        view.getCbOrdenarTelefone().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    ordenaTelefone();
                }
            }
        });

        view.setVisible(true);
    }

    private void fechar() {
        view.dispose();
    }

    private void criarTabela(DefaultTableModel tmContatos, ConsultarContatoView view) {

        tmContatos = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Nome", "Telefone"}
        );

        view.getTblContatos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        total = 0;
        tmContatos.setNumRows(0);
        ListIterator<Contato> it = contatos.getContatos().listIterator();

        while (it.hasNext()) {
            total++;
            Contato contato = it.next();
            tmContatos.addRow(new Object[]{contato.getNome(), contato.getTelefone()});
        }

        view.getTblContatos().setModel(tmContatos);

    }

    private void atualizaNumeroTotal() {
        view.getJblNumTotal().setText(Integer.toString(total));
    }

    private void ordenaTelefone() {
        Comparador comparator = new Comparador();
        Collections.sort(contatos.getContatos(), comparator);
        criarTabela(tmContatos, view);
    }

    private void editar() {
        int row;
        row = view.getTblContatos().getSelectedRow();
        if (row != -1) {
            viewInlcuir = new IncluirContatoPresenter(contatos, row);
        }
    }
}
