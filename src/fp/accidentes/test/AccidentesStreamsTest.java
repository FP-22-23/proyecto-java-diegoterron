package fp.accidentes.test;

import fp.accidentes.Accidente;
import fp.accidentes.Accidentes;
import fp.accidentes.FactoriaAccidentes;

public class AccidentesStreamsTest {

	public static void main(String[] args) {
		Accidentes accidentes = FactoriaAccidentes.leerAccidentesStream("data/plane_crashes.csv");
		
		System.out.println("\nfiltrarPorUbicacionStream");
		System.out.println("================================");
		accidentes = accidentes.filtrarPorUbicacionStream("Spain");
		System.out.println("Sólo accidentes en \"Spain\": ");
		System.out.println(accidentes);
		
		System.out.println("\ncontainsStream");
		System.out.println("================================");
		Accidente a = FactoriaAccidentes.parsearAccidente("11/22/1974;05:29;Barcelona, Spain;Alpa Servicios Aereos;None;Cessna 500 Citation I;EC-CGG;500-0108;Unknown;4;4;[Arnie Bernhard,Jemima Rossie,Titiana Byram,Lashawn Alon]");
		Accidente b = FactoriaAccidentes.parsearAccidente("04/16/2023;23:59;Seville;Ryanair;None;Sikorsky S61-L;N618PA;61426;Unknown;1;1;[Diego Terrón]");
		System.out.println("Existe el accidente "+a+": "+accidentes.containsStream(a));
		System.out.println("Existe el accidente "+b+": "+accidentes.containsStream(b));

		System.out.println("\ngetMediaMuertesStream");
		System.out.println("================================");
		System.out.println("Media de muertes usando Stream: " + accidentes.getMediaMuertesStream());

		System.out.println("\ngetMaxMuertes");
		System.out.println("================================");
		System.out.println("Máximo de muertes en \"Spain\": " + accidentes.getMaxMuertes("Spain"));

		System.out.println("\nfiltrarYOrdenarPorFecha");
		System.out.println("================================");
		System.out.println("Accidentes filtrados y ordenados por fecha: " + accidentes.filtrarYOrdenarPorFecha(3));

		System.out.println("\nfiltrarPorUbicacionStream");
		System.out.println("================================");
		System.out.println("Accidentes filtrados por ubicación usando Stream: " + accidentes.filtrarPorUbicacionStream("Spain"));

		System.out.println("\nagruparPorTipoDeAccidenteStream");
		System.out.println("================================");
		System.out.println("Agrupado por tipo de accidente usando Streams: " + accidentes.agruparPorTipoDeAccidenteStream());

		System.out.println("\ngetMayorAccidentePorTipo");
		System.out.println("================================");
		System.out.println("Mayor accidente por tipo: " + accidentes.getMayorAccidentePorTipo());

		System.out.println("\ngetTiposPorUbicacion");
		System.out.println("================================");
		System.out.println("Tipos de accidente por ubicación: " + accidentes.getTiposPorUbicacion());

		System.out.println("\ntripulacionPorNMuertes");
		System.out.println("================================");
		System.out.println("Tripulación por 3 muertes: " + accidentes.tripulacionPorNMuertes(3));

		System.out.println("\ntripulacionPorMasMuertes");
		System.out.println("================================");
		System.out.println("Tripulación por más muertes: " + accidentes.tripulacionPorMasMuertes());
		
		
		
	}
}
