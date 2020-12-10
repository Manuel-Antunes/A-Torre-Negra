package com.aTorreNegra.model;

import com.aTorreNegra.dialogue.DialogueNode;
import java.util.ArrayList;

public class Quest {

    private DialogueNode objetivoTexto;
    private ArrayList<Item> objetivosItem = new ArrayList<Item>();
    private ArrayList<Item> recompensas = new ArrayList<Item>();
    private boolean state;

    public Quest(DialogueNode objetivoTexto, boolean state) {
        this.objetivoTexto = objetivoTexto;
        this.state = state;
    }

    public Quest(DialogueNode objetivoTexto) {
        this.objetivoTexto = objetivoTexto;
        this.state = false;
    }
    public ArrayList<Item> completarQuest(DialogueNode node, Inventory in) {
        if (objetivosItem.size() == 0) {
            if (node.getId() == objetivoTexto.getId()) {
                return recompensas;
            }
        } else if (objetivoTexto == null) {
            int a = 0;
            for (int i = 0; i < objetivosItem.size(); i++) {
                for (int j = 0; j < in.getItems().size(); j++) {
                    if (in.getItems().get(i) == objetivosItem.get(j)) {
                        a = 1;
                        break;
                    }
                }
                if(a == 0){
                    break;
                }
            }
            if(a == 1){
                return recompensas;
            }
        }else{
            int a = 0;
            for (int i = 0; i < objetivosItem.size(); i++) {
                for (int j = 0; j < in.getItems().size(); j++) {
                    if (in.getItems().get(i) == objetivosItem.get(j)) {
                        a = 1;
                        break;
                    }
                }
                if(a == 0){
                    break;
                }
            }
            if(a == 1 && node.getId() == objetivoTexto.getId()){
                return recompensas;
            }
        }
        return null;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public DialogueNode getObjetivoTexto() {
        return objetivoTexto;
    }
    
}
