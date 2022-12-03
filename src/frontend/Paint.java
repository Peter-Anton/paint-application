package frontend;

import shapes.*;
import shapes.Rectangle;
import shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

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
            AtomicReference<LineSegment> lineSegment = new AtomicReference<>(new LineSegment());
            values = new ArrayList<>();
            LineData lineData=new LineData(values, lineSegment.get());
            lineData.end().whenComplete((Boolean wait,Object o)->{
                lineSegment.set(new LineSegment(new Point(values.get(0), values.get(1)), new Point(values.get(2), values.get(3)), "line_segment"));
                lineSegment.get().generateKey();
                drawingEngine.addShape(lineSegment.get());
                updateCombo(lineSegment.get());
                drawingEngine.refresh(canavas.getGraphics());
            });
            });

        triangleButton.addActionListener(e -> {
            AtomicReference<Triangle> triangle = new AtomicReference<>(new Triangle());
            values = new ArrayList<>();
            TriangleData triangleData=new TriangleData(values, triangle.get());
//            setVisible(false);
//            triangleData.setParent(this);
//            triangleData.setVisible(true);
            triangleData.end().whenComplete((Boolean wait,Object o)->{
                triangle.set(new Triangle(new Point(values.get(0), values.get(1)), new Point(values.get(2), values.get(3)), new Point(values.get(4), values.get(5)), "triangle"));
                triangle.get().generateKey();
                updateCombo(triangle.get());
                drawingEngine.addShape(triangle.get());
                drawingEngine.refresh(canavas.getGraphics());});
            triangleData.setVisible(true);

        });

        circleButton.addActionListener(e -> {
            AtomicReference<Circle> circle = new AtomicReference<>(new Circle());
            values = new ArrayList<>();
            CircleData circleData=new CircleData(values, circle.get());
//            setVisible(false);
//            circleData.setParent(this);
//            circleData.setVisible(true);
            circleData.end().whenComplete((Boolean wait,Object o)->{
                circle.set(new Circle(new Point(values.get(0), values.get(1)), values.get(2), "circle"));
                circle.get().generateKey();
                drawingEngine.addShape(circle.get());
                updateCombo(circle.get());
                drawingEngine.refresh(canavas.getGraphics());
            });
        });

        rectangleButton.addActionListener(e -> {
            AtomicReference<Rectangle> rectangle = new AtomicReference<>(new Rectangle());
            values = new ArrayList<>();
            RectangleData rectangleData=new RectangleData(values, rectangle.get());
//            setVisible(false);
//            rectangleData.setParent(this);
//            rectangleData.setVisible(true);
            rectangleData.end().whenComplete((Boolean wait,Object o)->{
                rectangle.set(new Rectangle(new Point(values.get(0), values.get(1)), values.get(2), values.get(3), "rectangle"));
                rectangle.get().generateKey();
                drawingEngine.addShape(rectangle.get());
                updateCombo(rectangle.get());
                drawingEngine.refresh(canavas.getGraphics());
            });

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
    private  class Click extends MouseAdapter {
        //todo:select
        @Override
        public void mousePressed(MouseEvent e) {
            for (Shape shape : drawingEngine.getShapes()) {
                if (shape.contains(e.getPoint())) {
                    comboBox1.setSelectedItem(((ShapeBase)shape).getName_key());
                }
            }
        }
    }
    private  class Drag extends MouseMotionAdapter{
        @Override
        public void mouseDragged(MouseEvent e)
        {
                Shape selectedShape=searchShape(comboBox1.getSelectedItem().toString());
                if (selectedShape==null)
                    return;

                selectedShape.moveTo(e.getPoint());
                selectedShape.setDraggingPoint(e.getPoint());
                drawingEngine.refresh(canavas.getGraphics());

        }
    }




}
