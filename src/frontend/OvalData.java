package frontend;

import shapes.Oval;

import shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CompletableFuture;

public class OvalData extends JFrame implements Node{
    private JButton setColorButton;
    private JButton setFillColorButton;
    private JButton createCircleButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JPanel panel1;
    private JButton cancelButton;
    private JTextField textField4;
    private Node parent;
    private Point point;
    private Color colorOut;
    private Color colorFill;
    private int height;
    private int width;
    CompletableFuture<Shape> shape=new CompletableFuture<>();
    public OvalData() {
        setContentPane(panel1);
        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(400, 300);
        JRootPane rootPane=SwingUtilities.getRootPane(panel1);
        rootPane.setDefaultButton(createCircleButton);
        createCircleButton.addActionListener(e -> {
            try {
                point=new Point(Integer.parseInt(textField1.getText()),Integer.parseInt(textField2.getText()));
                height=Integer.parseInt(textField3.getText());
                width=Integer.parseInt(textField4.getText());
                if (width<=0||height<=0)
                    throw new NumberFormatException();
                Oval circle=new Oval(point,height,width);
                circle.setColor(colorOut);
                circle.setFillColor(colorFill);
                ((JFrame)this.getParentNode()).setVisible(true);
                this.setVisible(false);
                shape.complete(circle);
                            }catch (Exception ex){
                JOptionPane.showMessageDialog(null,"enter positive valid data");
            }

        });
        setColorButton.addActionListener(e -> {
            colorOut = JColorChooser.showDialog(this,"choose outline color",Color.black);
        });
        setFillColorButton.addActionListener(e -> {
            colorFill = JColorChooser.showDialog(this,"choose fill color ",null);
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
