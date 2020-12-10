
package com.aTorreNegra.dialogue;

import java.util.HashMap;
import java.util.Map;


public class Dialogue {
    private Map<Integer, DialogueNode> nodes = new HashMap<Integer, DialogueNode>();
    private int id;
    private int index;
    private boolean state;
    public Dialogue(int id,int index, boolean state){
        this.id = id;
        this.index = index;
        this.state = state;
    }
    public DialogueNode getNode(int id) {
        return nodes.get(id);
    }
    
    public void addNode(DialogueNode node){
        this.nodes.put(node.getIndex(), node);
    }
    
    public int getStart(){
        return 0;
    }

    public int getId() {
        return id;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Map<Integer, DialogueNode> getNodes() {
        return nodes;
    }
    
    
}
