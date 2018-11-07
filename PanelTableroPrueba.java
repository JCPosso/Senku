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
	private ArrayList<Integer> posiciones ;
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
		posiciones= new ArrayList<Integer>();
		colorFicha=Color.red;
		setPreferredSize(new Dimension(Ancho,Alto)); 
		setTablero(new char[][] { 				{'o','o','o','+','+','+','o','o','o',},
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
                        pintaCirculo(g, posX, posY,  Color.red);
                    } else if (tablero[y][x] == 'x') {
                        pintaCirculo(g, posX, posY, Color.yellow);
                    } else if (tablero[y][x] == 'o') {
                        pintaCirculo(g, posX, posY,Color.blue);
                    }
                    posX = posX + (FICHA_SIZE * 2) + BORDE_FICHA;
                }
                posX = BORDE_FICHA;
                posY = posY + (FICHA_SIZE * 2) + BORDE_FICHA;
            }
        }
    }
    
    private void pintaCirculo(Graphics g, int posX, int posY, Color color){
        int size = FICHA_SIZE * 2;
        g.setColor(color);        
        g.fillOval(posX, posY, size, size);
    }
	public void ClickFicha(MouseEvent ev){
		int espacioMaximoFicha = (FICHA_SIZE * 2) + BORDE_FICHA ;
        int posY = (ev.getX() - (BORDE_FICHA/ 2)) / espacioMaximoFicha;
        int posX = (ev.getY() - (BORDE_FICHA/ 2)) / espacioMaximoFicha;
		int tamaño_Tablero =8;
		if (posiciones.size()==2){
			jugar((int)posiciones.get(0),(int)posiciones.get(1),posX,posY);
			System.out.println(posiciones.get(0)+" "+posiciones.get(1)+" "+posX+" "+posY);
			posiciones=new ArrayList<Integer>();
		}
		else{
			posiciones.add(posX);
			posiciones.add(posY);
		}
	}
	public void jugar(int ci,int fi,int cf,int ff){
		if(estaMarcada(cf,ff)){
			if (ci-cf== 2 && fi==ff) {
				System.out.println(ci+" "+fi+" "+cf+" "+ff);
				marcarFicha(ci,fi);
				marcarFicha(ci-1,fi);
				desmarcarFicha(cf,ff);
			}
			else if (ci-cf== -2 && fi==ff){
				System.out.println(ci+" "+fi+" "+cf+" "+ff);
				marcarFicha(ci,fi);
				marcarFicha(ci+1,fi);
				desmarcarFicha(cf,ff);
			}
			else if (fi-ff== 2 && ci==cf) {
				System.out.println(ci+" "+fi+" "+cf+" "+ff);
				marcarFicha(ci,fi);
				marcarFicha(ci,fi-1);
				desmarcarFicha(cf,ff);
			}
			else if (fi-ff== -2 && ci==cf) {
				System.out.println(ci+" "+fi+" "+cf+" "+ff);
				marcarFicha(ci,fi);
				marcarFicha(ci,fi+1);
				desmarcarFicha(cf,ff);				
			}
			else{
				System.out.println("nel");
			}
		repaint();
		}
		else{
			System.out.println("no se puede mn");
		}
		//verificarsiganó
	}
	public boolean estaMarcada( int posX ,int posY){
					if (tablero[posX][posY]=='x')
						return true;
					return false;
	}
	public void marcarFicha( int posX ,int posY){
            tablero[posX][posY]='x';
	}
	public void desmarcarFicha( int posX ,int posY){
            tablero[posX][posY]='+';
	}
	
	public void eliminarFichaMitad( int posX ,int posY){
            ;
	}
	
	public void fichasColor(){
		colorFicha=JColorChooser.showDialog(null,"Escoge el color de las fichas.",colorFicha);
		repaint();
	}
}
