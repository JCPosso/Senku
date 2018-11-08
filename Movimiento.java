package presentacion;
public class Movimiento{
	private int ci;
	private int fi;
	private int cf;
	private int ff;
	private int medioX;
	private int medioY;
	public Movimiento(int ci ,int fi,int cf ,int ff){
		this.ci=ci;
		this.fi=fi;
		this.cf=cf;
		this.ff=ff;
		medioX=(getEnmedio(ci,cf));
		medioY=(getEnmedio(fi,ff));
	}
	public int getEnmedio(int cord1 ,int cord2){
		if ((cord1-cord2)==2 )return cord1-1;
		if ((cord1-cord2)==-2) return cord1+1;
		else return cord1;		
	}
	public boolean esValido(){
		if((Math.abs(fi-ff)==2)&&(	Math.abs(ci-cf)==0)) return true; 
		else if((Math.abs(fi-ff)==0)&&(	Math.abs(ci-cf)==2)) return true; 
		else return false;
	}
	public int getMedioX(){
		return medioX;
	}
	public int getMedioY(){
		return medioY;
	}
}