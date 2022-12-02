package frontend;

import shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private Node parent;


    public TriangleData(ArrayList<Integer>values, Shape shape) {
        setColorButton.addActionListener(e -> {
            Color color = JColorChooser.showDialog(null,"choose outline color",Color.black);
            shape.setColor(color);
        });
        setFillColorButton.addActionListener(e -> {
            Color color = JColorChooser.showDialog(null,"choose fill color ",Color.black);
            shape.setFillColor(color);
        });
        createTriangleButton.addActionListener(e -> {});
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
