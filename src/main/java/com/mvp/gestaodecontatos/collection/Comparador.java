package com.mvp.gestaodecontatos.collection;

import com.mvp.gestaodecontatos.model.Contato;
import java.util.Comparator;

public class Comparador  implements Comparator<Contato>{        
    @Override
    public int compare(Contato contato, Contato outroContato){
        int primeiroContato = Integer.parseInt(contato.getTelefone());
        int segundoContato = Integer.parseInt(outroContato.getTelefone());
        
        if(primeiroContato < segundoContato)
            return -1;

        if(primeiroContato > segundoContato)
            return 1;

        
        return 0;
    }
}
