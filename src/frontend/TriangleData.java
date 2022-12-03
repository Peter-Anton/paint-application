package frontend;

import shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class TriangleData extends JFrame implements Node {
    private JButton setColorButton;
    private JButton setFillColorButton;
    private JButton createTriangleButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JPanel panel1;
    private Node parent;
    CompletableFuture<Boolean> wait=new CompletableFuture<>();
    public TriangleData(ArrayList<Integer>values, Shape shape) {
        setContentPane(panel1);
        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(400, 300);
        setColorButton.addActionListener(e -> {
            Color color = JColorChooser.showDialog(null,"choose outline color",Color.black);
            shape.setColor(color);
        });
        setFillColorButton.addActionListener(e -> {
            Color color = JColorChooser.showDialog(null,"choose fill color ",Color.black);
            shape.setFillColor(color);
        });
        createTriangleButton.addActionListener(e -> {

            try {
                values.add(Integer.parseInt(textField1.getText()));
                values.add(Integer.parseInt(textField2.getText()));
                values.add(Integer.parseInt(textField3.getText()));
                values.add(Integer.parseInt(textField4.getText()));
                values.add(Integer.parseInt(textField5.getText()));
                values.add(Integer.parseInt(textField6.getText()));
//                ((JFrame)TriangleData.this.getParentNode()).setVisible(true);
//                TriangleData.this.setVisible(false);
                wait.complete(true);
                dispose();
            }catch (Exception ex){
                JOptionPane.showMessageDialog(this,"enter data");
            }

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
