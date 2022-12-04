package frontend;

import shapes.*;
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
    Shape selectedShape;

    public Paint() {
        setContentPane(panel1);
        setVisible(true);
        setSize(1000, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Drawing Application");
        drawingEngine = new DrawingEngineBase();
        canavas.add(drawingEngine);
        drawingEngine.setBackground(Color.white);
        drawingEngine.addMouseListener(new Click());
        drawingEngine.addMouseMotionListener(new Drag());
        comboBox1.addItemListener(event -> {
            if (event.getStateChange() != ItemEvent.SELECTED || !comboBox1.hasFocus()) {
                return;
            }
            String shapeKey = (String) event.getItem();
            Shape selectShape = null;
            for (Shape shape : drawingEngine.getShapes()) {
                if(((ShapeBase)shape).getName_key().equals(shapeKey)) {
                    selectShape = shape;
                    break;
                }
            }
            this.selectedShape=selectShape;
        });

        linesegmentButton.addActionListener(e -> {
            values = new ArrayList<>();
            LineData lineData=new LineData();
            setVisible(false);
            lineData.setParent(this);
            lineData.setVisible(true);
            lineData.end().whenComplete((Shape shape,Object o)->{
                ((ShapeBase)shape).generateKey();
                drawingEngine.addShape(shape);
                updateCombo(shape);
                drawingEngine.refresh();
            });
            });

        triangleButton.addActionListener(e -> {
            values = new ArrayList<>();
            TriangleData triangleData=new TriangleData();
            setVisible(false);
            triangleData.setParent(this);
            triangleData.setVisible(true);
            triangleData.end().whenComplete((Shape shape,Object o)->{
                ((ShapeBase)shape).generateKey();
                updateCombo(shape);
                drawingEngine.addShape(shape);
                drawingEngine.refresh();

            });
            triangleData.setVisible(true);

        });

        circleButton.addActionListener(e -> {
            values = new ArrayList<>();
            CircleData circleData=new CircleData();
            setVisible(false);circleData.setParent(this);circleData.setVisible(true);
            circleData.end().whenComplete((Shape shape,Object o)->{
                ((ShapeBase)shape).generateKey();
                drawingEngine.addShape(shape);
                updateCombo(shape);
                drawingEngine.refresh();
            });
        });

        rectangleButton.addActionListener(e -> {
            values = new ArrayList<>();
            RectangleData rectangleData=new RectangleData();
            setVisible(false);
            rectangleData.setParent(this);
            rectangleData.setVisible(true);
            rectangleData.end().whenComplete((Shape shape,Object o)->{
                ((ShapeBase)shape).generateKey();
                drawingEngine.addShape(shape);
                updateCombo(shape);
                drawingEngine.refresh();
            });

        });

        deleteButton.addActionListener(e -> {
            Shape shape = searchShape(String.valueOf(comboBox1.getSelectedItem()));
            if (shape != null) {
                drawingEngine.removeShape(shape);
                drawingEngine.refresh();
                comboBox1.removeItem(comboBox1.getSelectedItem());
            }
        });

        colorizeButton.addActionListener(e -> {
            Shape shapeColor = searchShape((String) comboBox1.getSelectedItem());
            if (shapeColor != null) {
                ColorChoose colorChoose1=new ColorChoose(drawingEngine,shapeColor);
                colorChoose1.setParent(this);
                colorChoose1.setTitle("Colorize "+comboBox1.getSelectedItem());
                    drawingEngine.refresh();
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
                drawingEngine.refresh();
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                drawingEngine.refresh();
            }
        });
        panel1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                        drawingEngine.refresh();
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
        @Override
        public void mousePressed(MouseEvent e) {
            selectedShape=null;
            for (Shape shape : drawingEngine.getShapes()) {
                if (shape.contains(e.getPoint())) {
                    comboBox1.setSelectedItem(((ShapeBase)shape).getName_key());
                    selectedShape=shape;
                    selectedShape.setDraggingPoint(e.getPoint());
                }
            }
        }
    }
    private  class Drag extends MouseMotionAdapter{
        @Override
        public void mouseDragged(MouseEvent e)
        {
            if (selectedShape==null)
                return;
            selectedShape.moveTo(e.getPoint());
                selectedShape.setDraggingPoint(e.getPoint());
                drawingEngine.refresh();
        }
    }
}
