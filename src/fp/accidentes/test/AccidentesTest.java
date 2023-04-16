package fp.accidentes.test;

import fp.accidentes.Accidente;
import fp.accidentes.Accidentes;
import fp.accidentes.FactoriaAccidentes;

public class AccidentesTest {

	public static void main(String[] args) {
		Accidentes accidentes = FactoriaAccidentes.leerAccidentes("data/plane_crashes.csv");
		
		System.out.println("\nfiltrarPorUbicacion");
		System.out.println("================================");
		accidentes.filtrarPorUbicacion("Spain");
		System.out.println("Sólo accidentes en \"Spain\": ");
		System.out.println(accidentes);
		
		System.out.println("\ncontains");
		System.out.println("================================");
		Accidente a = FactoriaAccidentes.parsearAccidente("11/22/1974;05:29;Barcelona, Spain;Alpa Servicios Aereos;None;Cessna 500 Citation I;EC-CGG;500-0108;Unknown;4;4;[Arnie Bernhard,Jemima Rossie,Titiana Byram,Lashawn Alon]");
		Accidente b = FactoriaAccidentes.parsearAccidente("04/16/2023;23:59;Seville;Ryanair;None;Sikorsky S61-L;N618PA;61426;Unknown;1;1;[Diego Terrón]");
		System.out.println("Existe el accidente "+a+": "+accidentes.contains(a));
		System.out.println("Existe el accidente "+b+": "+accidentes.contains(b));
		
		System.out.println("\ngetMediaMuertes");
		System.out.println("================================");
		System.out.println("Media de muertes: "+accidentes.getMediaMuertes());
		
		System.out.println("\nagruparPorTipoDeAccidente");
		System.out.println("================================");
		System.out.println("Agrupado por tipo de accidente: "+accidentes.agruparPorTipoDeAccidente());
		
		System.out.println("\ncontarPorTipoDeAccidente");
		System.out.println("================================");
		System.out.println("Contado por tipo de accidente: "+accidentes.contarPorTipoDeAccidente());
		
		
		
	}
}
