package frontend;

import shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CircleData extends JFrame implements Node{
    private JButton setColorButton;
    private JButton setFillColorButton;
    private JButton createCircleButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private Node parent;


    public CircleData(ArrayList<Integer>values, Shape shape) {
        createCircleButton.addActionListener(e -> {});
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
}
