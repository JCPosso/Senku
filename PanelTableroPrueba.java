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
	private int tamano;
	private char loadChar ;
	private ArrayList<Integer> posiciones ;
	private char[][] tablero;
	private void setTablero(char[][] tableroNuevo){
		tablero=tableroNuevo;
		size = tablero.length;
	}
	private void crearTablero(int mini_col, int n_filas){
		tablero = new char[n_filas][n_filas];
		tamano=n_filas;
		/*dibuja cruz del centro*/
		int mid=(n_filas/2 + n_filas%2)-1;
		int cont=0;
		dibujarCruz(mid);
		tablero[mid][mid]='x';
		cont++;
		/*dibuja el resto de las cruces*/
		int avanzar=mid;
		int retroceder=mid;
		while( cont!=mini_col) {
			if (cont%2==0){
				retroceder--;
				dibujarCruz(retroceder);

			}
			else{
				avanzar++;
				dibujarCruz(avanzar);
			}
			cont++;
		}
	}
	private void dibujarCruz(int mid){
		for (int iter = 0; iter < tamano; iter++) {
			tablero[iter][mid]='+';
			tablero[mid][iter]='+';
		}
	}
	public PanelTableroPrueba(int fil, int col){
		setBackground(Color.blue);
		posiciones= new ArrayList<Integer>();
		colorFicha=Color.red;
		setPreferredSize(new Dimension(Ancho,Alto)); 
		crearTablero(fil,col);
		repaint();
	}
	 @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (tablero != null){
            int posX = BORDE_FICHA;
            int posY = BORDE_FICHA;
            for (int y = 0; y < tablero.length; y++) {
                for (int x = 0; x < tablero.length; x++) {
                    if (tablero[y][x] == '+') {
                        pintaCirculo(g, posX, posY,  colorFicha);
                    } else if (tablero[y][x] == 'x') {
                        pintaCirculo(g, posX, posY, Color.yellow);
                    } else if (tablero[y][x] == 'o') {
                        pintaCirculo(g, posX, posY,Color.blue);
                    } else if (tablero[y][x] == 'a') {
                        pintaCirculo(g, posX, posY,Color.green);
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
		if (!(posX>=tamano) && !(posY>=tamano)){ 
			if (posiciones.size()==2){
				jugar((int)posiciones.get(0),(int)posiciones.get(1),posX,posY);
				System.out.println(posiciones.get(0)+" "+posiciones.get(1)+" "+posX+" "+posY);
				posiciones=new ArrayList<Integer>();
			}
			else{
				posiciones.add(posX);
				posiciones.add(posY);
				loadChar=tablero[posX][posY];
				tablero[posX][posY]='a';
				repaint();
			}
		}
		else{
			JOptionPane.showMessageDialog(this,"lugar fuera del juego, por favor intente de nuevo","importante",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public void jugar(int ci,int fi,int cf,int ff){
		Movimiento jugada = new Movimiento(ci,fi,cf,ff);
		if(jugada.esValido()&&tablero[jugada.getMedioX()][jugada.getMedioY()]=='+' && tablero[cf][ff]=='x'){
				System.out.println(ci+" "+fi+" "+cf+" "+ff);
				marcarFicha(ci,fi);
				marcarFicha(jugada.getMedioX(),jugada.getMedioY());
				desmarcarFicha(cf,ff);
		}			
		else{
				System.out.println("nel");
				tablero[ci][fi]=loadChar;
			}
		repaint();

		//verificarsiganó
	}
	public void marcarFicha( int posX ,int posY){
            tablero[posX][posY]='x';
	}
	public void desmarcarFicha( int posX ,int posY){
            tablero[posX][posY]='+';
	}
	
	public void fichasColor(){
		colorFicha=JColorChooser.showDialog(null,"Escoge el color de las fichas.",colorFicha);
		if (colorFicha==null){	
			colorFicha=Color.red;	
		}
		repaint();
	}
}
