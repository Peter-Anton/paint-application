package frontend;

import backend.DrawingEngineBase;
import backend.Shape;
import javax.swing.*;
import java.awt.*;
public class ColorChoose extends JFrame  {
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
    public ColorChoose(DrawingEngineBase engine, Shape shape, Graphics canvas) {
        setContentPane(panel1);
        setVisible(true);
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("color");

        this.canvas = canvas;
        this.engine = engine;
        this.shape=shape;

        setColorButton.addActionListener(e -> {
        });
        fillColorCheckBox.addActionListener(e1 -> {

            fillColorButton.addActionListener(e -> {
                color = java.awt.Color.BLACK;
                color = JColorChooser.showDialog(this,"Select a Color",color);
                if(color == null)
                {
                    color = Color.white;
                    fillColorPanel.setBackground(color);
                    shape.setFillColor(color);
                }
                fillColorPanel.setBackground(color);
                shape.setFillColor(color);
            });

        });
        outlinecolrCheckBox.addActionListener(e1 -> {
            outlineColorButton.addActionListener(e -> {
                color = Color.BLACK;
                color = JColorChooser.showDialog(this,"Select a Color",color);
                if(color == null)
                {
                    color = java.awt.Color.BLACK;
                    outlinesColorpanel.setBackground(color);
                    shape.setColor(color);
                }
                outlinesColorpanel.setBackground(color);
                System.out.println(shape == null);
            });

        });
    }

}
