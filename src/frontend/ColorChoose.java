package frontend;

import backend.DrawingEngineBase;
import backend.Shape;
import javax.swing.*;
import java.awt.*;
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
    private JButton cancelButton;
    private Node parent;
    public ColorChoose(DrawingEngineBase engine, Shape shape, Graphics canvas) {
        System.out.println(panel1);

        setContentPane(panel1);
        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(400, 300);
        setTitle("Drawing Application");
        this.canvas = canvas;
        this.engine = engine;
        this.shape=shape;
        fillColorButton.addActionListener(e -> {
            if (fillColorCheckBox.isSelected()){
            color = Color.BLACK;
            color = JColorChooser.showDialog(this,"Select a Color",color);
            fillColorPanel.setBackground(color);
            shape.setFillColor(color);
            return;
            }
            fillColorPanel.setBackground(null);
            shape.setFillColor(null);

        });

        setColorButton.addActionListener(e -> {
            engine.refresh(canvas);
            ((JFrame) ColorChoose.this.getParentNode()).setVisible(true);
            ColorChoose.this.setVisible(false);

        });

        outlineColorButton.addActionListener(e -> {
            if (outlinecolrCheckBox.isSelected()){
            color = java.awt.Color.BLACK;
            color = JColorChooser.showDialog(this,"Select a Color",color);
            outlinesColorpanel.setBackground(color);
            if (shape!=null)
            shape.setColor(color);
            return;
            }
            outlinesColorpanel.setBackground(Color.BLACK);
            shape.setColor(Color.BLACK);
        });

        cancelButton.addActionListener(e -> {

            ((JFrame) ColorChoose.this.getParentNode()).setVisible(true);
            ColorChoose.this.setVisible(false);
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
