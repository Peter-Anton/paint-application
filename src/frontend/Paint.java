package frontend;

import backend.*;
import backend.Rectangle;
import backend.Shape;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Paint extends JFrame implements Node {
    private JButton linesegmentButton;
    private JPanel panel1;
    private JPanel canavas;
    private JButton circleButton;
    private JButton rectangleButton;
    private JButton squareButton;
    private JButton colorizeButton;
    private JButton deleteButton;
    private JComboBox<String> comboBox1;
    ArrayList<Integer> values;
    DrawingEngineBase drawingEngine;
    Random random = new Random();
    ColorChoose colorChoose;
    java.awt.Color color;
    public Paint() {
        setContentPane(panel1);
        setVisible(true);
        setSize(850, 850);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Drawing Application");

        drawingEngine = new DrawingEngineBase();

        linesegmentButton.addActionListener(e -> {
            LineSegment lineSegment = new LineSegment();
            String[] data = lineSegment.data();
            int i = 0;
            values = new ArrayList<>();
            while (i < data.length) {
                try {
                    values.add(Integer.parseInt(JOptionPane.showInputDialog("please enter " + data[i] + ":")));
                    i++;
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "enter a valid number");
                }
            }
            lineSegment = new LineSegment(values.get(0), values.get(1), values.get(2), values.get(3), "line_segment");
            lineSegment.generateKey();
            drawingEngine.addShape(lineSegment);
            updateCombo(lineSegment);
            drawingEngine.refresh(canavas.getGraphics());
        });

        squareButton.addActionListener(e -> {
            Rectangle square = new Rectangle();
            String[] data = square.squareData();
            int i = 0;
            values = new ArrayList<>();
            while (i < data.length) {
                try {
                    values.add(Integer.parseInt(JOptionPane.showInputDialog("please enter " + data[i] + " of the square:")));
                    i++;
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "enter a valid number");
                }
            }
            square = new Rectangle(values.get(0), values.get(1), values.get(2), values.get(2), "square");
            square.generateKey();
            updateCombo(square);
            drawingEngine.addShape(square);
            drawingEngine.refresh(canavas.getGraphics());
        });

        circleButton.addActionListener(e -> {
            Circle circle = new Circle();
            String[] data = circle.data();
            int i = 0;
            values = new ArrayList<>();
            while (i < data.length) {
                try {
                    values.add(Integer.parseInt(JOptionPane.showInputDialog("please enter " + data[i] + ":")));
                    i++;
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "enter a valid number");
                }

            }

            circle = new Circle(values.get(0), values.get(1), values.get(2), "circle");
            circle.generateKey();
            drawingEngine.addShape(circle);
            updateCombo(circle);
            drawingEngine.refresh(canavas.getGraphics());
        });

        rectangleButton.addActionListener(e -> {
            Rectangle rectangle = new Rectangle();
            String[] data = rectangle.data();
            int i = 0;
            values = new ArrayList<>();
            while (i < data.length) {
                try {
                    values.add(Integer.parseInt(JOptionPane.showInputDialog("please enter " + data[i] + ":")));
                    i++;
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "enter a valid number");
                }
            }
            rectangle = new Rectangle(values.get(0), values.get(1), values.get(2), values.get(3), "rectangle");
            rectangle.generateKey();
            drawingEngine.addShape(rectangle);
            updateCombo(rectangle);
            drawingEngine.refresh(canavas.getGraphics());
        });

        deleteButton.addActionListener(e -> {
            Shape shape = searchShape(String.valueOf(comboBox1.getSelectedItem()));
            if (shape != null) {
                drawingEngine.removeShape(shape);
                drawingEngine.refresh(canavas.getGraphics());
                comboBox1.removeItem(comboBox1.getSelectedItem());
            }
        });

        colorizeButton.addActionListener(e -> {
            Shape shapeColor = searchShape((String) comboBox1.getSelectedItem());
            if (shapeColor != null) {
                ColorChoose colorChoose1=new ColorChoose(drawingEngine,shapeColor,canavas.getGraphics());
                colorChoose1.setParent(this);
                drawingEngine.refresh(canavas.getGraphics());
//                Color BorderChoser=JColorChooser.showDialog(this,"borde chooser",color);
//                shapeColor.setColor(BorderChoser);
//                System.out.println("Border choser"+BorderChoser);
//                System.out.println("border color:"+shapeColor.getColor());
//                Color FillChoser=JColorChooser.showDialog(this,"fill chooser",color);
//                shapeColor.setFillColor(FillChoser);
//                System.out.println("fill chooser"+FillChoser);
//                System.out.println("fill color:"+shapeColor.getFillColor());
//                drawingEngine.refresh(canavas.getGraphics());
            }


        });
    }

    private void updateCombo(Shape shape) {
        comboBox1.addItem(shape.getName_key());
    }

    private Shape searchShape(String Key) {
        Shape[] shapes = drawingEngine.getShapes();
        for (Shape shap : shapes) {
            if (Key.equals(shap.getName_key())) {
                return shap;
            }

        }
        return null;
    }

    @Override
    public Node getParentNode() {
        return null;
    }

    @Override
    public void setParent(Node node) {

    }
}
