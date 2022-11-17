package frontend;

import backend.DrawingEngineBase;
import backend.Shape;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class ColorChoose extends JFrame implements Node {
    Graphics canvas;
    DrawingEngineBase engine;
    java.awt.Color color;
    Shape shape;
    private JPanel panel1;
    private JButton outlineColorButton;
    private JButton fillColorButton;
    private JCheckBox outlinecolrCheckBox;
    private JCheckBox fillColorCheckBox;
    private JButton setColorButton;
    private JPanel fillColorPanel;
    private JPanel outlinesColorpanel;
    private Node parent;
    HashMap<String,Double> properties;
    public ColorChoose(DrawingEngineBase engine, Shape shape, Graphics canvas,HashMap<String,Double> properties) {
        System.out.println(panel1);
        setContentPane(panel1);
        setVisible(true);
        setSize(400, 300);
        setTitle("Drawing Application");
        this.canvas = canvas;
        this.engine = engine;
        this.shape=shape;
        this.properties=properties;
        fillColorButton.addActionListener(e -> {
            if (fillColorCheckBox.isSelected()){
            color = java.awt.Color.BLACK;
            color = JColorChooser.showDialog(this,"Select a Color",color);
            if(color == null)
            {
                color = java.awt.Color.BLACK;
                fillColorPanel.setBackground(color);
                shape.setFillColor(color);
                properties.put("fill colorize",2.0);
                shape.setProperties(properties);
            }
            fillColorPanel.setBackground(color);
            shape.setFillColor(color);
            return;
            }
            fillColorPanel.setBackground(Color.getColor("ABB2BF"));
            shape.setFillColor(Color.getColor("ABB2BF"));

        });
        setColorButton.addActionListener(e -> {
            ((JFrame) ColorChoose.this.getParentNode()).setVisible(true);
            ColorChoose.this.setVisible(false);
            engine.refresh(canvas);

        });
        outlineColorButton.addActionListener(e -> {
            if (outlinecolrCheckBox.isSelected()){
            color = java.awt.Color.BLACK;
            color = JColorChooser.showDialog(this,"Select a Color",color);
            if(color == null)
            {
                color = java.awt.Color.BLACK;
                outlinesColorpanel.setBackground(color);
                shape.setColor(color);
                properties.put("border colorize",1.0);
                shape.setProperties(properties);
            }
            outlinesColorpanel.setBackground(color);
            System.out.println(shape == null);
            return;
            }
            outlinesColorpanel.setBackground(Color.BLACK);
            shape.setColor(Color.BLACK);
        });

    }
public Graphics returnCanavas(){
        return this.canvas;
}
    @Override
    public Node getParentNode() {
        return parent;
    }

    @Override
    public void setParent(Node node) {
        this.parent=node;

    }
}
