package fp.accidentes.test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import fp.accidentes.Accidente;
import fp.common.Avion;
import fp.common.TipoAccidente;

// test

public class AccidenteTest {
	
	public static void mostrarAccidente(Accidente a) {
		System.out.println(a);
		System.out.println("getDate: "+a.getDate());
		System.out.println("getTime: "+a.getTime());
		System.out.println("getLocation: "+a.getLocation());
		System.out.println("getAircraft: "+a.getAircraft());
		System.out.println("getAccidentType: "+a.getAccidentType());
		System.out.println("getFatalities: "+a.getFatalities());
		System.out.println("getTotalCrew: "+a.getTotalCrew());
		System.out.println("getDeadNames: "+a.getDeadNames());
		System.out.println("getDeadly: "+a.getDeadly());
		System.out.println("getDeathPercentage: "+a.getDeathPercentage()+"%");
	}
	
	public static void testConstructor1(LocalDate date, LocalTime time, String location, Avion aircraft, TipoAccidente accidentType,
			Integer fatalities, Integer totalCrew, ArrayList<String> deadNames) {
		
		try {
			Accidente a = new Accidente(date, time, location, aircraft, accidentType,
					fatalities, totalCrew, deadNames);	
			mostrarAccidente(a);
		} catch(IllegalArgumentException e) {
			System.out.println("Excepción capturada:\n   " + e);	
		} catch (Exception e) {
			System.out.println("Excepción inesperada:\n   " + e);
		}

	}
	
	public static void testConstructor2(LocalDate date, LocalTime time, String location) {
		
		try {
			Accidente a = new Accidente(date, time, location);	
			mostrarAccidente(a);
		} catch(IllegalArgumentException e) {
			System.out.println("Excepción capturada:\n   " + e);	
		} catch (Exception e) {
			System.out.println("Excepción inesperada:\n   " + e);
		}

	}
	
	public static void main(String[] args) {
		Integer i= 1;
		System.out.println("====================================");
		System.out.println("Constructor 1 - Caso de prueba " + i);
		testConstructor1(LocalDate.of(2019, 3, 14), LocalTime.of(8, 30), "Andalusia, Spain", new Avion("302","Boeing 737 Max 8","ET-AVJ","63450/7243"), TipoAccidente.MALFUNCTION,
				2, 16, new ArrayList<String>(Arrays.asList("Diego Terrón", "Lababa Giyas")));
		
		i++;
		System.out.println("====================================");
		System.out.println("Constructor 1 - Caso de prueba " + i);
		testConstructor1(LocalDate.of(2019, 3, 14), LocalTime.of(8, 30), "Andalusia, Spain", new Avion("302","Boeing 737 Max 8","ET-AVJ","63450/7243"), TipoAccidente.MALFUNCTION,
				1, 16, new ArrayList<String>(Arrays.asList("Diego Terrón", "Lababa Giyas")));
		
		i++;
		System.out.println("====================================");
		System.out.println("Constructor 1 - Caso de prueba " + i);
		testConstructor1(LocalDate.of(2019, 3, 14), LocalTime.of(8, 30), "Andalusia, Spain", new Avion("302","Boeing 737 Max 8","ET-AVJ","63450/7243"), TipoAccidente.MALFUNCTION,
				2, 1, new ArrayList<String>(Arrays.asList("Diego Terrón", "Lababa Giyas")));
		
		i++;
		System.out.println("====================================");
		System.out.println("Constructor 2 - Caso de prueba " + i);
		testConstructor2(LocalDate.of(2019, 3, 14), LocalTime.of(8, 30), "Andalusia, Spain");
	}

}
