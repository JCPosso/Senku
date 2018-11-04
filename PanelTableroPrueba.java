package presentacion;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class PanelTableroPrueba extends JFrame{
	
	
	private final int FICHA_SIZE=15;
	private final int BORDE_FICHA=8;
	private final Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
	private	int Ancho=screenSize.width/2;
	private	int Alto=screenSize.height/2;
	private char[][] tablero;
	private void setTablero(char[][] tableroNuevo){
		tablero=tableroNuevo;
	}
	
	private PanelTableroPrueba(){
		prepareElementos();
		prepareAcciones();
	}
	private void prepareElementos(){
		setTitle("SenkuJuego");
		setLocation((screenSize.width-Ancho)/2,(screenSize.height-Alto)/2);
		setSize(new Dimension(Ancho,Alto));
	}
	public void prepareAcciones(){
		
	}
	 @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (tablero != null){

            int posX = (int)(Ancho*0.1)+BORDE_FICHA;
            int posY = (int)(Alto*0.1)+BORDE_FICHA;
            int size = tablero.length;

            for (int y = 0; y < size; y++) {

                for (int x = 0; x < size; x++) {

                    if (tablero[y][x] == '+') {
                        pintaCirculo(g, posX, posY, FICHA_SIZE,  Color.red);
                    } else if (tablero[y][x] == 'x') {
                        pintaCirculo(g, posX, posY, FICHA_SIZE, Color.yellow);
                    } else if (tablero[y][x] == 'o') {
                        pintaCirculoOtro(g, posX, posY, FICHA_SIZE,Color.blue);
                    }

                    posX = posX + (FICHA_SIZE * 2) + BORDE_FICHA;
                }
                posX = (int)(Ancho*0.1)+BORDE_FICHA;
                posY = posY + (FICHA_SIZE * 2) + BORDE_FICHA;
            }
        }
    }
    
    private void pintaCirculoOtro(Graphics g, int posX, int posY, int radio, Color color){
        int size = radio * 2;
        g.setColor(color);        
        g.fillOval(posX, posY, size, size);
    }
    private void pintaCirculo(Graphics g, int posX, int posY, int radio, Color color){

	
        int size = radio * 2;

        for (int x=size; x > 1; x=x-2){
            g.setColor(color);
            g.fillOval(posX, posY, x, x);
            if ((x % 4) == 0.0){
                posY++;
                posX++;
            }
        }
    }
	public static void main(String args[]){
	PanelTableroPrueba panel=new PanelTableroPrueba();
	panel.setTablero(new char[][] { {'o','o','o','+','+','+','o','o','o',},
									{'o','o','o','+','+','+','o','o','o'},
									{'o','o','o','+','+','+','o','o','o'},
									
									{'+','+','+','+','+','+','+','+','+'},
									{'+','+','+','+','x','+','+','+','+'},
									{'+','+','+','+','+','+','+','+','+'},
									
									{'o','o','o','+','+','+','o','o','o'},
									{'o','o','o','+','+','+','o','o','o'},
									{'o','o','o','+','+','+','o','o','o'},
	}
	);
	panel.setVisible(true);
	}
}
