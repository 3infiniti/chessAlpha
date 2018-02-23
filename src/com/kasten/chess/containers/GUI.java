package com.kasten.chess.containers;

import com.kasten.chess.pieces.Piece;
import com.kasten.chess.views.BoardWindow;
import com.kasten.chess.views.MenuWindow;
import com.kasten.chess.views.OptionsWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class GUI {
    private App myApp;
    private String currentView;
    private MenuWindow mainMenu;
    private OptionsWindow optionsMenu;
    private BoardWindow gameScreen;

    public GUI (App app) {
        myApp = app;
        currentView = "";
    }

    public void updateState(HashMap<String, String> newState) {
        String newView = newState.get("view");
        ArrayList<ArrayList<String>> boardState =  myApp.getBoardState();

        if (!newView.equals(currentView)) {
            System.out.println("new view: " + newView); // FOR DEBUGGING
            if (newView.equals("options")) optionsMenu = new OptionsWindow(this, newState);
            if (newView.equals("main")) mainMenu = new MenuWindow(this, newState);
            if (newView.equals("board")) {
                Board newBoard = myApp.getBoard();
                gameScreen = new BoardWindow(this, newState, boardState);
            }
        }
    }

    public void setView(String newView) {
        HashMap<String, String> newState = getOptions();
        newState.put("view", newView);
        myApp.setOptions(newState);
    }

    public void setOptions(HashMap<String, String> selectedOptions) {
        System.out.println("setting " + selectedOptions.get("theme"));
        myApp.setOptions(selectedOptions);
    }

    public HashMap<String, String> getOptions() {
        return myApp.getOptions();
    }

    public void setSelected(String selected) {
        //HashMap<String, String> newState = getOptions();
        //newState.put("selectedCell", selected);
        //myApp.setOptions(newState);
        myApp.setSelected(selected);
    }
}