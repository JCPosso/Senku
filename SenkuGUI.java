package presentacion;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class SenkuGUI extends JFrame{
	
	private JButton menusito;
	private JFrame MenuF;
	
	/*Frame Menu*/
	private JFrame MenuF;
	
	/*Panel Nuevo*/
    private JButton Nuevo;
    
    /*Panel Abrir*/
	private JButton Abrir;
    
    /*Panel Salvar*/
	private JButton Salvar;
	
	/*Panel Salvar Como*/
	private JButton Salvar_Como;
	
	/*Panel Salir*/
	private JButton Salir;
	
	private SenkuGUI(){
		prepareElementos();
		prepareAcciones();
	}
	
	private void prepareElementos(){
		setTitle("Senku");
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		int Ancho=screenSize.width/2;
		int Alto=screenSize.height/2;
		setLocation((screenSize.width-Ancho)/2,(screenSize.height-Alto)/2);
		setSize(new Dimension(Ancho,Alto));
		menusito=new JButton("Menusito");
		add(menusito);
	}
	
	public void prepareAcciones(){
		setDefaultCloseOperation(0);
        	addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent evt){
				Salga();
			}
		});
		menusito.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				Pausa();
			}
		});
	}
	public void prepareElementosMenu(){
		MenuF=new JFrame("Menu");
		MenuF.setLayout(new GridLayout(5,1));
		Nuevo = new JButton("Nuevo");
		Abrir = new JButton("Abrir");
		Salvar = new JButton("Salvar");
		Salvar_Como = new JButton("Salvar Como");
		Salir = new JButton("Salir");
		MenuF.add(Nuevo);
		MenuF.add(Abrir);
		MenuF.add(Salvar);
		MenuF.add(Salvar_Como);
		MenuF.add(Salir);
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		int Ancho=screenSize.width/2;
	    	int Alto=screenSize.height/2;
		MenuF.setLocation((screenSize.width-Ancho)/2,(screenSize.height-Alto)/2);
		MenuF.setSize(new Dimension(Ancho,Alto));
		MenuF.setVisible(true);
		MenuF.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent evt2){
				Salga();
			}
		});
		
	}
	public void Salga(){
		if (JOptionPane.showConfirmDialog(null,"Are you sure you want to exit",
				"Exit?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
					System.exit(0);
		}
	}
	
	public void Pausa(){
		prepareElementosMenu();
	}


public static void main(String args[]){
	SenkuGUI gui=new SenkuGUI();
	gui.setVisible(true);
	}
}
