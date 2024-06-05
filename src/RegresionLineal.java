import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class RegresionLineal extends JFrame {
    private JTextField[] xFields;
    private JTextField[] yFields;
    private JTextField predictField;
    private JLabel slopeLabel;
    private JLabel interceptLabel;
    private JLabel predictionLabel;
    private JTable resultsTable;
    private DefaultTableModel tableModel;

    public RegresionLineal() {
        setTitle("Regresión Lineal");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(0, 2));
        add(inputPanel, BorderLayout.NORTH);

        JLabel numPointsLabel = new JLabel("Número de puntos de datos:");
        inputPanel.add(numPointsLabel);

        JTextField numPointsField = new JTextField();
        inputPanel.add(numPointsField);

        JButton setPointsButton = new JButton("Establecer puntos");
        inputPanel.add(setPointsButton);

        JPanel pointsPanel = new JPanel(new GridLayout(0, 2));
        add(pointsPanel, BorderLayout.CENTER);

        setPointsButton.addActionListener(e -> {
            int numPoints = Integer.parseInt(numPointsField.getText());
            pointsPanel.removeAll();
            pointsPanel.setLayout(new GridLayout(numPoints + 1, 2));
            xFields = new JTextField[numPoints];
            yFields = new JTextField[numPoints];

            pointsPanel.add(new JLabel("Valores de X:"));
            pointsPanel.add(new JLabel("Valores de Y:"));

            for (int i = 0; i < numPoints; i++) {
                xFields[i] = new JTextField();
                yFields[i] = new JTextField();
                pointsPanel.add(xFields[i]);
                pointsPanel.add(yFields[i]);
            }

            revalidate();
            repaint();
        });

        JPanel actionPanel = new JPanel();
        add(actionPanel, BorderLayout.SOUTH);

        JButton fitButton = new JButton("Ajustar");
        actionPanel.add(fitButton);

        slopeLabel = new JLabel("Pendiente: ");
        actionPanel.add(slopeLabel);

        interceptLabel = new JLabel("Intercepto: ");
        actionPanel.add(interceptLabel);

        JLabel predictLabel = new JLabel("Predecir para x: ");
        actionPanel.add(predictLabel);

        predictField = new JTextField(5);
        actionPanel.add(predictField);

        JButton predictButton = new JButton("Predecir");
        actionPanel.add(predictButton);

        predictionLabel = new JLabel("Predicción: ");
        actionPanel.add(predictionLabel);

        tableModel = new DefaultTableModel(new String[]{"X", "Y"}, 0);
        resultsTable = new JTable(tableModel);
        add(new JScrollPane(resultsTable), BorderLayout.EAST);

        fitButton.addActionListener(new FitButtonListener());
        predictButton.addActionListener(new PredictButtonListener());
        setVisible(true);
    }

    private class FitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int numPoints = xFields.length;
            double[] x = new double[numPoints];
            double[] y = new double[numPoints];

            for (int i = 0; i < numPoints; i++) {
                x[i] = Double.parseDouble(xFields[i].getText());
                y[i] = Double.parseDouble(yFields[i].getText());
            }

            LinearRegression lr = new LinearRegression(x, y);
            lr.fit();

            slopeLabel.setText("Pendiente: " + lr.getSlope());
            interceptLabel.setText("Intercepto: " + lr.getIntercept());

            tableModel.setRowCount(0);
            for (int i = 0; i < numPoints; i++) {
                tableModel.addRow(new Object[]{x[i], y[i]});
            }
        }
    }

    private class PredictButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double xValue = Double.parseDouble(predictField.getText());

            int numPoints = xFields.length;
            double[] x = new double[numPoints];
            double[] y = new double[numPoints];

            for (int i = 0; i < numPoints; i++) {
                x[i] = Double.parseDouble(xFields[i].getText());
                y[i] = Double.parseDouble(yFields[i].getText());
            }

            LinearRegression lr = new LinearRegression(x, y);
            lr.fit();

            double prediction = lr.predict(xValue);
            predictionLabel.setText("Predicción: " + prediction);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RegresionLineal app = new RegresionLineal();
            app.setVisible(true);
        });
    }
}

class LinearRegression {
    private final double[] x;
    private final double[] y;
    private final int n;
    private double slope;
    private double intercept;
    private boolean fitted;

    public LinearRegression(double[] x, double[] y) {
        if (x.length != y.length) {
            throw new IllegalArgumentException("Las longitudes de x y y deben ser iguales.");
        }
        this.x = x;
        this.y = y;
        this.n = x.length;
        this.fitted = false;
    }

    public void fit() {
        double sumX = Arrays.stream(x).sum();
        double sumY = Arrays.stream(y).sum();
        double sumX2 = Arrays.stream(x).map(val -> val * val).sum();
        double sumXY = 0.0;

        for (int i = 0; i < n; i++) {
            sumXY += x[i] * y[i];
        }

        double denominator = (n * sumX2 - sumX * sumX);
        if (denominator == 0) {
            throw new ArithmeticException("El denominador en el cálculo de la pendiente es cero. No se puede ajustar el modelo.");
        }

        slope = (n * sumXY - sumX * sumY) / denominator;
        intercept = (sumY - slope * sumX) / n;
        fitted = true;
    }

    public double predict(double xValue) {
        if (!fitted) {
            throw new IllegalStateException("El modelo no está ajustado. Llame a fit() antes de hacer predicciones.");
        }
        return intercept + slope * xValue;
    }

    public double getSlope() {
        if (!fitted) {
            throw new IllegalStateException("El modelo no está ajustado. Llame a fit() para calcular la pendiente.");
        }
        return slope;
    }

    public double getIntercept() {
        if (!fitted) {
            throw new IllegalStateException("El modelo no está ajustado. Llame a fit() para calcular el intercepto.");
        }
        return intercept;
    }
}
