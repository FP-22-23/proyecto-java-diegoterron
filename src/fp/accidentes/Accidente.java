package fp.accidentes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;

import fp.common.Avion;
import fp.common.TipoAccidente;
import fp.utiles.Checkers;

public class Accidente implements Comparable<Accidente> {
	
	private LocalDate date;
	private LocalTime time;
	private String location;
	private Avion aircraft; 
	private TipoAccidente accidentType;
	private Integer fatalities;
	private Integer totalCrew;
	private ArrayList<String> deadNames;
	
	public Accidente(LocalDate date, LocalTime time, String location, Avion aircraft, TipoAccidente accidentType,
			Integer fatalities, Integer totalCrew, ArrayList<String> deadNames) {
		
		Checkers.check("No puede haber más muertes que miembros de la tripulación.", fatalities <= totalCrew);
		Checkers.check("La cantidad de nombres de fallecidos debe ser igual o menor que el número de fallecidos.", deadNames.size() <= fatalities);
		
		this.date = date;
		this.time = time;
		this.location = location;
		this.aircraft = aircraft;
		this.accidentType = accidentType;
		this.fatalities = fatalities;
		this.totalCrew = totalCrew;
		this.deadNames = deadNames;

		Checkers.checkNoNull(this);
	}
	
	public Accidente(LocalDate date, LocalTime time, String location) {
		
		this.date = date;
		this.time = time;
		this.location = location;
		this.aircraft = new Avion("None", "Unknown", "None", "None");
		this.accidentType = TipoAccidente.UNKNOWN;
		this.fatalities = 0;
		this.totalCrew = 0;
		this.deadNames = new ArrayList<String>();
	}

	public LocalDate getDate() {
		return date;
	}

	public LocalTime getTime() {
		return time;
	}

	public String getLocation() {
		return location;
	}

	public Avion getAircraft() {
		return aircraft;
	}

	public TipoAccidente getAccidentType() {
		return accidentType;
	}

	public Integer getFatalities() {
		return fatalities;
	}
 
	public Integer getTotalCrew() { 
		return totalCrew;
	}

	public ArrayList<String> getDeadNames() {
		return deadNames;
	}
	
	public boolean getDeadly() {
		return this.fatalities > 0;
	}
	
	public Float getDeathPercentage() {
		return (((float) this.fatalities)/((float) this.totalCrew))*100f;
	}

	@Override
	public String toString() {
		return "Accidente [date=" + date + ", time=" + time + ", location=" + location + ", aircraft=" + aircraft
				+ ", accidentType=" + accidentType + ", deadly=true, death_percentage=" + getDeathPercentage() + "%, fatalities=" + fatalities + ", totalCrew=" + totalCrew
				+ ", deadNames=" + deadNames + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Accidente other = (Accidente) obj;
		return accidentType == other.accidentType && Objects.equals(aircraft, other.aircraft)
				&& Objects.equals(date, other.date) && Objects.equals(deadNames, other.deadNames)
				&& Objects.equals(fatalities, other.fatalities) && Objects.equals(location, other.location)
				&& Objects.equals(time, other.time) && Objects.equals(totalCrew, other.totalCrew);
	}
	
	public int compareTo(Accidente o) {
		return o.getDate().until(this.date).getDays();
	}
	
}