package fp.accidentes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fp.accidentes.Accidente;
import fp.common.Avion;
import fp.common.TipoAccidente;

public class FactoriaAccidentes {
	public static Accidentes leerAccidentes(String nomfich) {
		Accidentes res=null;
		int i=0;
		try {
			List<Accidente>  partidas=Files.lines(Paths.get(nomfich))
					.skip(1)
					.map(FactoriaAccidentes::parsearAccidente)
					.collect(Collectors.toList());
			
			res = new Accidentes(partidas);
			} catch(IOException e) {
			System.out.println("Fichero no encontrado: "+nomfich);
			e.printStackTrace();
		}
	return res; 
	}
	
	public static Accidentes leerAccidentesStream(String nomfich) {
		Accidentes res=null;
		int i=0;
		try {
			Stream<Accidente>  partidas=Files.lines(Paths.get(nomfich))
					.skip(1)
					.map(FactoriaAccidentes::parsearAccidente);
			res = new Accidentes(partidas);
			} catch(IOException e) {
			System.out.println("Fichero no encontrado: "+nomfich);
			e.printStackTrace();
		}
	return res;
	}
	
	public static Accidente parsearAccidente(String linea) {
		String[] trozos=linea.split(";");
		
		LocalDate date = LocalDate.parse(trozos[0].trim(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		LocalTime time = LocalTime.parse(trozos[1].trim(), DateTimeFormatter.ofPattern("HH:mm"));
		String location = trozos[2].trim();
		String flightTag = trozos[4].trim();
		String aircraftType = trozos[5].trim();
		String aircraftReg = trozos[6].trim();
		String cnLn = trozos[7].trim();
		Avion aircraft = new Avion(flightTag,aircraftType,aircraftReg,cnLn);
		TipoAccidente accidentType = TipoAccidente.valueOf(trozos[8].trim().toUpperCase().replace(" ","_"));
		Integer fatalities = Integer.valueOf(trozos[9].trim());
		Integer totalCrew = Integer.valueOf(trozos[10].trim());
		ArrayList<String> deadNames = trozos[11].trim().equals("[]") ? new ArrayList<String>() : new ArrayList<String>(Arrays.asList(trozos[11].substring(1, trozos[11].length()-1).trim().split(";")));
		
		return new Accidente(date, time, location, aircraft, accidentType, fatalities, totalCrew, deadNames);
	}
}
