package frontend;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import shapes.Shape;
import shapes.ShapeBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class Paint extends JFrame implements Node, ActionListener{
    private JButton linesegmentButton;
    private JPanel panel1;
    private JPanel canavas;
    private JButton OvalButton;
    private JButton rectangleButton;
    private JButton triangleButton;
    private JButton colorizeButton;
    private JButton deleteButton;
    private JComboBox<String> comboBox1;
    private JButton copyButton;
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem saveItem;
    JMenuItem loadItem;
    JFileChooser fileChooser;
    DrawingEngineBase drawingEngine;
    protected Shape selectedShape;
    protected ShapeBase copyShape;
    Point resize;

    public Paint() {
        setContentPane(panel1);
        setVisible(true);
        setSize(1000, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Drawing Application");
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");

        saveItem = new JMenuItem("Save");
        loadItem = new JMenuItem("Load");

        loadItem.addActionListener(this);
        saveItem.addActionListener(this);
        loadItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,InputEvent.CTRL_MASK));
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));

        fileMenu.add(saveItem);
        fileMenu.add(loadItem);

        menuBar.add(fileMenu);

        this.setJMenuBar(menuBar);

        fileChooser = new JFileChooser();

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
                if(shape.toString().equals(shapeKey)) {
                    selectShape = shape;
                    break;
                }
            }
            this.selectedShape=selectShape;
        });


        linesegmentButton.addActionListener(e -> {
            LineData lineData=new LineData();
            setVisible(false);
            lineData.setParent(this);
            lineData.setVisible(true);
            lineData.end().whenComplete((Shape shape,Object o)->{
                shape.toString();
                drawingEngine.addShape(shape);
                updateCombo(shape);
                drawingEngine.refresh();
            });
            });

        triangleButton.addActionListener(e -> {
            TriangleData triangleData=new TriangleData();
            setVisible(false);
            triangleData.setParent(this);
            triangleData.setVisible(true);
            triangleData.end().whenComplete((Shape shape,Object o)->{
                updateCombo(shape);
                drawingEngine.addShape(shape);
                drawingEngine.refresh();

            });
            triangleData.setVisible(true);

        });

        OvalButton.addActionListener(e -> {
            OvalData circleData=new OvalData();
            setVisible(false);circleData.setParent(this);circleData.setVisible(true);
            circleData.end().whenComplete((Shape shape,Object o)->{
                drawingEngine.addShape(shape);
                updateCombo(shape);
                drawingEngine.refresh();
            });
        });

        rectangleButton.addActionListener(e -> {
            RectangleData rectangleData=new RectangleData();
            setVisible(false);
            rectangleData.setParent(this);
            rectangleData.setVisible(true);
            rectangleData.end().whenComplete((Shape shape,Object o)->{
                drawingEngine.addShape(shape);
                updateCombo(shape);
                drawingEngine.refresh();
            });

        });

        deleteButton.addActionListener(e -> {
            Shape shape = searchShape(String.valueOf(comboBox1.getSelectedItem()));
            if (shape != null) {
                drawingEngine.removeShape(shape);
                drawingEngine.setShape(null);
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

        copyButton.addActionListener(e -> {
            if (selectedShape!=null&&comboBox1.getItemCount()!=0){
                try {
                    copyShape = (ShapeBase) ((ShapeBase) selectedShape).clone();
                    copyShape.toString();
                    drawingEngine.addShape(copyShape);
                    updateCombo(copyShape);
                    drawingEngine.refresh();

                } catch (CloneNotSupportedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }


    private void updateCombo(Shape shape) {
        comboBox1.addItem(shape.toString());
    }

    private void saveJSONFile(File file)
    {
        JSONArray jsonShapes = new JSONArray();

        for (Shape shape : drawingEngine.getShapes())
        {
            JSONObject jsonObject =  ((ShapeBase)shape).shapeToJson();
            jsonShapes.add(jsonObject);
        }
        try(FileWriter fileWriter = new FileWriter(file))
        {
            fileWriter.write(jsonShapes.toJSONString());
            fileWriter.flush();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    private void loadJSONFile(File file)
    {
        System.out.println(file);
        JSONParser jsonShape = new JSONParser();
        try(FileReader reader = new FileReader(file))
        {
            //Read JSONFile
            Object object = jsonShape.parse(reader);
            JSONArray shapeList = (JSONArray) object;
            System.out.println("wasalt hena");
            System.out.println(shapeList);

            shapeList.forEach(Shape -> {
                shapes.Shape shapeObject2  = ShapeBase.parseShapeObject2((JSONObject) Shape);
                drawingEngine.addShape(shapeObject2);
                this.updateCombo(shapeObject2);
            });
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
    private Shape searchShape(String Key) {
        Shape[] shapes = drawingEngine.getShapes();
        for (Shape shap : shapes) {
            if (Key.equals(shap.toString())) {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loadItem)
        {
            System.out.println("loadItem");
            fileChooser.setCurrentDirectory(new File("Paint_GUi\\src\\jason file"));
            int response =   fileChooser.showOpenDialog( null); // select file to open
           if (response!=JFileChooser.CANCEL_OPTION){
            if (drawingEngine.getShapes()!=null) {
                for (Shape shape : drawingEngine.getShapes()) {
                    drawingEngine.removeShape(shape);
                }
                comboBox1.removeAllItems();
                drawingEngine.refresh();}
            }
            if(response == JFileChooser.APPROVE_OPTION)
            {
                fileChooser.setCurrentDirectory(new File("D\\"));
                loadJSONFile(fileChooser.getSelectedFile());
                drawingEngine.refresh();
            }
            drawingEngine.refresh();
        } else if (e.getSource() == saveItem) {
            System.out.println("saveItem");
            int response =   fileChooser.showSaveDialog( null); // select file to save
            fileChooser.setCurrentDirectory(new File("Paint_GUi\\src\\jason file"));
            if(response == JFileChooser.APPROVE_OPTION)
            {
                saveJSONFile(fileChooser.getSelectedFile());
            }



        }
    }

    private  class  Click extends MouseAdapter {


        @Override
        public void mouseReleased(MouseEvent e) {
            resize=null;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            selectedShape=null;
            drawingEngine.setShape(null);
            for (Shape shape : drawingEngine.getShapes()) {
                if (shape.contains(e.getPoint())) {
                    comboBox1.setSelectedItem(shape.toString());
                    selectedShape=shape;
                    drawingEngine.setShape(selectedShape);
                    selectedShape.setDraggingPoint(e.getPoint());
                }
            }
            drawingEngine.refresh();
              }
    }
    private  class Drag extends MouseMotionAdapter{
        @Override
        public void mouseDragged(MouseEvent e)
        {
            if (selectedShape==null)
                return;
            if (resize==null){
            selectedShape.moveTo(e.getPoint());
            selectedShape.setDraggingPoint(e.getPoint());
            drawingEngine.refresh();
            }
            for (Point point1: selectedShape.getPoint()) {
                if (point1.distance(e.getPoint())<=5)
                {
                    resize=point1;
                }
            }
            Point selectedPoint=selectedShape.resize(resize,e.getPoint());
            if (selectedPoint!=null)
                resize=selectedPoint;
            drawingEngine.refresh();
        }
    }

}
