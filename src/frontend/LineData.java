package frontend;

import shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class LineData extends JFrame implements Node {
    private JButton setColorButton;
    private JButton createLineButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JPanel panel1;
    private JButton cancelButton;
    private Node parent;
    CompletableFuture<Boolean> wait=new CompletableFuture<>();
    public LineData(ArrayList<Integer> values, Shape shape) {
        setContentPane(panel1);
        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(400, 300);
        setColorButton.addActionListener(e -> {
            Color color = JColorChooser.showDialog(null,"choose outline color",Color.black);
            shape.setColor(color);
        });
        createLineButton.addActionListener(e -> {
            try {
                values.add(Integer.parseInt(textField1.getText()));
                values.add(Integer.parseInt(textField2.getText()));
                values.add(Integer.parseInt(textField3.getText()));
                values.add(Integer.parseInt(textField4.getText()));
                ((JFrame)this.getParentNode()).setVisible(true);
               this.setVisible(false);
                wait.complete(true);
            }catch (Exception ex){
                JOptionPane.showMessageDialog(this,"enter a valid data");
            }

        });
        cancelButton.addActionListener(e -> {
            ((JFrame)this.getParentNode()).setVisible(true);
            this.setVisible(false);
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
