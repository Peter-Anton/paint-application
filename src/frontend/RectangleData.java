package frontend;

import shapes.Rectangle;
import shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class RectangleData extends JFrame implements Node {
    private JButton setColorButton;
    private JButton setFillColorButton;
    private JButton createRectangleButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JPanel panel1;
    private JButton cancelButton;
    private Node parent;
    private int height;
    private int width;
    private Point point=new Point();
    Color colorOut;
    Color colorFill;
    CompletableFuture<Shape> wait=new CompletableFuture<>();
    public RectangleData() {
        setContentPane(panel1);
        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(400, 300);
        setColorButton.addActionListener(e -> {
             colorOut = JColorChooser.showDialog(this,"choose outline color",Color.black);
        });
        setFillColorButton.addActionListener(e -> {
             colorFill = JColorChooser.showDialog(this,"choose fill color ",Color.black);
        });
        createRectangleButton.addActionListener(e -> {
            try {
                point.x=Integer.parseInt(textField1.getText());
                point.y=Integer.parseInt(textField2.getText());
                width=Integer.parseInt(textField3.getText());
                height=Integer.parseInt(textField4.getText());
                ((JFrame)this.getParentNode()).setVisible(true);
                Rectangle rectangle=new Rectangle(point,width,height);
                rectangle.setColor(colorOut);
                rectangle.setFillColor(colorFill);
            this.setVisible(false);
                wait.complete(rectangle);
            }catch (Exception ex){
                JOptionPane.showMessageDialog(this,"enter data");
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
        return wait;
    }
}
