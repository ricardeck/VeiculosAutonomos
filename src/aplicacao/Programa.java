package aplicacao;

import ui.Console;
import ui.Menu;
import ui.VeiculosAutonomosUIIF;

public class Programa {
	
	public void run() throws Exception{
//		VeiculosAutonomosUIIF ui = (VeiculosAutonomosUIIF) new Console();
		VeiculosAutonomosUIIF ui = (VeiculosAutonomosUIIF) new Menu();
		ui.exibir();
	}
	
	public static void main(String[] args) throws Exception {
		(new Programa()).run();
	}

}
