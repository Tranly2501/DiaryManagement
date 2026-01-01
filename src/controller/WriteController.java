package controller;

import model.DiaryModel;
import view.WriteView;

import javax.swing.*;
import java.awt.*;

public class WriteController extends Component {
    private WriteView write;
    private DiaryModel diaryModel;

    public WriteController ( WriteView write){
        this.write = write;
        this.diaryModel = new DiaryModel();
        intController();
        loadData();
    }

    public void intController() {
        this.write.getBtnHuy().addActionListener( e -> {
            System.out.println(" Ban dx bam huy");
        });
        this.write.getBtnLuu().addActionListener(e ->{
            diaryModel.saveDinary();
            JOptionPane.showMessageDialog(write,"Đã lưu thành công nhật ký!");
        });
    }
    public  void loadData(){}
}
