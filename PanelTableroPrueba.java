package presentacion;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class PanelTableroPrueba extends JPanel{
	
	private final Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
	private	int Ancho=screenSize.width/2;
	private	int Alto=screenSize.height/2;
	private Color colorFicha;
	private final int FICHA_SIZE=10;
	private final int BORDE_FICHA=8;
	private int size ;
	private char[][] tablero;
	private void setTablero(char[][] tableroNuevo){
		tablero=tableroNuevo;
		size = tablero.length;
	}
	public PanelTableroPrueba(){
		setBackground(Color.blue);
		colorFicha=Color.red;
		setPreferredSize(new Dimension(Ancho,Alto)); 
		setTablero(new char[][] { 	{'o','o','o','+','+','+','o','o','o',},
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
	}
	 @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (tablero != null){
            int posX = BORDE_FICHA;
            int posY = BORDE_FICHA;
            for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {

                    if (tablero[y][x] == '+') {
                        pintaCirculo(g, posX, posY, FICHA_SIZE,  colorFicha);
                    } else if (tablero[y][x] == 'x') {
                        pintaCirculo(g, posX, posY, FICHA_SIZE, Color.yellow);
                    } else if (tablero[y][x] == 'o') {
                        pintaCirculo(g, posX, posY, FICHA_SIZE,Color.blue);
                    }
                    posX = posX + (FICHA_SIZE * 2) + BORDE_FICHA;
                }
                posX = BORDE_FICHA;
                posY = posY + (FICHA_SIZE * 2) + BORDE_FICHA;
            }
        }
    }
    
    private void pintaCirculo(Graphics g, int posX, int posY, int radio, Color color){
        int size = radio * 2;
        g.setColor(color);        
        g.fillOval(posX, posY, size, size);
    }
	public void ClickFicha(MouseEvent ev){
		int espacioMaximoFicha = (FICHA_SIZE * 2) + BORDE_FICHA ;
        int posY = (ev.getX() - (BORDE_FICHA/ 2)) / espacioMaximoFicha;
        int posX = (ev.getY() - (BORDE_FICHA/ 2)) / espacioMaximoFicha;
		
	}
	public void jugar(int fi, int ci ,int ff , int cf ){
		if(estaMarcada(ff,cf)){
			//marcarFicha(fi,ci);
			//desmarcarFicha(ff,cf);
			//eliminarfichaMitad();
			
		}
		//verificarsiganó
	}
	public boolean estaMarcada( int posX ,int posY){
            for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {
					if (tablero[posX][posY]=='x')
						return true;
				}		
			}
			return false;
	}
	
	public void fichasColor(){
		colorFicha=JColorChooser.showDialog(null,"Escoge el color de las fichas.",colorFicha);
		repaint();
	}
}
