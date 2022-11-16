package frontend;

import backend.*;
import backend.Rectangle;
import backend.Shape;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Paint extends JFrame{
    private JButton linesegmentButton;
    private JPanel panel1;
    private JPanel canavas;
    private JButton circleButton;
    private JButton rectangleButton;
    private JButton squareButton;
    private JButton colorizeButton;
    private JButton deleteButton;
    private JComboBox<String> comboBox1;
    ArrayList<Integer>values;
    DrawingEngineBase drawingEngine;
    Random random=new Random();
    public Paint()  {
        setContentPane(panel1);
        setVisible(true);
        setSize(850,850);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        drawingEngine=new DrawingEngineBase(canavas.getGraphics());

        linesegmentButton.addActionListener(e -> {
            LineSegment lineSegment  = new LineSegment();
            String[]data=lineSegment.data();
            int i=0;
            values=new ArrayList<>();
            while (i<data.length) {
                try {
                    values.add(Integer.parseInt(JOptionPane.showInputDialog("please enter " + data[i] + ":")));
                    i++;
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "enter a valid number");

                }
            }
            lineSegment = new LineSegment(values.get(0), values.get(1), values.get(2), values.get(3), "line_segment");
            drawingEngine.addShape(lineSegment);
            lineSegment.generateKey();
            updateCombo(lineSegment);
        });

        squareButton.addActionListener(e -> {
            Rectangle square=new Rectangle();
            String[]data=square.squareData();
            int i=0;
            values=new ArrayList<>();
            while (i<data.length){
                try {
                    values.add(Integer.parseInt(JOptionPane.showInputDialog("please enter "+data[i]+" of the square:" )));
                    i++;
                }catch (NumberFormatException e1){
                    JOptionPane.showMessageDialog(null,"enter a valid number");
                }
            }
            square=new Rectangle(values.get(0),values.get(1),values.get(2),values.get(2),"square");
            drawingEngine.addShape(square);
            square.generateKey();
            updateCombo(square);
        });

        circleButton.addActionListener(e -> {
            Circle circle=new Circle();
            String[]data=circle.data();
            int i=0;
            values=new ArrayList<>();
            while (i<data.length){
                try {
                    values.add(Integer.parseInt(JOptionPane.showInputDialog("please enter "+data[i]+ ":")));
                    i++;
                }catch (NumberFormatException e1){
                    JOptionPane.showMessageDialog(null,"enter a valid number");
                }

            }
            circle=new Circle(values.get(0),values.get(1),values.get(2),"circle");
            drawingEngine.addShape(circle);
            circle.generateKey();
            updateCombo(circle);
        });

        rectangleButton.addActionListener(e -> {
            Rectangle rectangle=new Rectangle();
            String[]data=rectangle.data();
            int i=0;
            values=new ArrayList<>();
            while (i<data.length){
                try {
                    values.add(Integer.parseInt(JOptionPane.showInputDialog("please enter "+data[i]+ ":")));
                    i++;
                }catch (NumberFormatException e1){
                    JOptionPane.showMessageDialog(null,"enter a valid number");
                }
            }
            rectangle=new Rectangle(values.get(0),values.get(1),values.get(2),values.get(3),"rectangle"
            );
            drawingEngine.addShape(rectangle);
            rectangle.generateKey();
            updateCombo(rectangle);

        });

        deleteButton.addActionListener(e -> {

            Shape shape=searchShape(String.valueOf(comboBox1.getSelectedItem()) );
            if (shape!=null)
            {
                drawingEngine.removeShape(shape);
                comboBox1.removeItem(comboBox1.getSelectedItem());
                drawingEngine.refresh(canavas.getGraphics());
            }

        });

        colorizeButton.addActionListener(e -> {
                    Shape shape=searchShape((String) comboBox1.getSelectedItem());

                    if (shape!=null)
                    {
                       shape.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
                       shape.setFillColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
                       drawingEngine.refresh();
                    }

        });
    }

    private void updateCombo(Shape shape){
        comboBox1.addItem(shape.getName_key());
    }
    private Shape searchShape(String Key){
        Shape[] shapes= drawingEngine.getShapes();
        for (Shape shap: shapes) {
            if (Key.equals(shap.getName_key()))
            {
                return shap;
            }

        }
        return null;
    }
}
