package frontend;

import shapes.*;
import shapes.Rectangle;
import shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Paint extends JFrame implements Node{
    private JButton linesegmentButton;
    private JPanel panel1;
    private JPanel canavas;
    private JButton circleButton;
    private JButton rectangleButton;
    private JButton triangleButton;
    private JButton colorizeButton;
    private JButton deleteButton;
    private JComboBox<String> comboBox1;
    ArrayList<Integer> values;
    DrawingEngineBase drawingEngine;
    private  static Point selectedPoint;
    public Paint() {
        setContentPane(panel1);
        setVisible(true);
        setSize(1000, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Drawing Application");
        drawingEngine = new DrawingEngineBase();
        canavas.addMouseListener(new Click());
        canavas.addMouseMotionListener(new Drag());
        linesegmentButton.addActionListener(e -> {
            LineSegment lineSegment = new LineSegment();
            values = new ArrayList<>();
            new LineData(values,lineSegment);
            lineSegment = new LineSegment(new Point(values.get(0), values.get(1)), new Point(values.get(2), values.get(3)), "line_segment");
            lineSegment.generateKey();
            drawingEngine.addShape(lineSegment);
            updateCombo(lineSegment);
            drawingEngine.refresh(canavas.getGraphics());
        });

        triangleButton.addActionListener(e -> {
            Triangle triangle = new Triangle();
            values = new ArrayList<>();
            setVisible(false);
            TriangleData triangleData=new TriangleData(values,triangle);
            triangleData.setVisible(true);
            triangle = new Triangle(new Point(values.get(0), values.get(1)),new Point( values.get(2), values.get(3)),new Point(values.get(4),values.get(5)),"triangle");
            triangle.generateKey();
            updateCombo(triangle);
            drawingEngine.addShape(triangle);
            drawingEngine.refresh(canavas.getGraphics());
        });

        circleButton.addActionListener(e -> {
            Circle circle = new Circle();

            values = new ArrayList<>();
            CircleData circleData=new CircleData(values,circle);
            setVisible(false);
            circleData.setParent(this);
            setVisible(true);

         circle = new Circle(new Point(values.get(0), values.get(1)), values.get(2), "circle");
            circle.generateKey();
            drawingEngine.addShape(circle);
            updateCombo(circle);
            drawingEngine.refresh(canavas.getGraphics());
        });

        rectangleButton.addActionListener(e -> {
            Rectangle rectangle = new Rectangle();

            values = new ArrayList<>();
            RectangleData rectangleData=new RectangleData(values,rectangle);
            setVisible(false);
            rectangleData.setParent(this);
            rectangleData.setVisible(true);
            rectangle = new Rectangle(new Point(values.get(0), values.get(1)), values.get(2), values.get(3), "rectangle");
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
                colorChoose1.setTitle("Colorize "+comboBox1.getSelectedItem());
                colorChoose1.setParent(this);
                drawingEngine.refresh(canavas.getGraphics());
            }


        });

        addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {
                drawingEngine.refresh(canavas.getGraphics());
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                drawingEngine.refresh(canavas.getGraphics());
            }
        });
        panel1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                        drawingEngine.refresh(canavas.getGraphics());
                    }
        });

    }

    private void updateCombo(Shape shape) {
        comboBox1.addItem(((ShapeBase)shape).getName_key());
    }

    private Shape searchShape(String Key) {
        Shape[] shapes = drawingEngine.getShapes();
        for (Shape shap : shapes) {
            if (Key.equals(((ShapeBase)shap).getName_key())) {
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
    private static class Click extends MouseAdapter{
        //todo:select
        @Override
        public void mousePressed(MouseEvent e) {
            selectedPoint=e.getPoint();
        }
    }

    private static class Drag extends MouseMotionAdapter{
        @Override
        public void mouseDragged(MouseEvent e) {
            //Todo:drag
        }
    }




}
