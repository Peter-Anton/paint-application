package frontend;

import shapes.Shape;
import shapes.Triangle;

import javax.swing.*;
import java.awt.*;
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
    private JButton cancelButton;
    private Node parent;
    private Point point1;
    private Point point2;
    private Point point3;
    private Color colorOut;
    private Color colorFill;

    CompletableFuture<Shape> shape=new CompletableFuture<>();
    public TriangleData() {
        setContentPane(panel1);
        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(400, 300);
        setColorButton.addActionListener(e -> {
            colorOut = JColorChooser.showDialog(null,"choose outline color",Color.black);
        });
        setFillColorButton.addActionListener(e -> {
             colorFill = JColorChooser.showDialog(null,"choose fill color ",Color.black);
        });
        createTriangleButton.addActionListener(e -> {
            try {
                point1=new Point(Integer.parseInt(textField1.getText()),Integer.parseInt(textField2.getText()));
                point2=new Point(Integer.parseInt(textField3.getText()),Integer.parseInt(textField4.getText()));
                point3=new Point(Integer.parseInt(textField5.getText()),Integer.parseInt(textField6.getText()));
                Triangle triangle=new Triangle(point1,point2,point3);
                triangle.setColor(colorOut);
                triangle.setFillColor(colorFill);
            ((JFrame)this.getParentNode()).setVisible(true);
               this.setVisible(false);
                shape.complete(triangle);
                dispose();
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
        return shape;
    }
}
