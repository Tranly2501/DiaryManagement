package main;

import controller.WriteController;
import model.DiaryModel;
import view.WriteView;

import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater( () ->{
            WriteView writeView = new WriteView();
            new WriteController(writeView);
            writeView.setVisible(true);
        });
    }

}
