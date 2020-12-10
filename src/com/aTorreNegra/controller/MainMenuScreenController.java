/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aTorreNegra.controller;

import com.aTorreNegra.screen.GameScreen;
import com.aTorreNegra.screen.MainMenuScreen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import javax.swing.JOptionPane;

/**
 *
 * @author Matheus
 */
public class MainMenuScreenController extends InputAdapter {
    private MainMenuScreen screen;

    public MainMenuScreenController(MainMenuScreen screen) {
        this.screen = screen;
    }
    
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.X) {
            return true;
        }
        if (keycode == Keys.UP){
            return true;
        }
        if (keycode == Keys.DOWN){
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Keys.UP) {
            screen.moveUp();
            return true;
        } else if (keycode == Keys.DOWN) {
            screen.moveDown();
            return true;
        }else if (keycode == Keys.X){
            if(screen.getSelectorIndex()==0||screen.getSelectorIndex()==1){
                screen.getApp().setScreen(new GameScreen(screen.getApp()));
            }else if(screen.getSelectorIndex()==2){
                
            }else if(screen.getSelectorIndex()==3){
                JOptionPane.showMessageDialog(null, "TODOS OS DIRETOS RESERVADOS PARA:\nO GRANDE GALAXILORDEUNIVERSALSUPREMODELICIOSODAGOSTOZURAINFINITAMENTEIMENSURAVELEKANXISSEINSUPERAVEL\nDELINDOSORRISOEDEUSSUPREMODAPROGRAMAÇAOAPENASACITEMMEROSMORTAISESENHORSUPREMODETUDOAQULOQUEENERD: MANUEL NASCIMENTO");
            }else{
                
            }
            return true;
        }
        return false;
    }

}
