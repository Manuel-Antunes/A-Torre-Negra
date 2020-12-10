package com.aTorreNegra.controller;

import com.aTorreNegra.dialogue.Dialogue;
import com.aTorreNegra.dialogue.DialogueNode;
import com.aTorreNegra.dialogue.DialogueNode.NODE_TYPE;
import com.aTorreNegra.dialogue.DialogueTraverser;
import com.aTorreNegra.ui.DialogueBox;
import com.aTorreNegra.ui.OptionBox;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class DialogueController extends InputAdapter {

    private DialogueTraverser traverser;
    private DialogueBox dialogueBox;
    private OptionBox optionBox;
    private boolean active;
    private boolean finished;

    public DialogueController(DialogueBox dialogueBox, OptionBox optionBox) {
        this.dialogueBox = dialogueBox;
        this.optionBox = optionBox;
        active = false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (dialogueBox.isVisible()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (optionBox.isVisible()) {
            if (keycode == Keys.UP) {
                optionBox.moveUp();
                return true;
            } else if (keycode == Keys.DOWN) {
                optionBox.moveDown();
                return true;
            }
        }
        if (traverser != null && keycode == Keys.X && dialogueBox.isFinishend()) {
            if (traverser.getType() == NODE_TYPE.END) {
                traverser = null;
                dialogueBox.setVisible(false);

            } else if (traverser.getType() == NODE_TYPE.LINEAR) {
                progress(0);
            } else if (traverser.getType() == NODE_TYPE.MULTIPLE_CHOICE) {
                progress(optionBox.getIndex());
            }
            active = false;
            return true;
        }
        if (dialogueBox.isVisible()) {
            return true;
        }
        return false;
    }

    public void update(float delta) {
        if (dialogueBox.isFinishend() && traverser != null) {
            if (traverser.getType() == NODE_TYPE.MULTIPLE_CHOICE) {
                optionBox.setVisible(true);
            }
        }
    }

    public void startDialogue(Dialogue dialogue) {
        active = true;
        traverser = new DialogueTraverser(dialogue);
        dialogueBox.setVisible(true);
        dialogueBox.animateText(traverser.getText());

        if (traverser.getType() == NODE_TYPE.MULTIPLE_CHOICE) {
            optionBox.clear();
            for (String s : dialogue.getNode(dialogue.getStart()).getLabels()) {
                optionBox.addOption(s);
            }
        }
        dialogue.setState(false);
    }

    private void progress(int index) {
        optionBox.setVisible(false);
        DialogueNode nextNode = traverser.getNextNode(index);
        dialogueBox.animateText(nextNode.getText());
        if (nextNode.getType() == NODE_TYPE.MULTIPLE_CHOICE) {
            optionBox.clearChoices();
            for (String s : nextNode.getLabels()) {
                optionBox.addOption(s);
            }
        }
    }

    public boolean isActive() {
        return active;
    }

    public DialogueTraverser getTraverser() {
        return traverser;
    }
    

}
