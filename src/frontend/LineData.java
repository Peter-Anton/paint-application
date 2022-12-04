package frontend;

import shapes.LineSegment;
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
    private Point point1;
    private Point point2;
    private Color colorOut;
    CompletableFuture<Shape> shape=new CompletableFuture<>();
    public LineData() {
        setContentPane(panel1);
        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(400, 300);
        setColorButton.addActionListener(e -> {
            colorOut= JColorChooser.showDialog(null,"choose outline color",Color.black);

        });
        createLineButton.addActionListener(e -> {
            try {
                point1=new Point(Integer.parseInt(textField1.getText()),Integer.parseInt(textField2.getText()));
                point2=new Point(Integer.parseInt(textField3.getText()),Integer.parseInt(textField4.getText()));
                LineSegment lineSegment=new LineSegment(point1,point2);
                lineSegment.setColor(colorOut);
                ((JFrame)this.getParentNode()).setVisible(true);
               this.setVisible(false);
                shape.complete(lineSegment);
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
    public CompletableFuture<Shape> end(){
        return shape;
    }
}
