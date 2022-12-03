package frontend;

import shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class CircleData extends JFrame implements Node{
    private JButton setColorButton;
    private JButton setFillColorButton;
    private JButton createCircleButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JPanel panel1;
    private Node parent;
    CompletableFuture<Boolean> wait=new CompletableFuture<>();
    public CircleData(ArrayList<Integer>values, Shape shape) {
        setContentPane(panel1);
        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(400, 300);
        createCircleButton.addActionListener(e -> {
            try {
                values.add(Integer.parseInt(textField1.getText()));
                values.add(Integer.parseInt(textField2.getText()));
                values.add(Integer.parseInt(textField3.getText()));
//                ((JFrame)CircleData.this.getParentNode()).setVisible(true);
//                CircleData.this.setVisible(false);
                wait.complete(true);
                dispose();
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null,"enter valid data");
            }

        });
        setColorButton.addActionListener(e -> {
            Color color = JColorChooser.showDialog(null,"choose outline color",Color.black);
            shape.setColor(color);
        });
        setFillColorButton.addActionListener(e -> {
            Color color = JColorChooser.showDialog(null,"choose fill color ",Color.black);
            shape.setFillColor(color);
        });
    }

    @Override
    public Node getParentNode() {
        return parent;
    }

    @Override
    public void setParent(Node node) {
        parent=node;
    }
    public CompletableFuture<Boolean> end(){
        return wait;
    }
}
