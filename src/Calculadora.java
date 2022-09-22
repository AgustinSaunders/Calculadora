//
//Saunders Agustin
//
//Agosto 2022

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Calculadora {

    private double total = 0.0;
    private double total2 = 0.0;
    private int operacion = 1;
    private JPanel Calculadora;
    private JTextField textField1;
    private JButton igualdad;
    private JButton suma;
    private JButton resta;
    private JButton multiplicacion;
    private JButton division;
    private JButton borrar;
    private JButton cero;
    private JButton uno;
    private JButton dos;
    private JButton tres;
    private JButton cuatro;
    private JButton cinco;
    private JButton seis;
    private JButton siete;
    private JButton ocho;
    private JButton nueve;
    private JButton e;
    private JButton pi;
    private JButton punto;

    public Calculadora() {
        //no se pueden añadir numeros inmediatamente despues de un "="
        List<JButton> botonesNumericos = new ArrayList<JButton>();
        Collections.addAll(botonesNumericos, uno, dos, tres, cuatro, cinco,
                                            seis, siete, ocho, nueve, cero);

        for (JButton boton:botonesNumericos) {
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (operacion != 5) {
                        textField1.setText(textField1.getText() + boton.getText());
                    }
                }
            });
        }

        // Pi y e solo funcionan si el cuadro de texto esta vacio
        // esto es para evitar complicaciones con el uso de los decimales
        pi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().length() == 0) {
                    textField1.setText("3.1416");

                }
            }
        });
        e.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().length() == 0) {
                    textField1.setText("2.7183");
                }
            }
        });
        punto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char charPunto = '.';
                int count = 0;
                boolean inicial = false;

                for (int i = 0; i < textField1.getText().length(); i++) {
                    if (textField1.getText().charAt(i) == charPunto) {
                        count++;
                    }
                }
                if (textField1.getText().length() == 0) {
                    inicial = true;
                }
                if (count == 0 && !inicial) {
                    String puntoText = textField1.getText() + punto.getText();
                    textField1.setText(puntoText);
                }
                if (inicial) {
                    textField1.setText("0.");
                }
            }
        });
        borrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText(null);
                operacion = 1;
                total = total2 = 0;
            }
        });

        List<JButton> botonesOperaciones = new ArrayList<JButton>();
        Collections.addAll(botonesOperaciones, suma, resta, multiplicacion, division, igualdad);

        for (int i = 0; i < 5; i++) {
            configurarListenerOperaciones(botonesOperaciones.get(i), i+1);
        }
    }

    private void configurarListenerOperaciones(JButton boton, int numeroOperacion) {
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                switch (operacion) {
                    case 1:
                        total = total + Double.parseDouble(textField1.getText());
                        break;
                    case 2:
                        total = total - Double.parseDouble(textField1.getText());
                        break;
                    case 3:
                        total = total * Double.parseDouble(textField1.getText());
                        break;
                    case 4:
                        total = total / Double.parseDouble(textField1.getText());
                        break;
                    case 5:
                        total = total2;
                }
                operacion = numeroOperacion;
                if (numeroOperacion==5) {
                    total2 = total;
                    textField1.setText(String.valueOf(total2));
                } else {
                    textField1.setText(null);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculadora");
        frame.setContentPane(new Calculadora().Calculadora);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(350, 450);
        frame.setVisible(true);
    }
}