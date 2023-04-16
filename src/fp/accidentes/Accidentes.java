package fp.accidentes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import fp.common.TipoAccidente;

public class Accidentes {
	
	private List<Accidente> accidentes;
	
	public Accidentes() {
		accidentes = new ArrayList<Accidente>();
	}
	
	public Accidentes(Collection<Accidente> accidentes) {
		this.accidentes = new ArrayList<Accidente>(accidentes);
	}
	
	public int size() {
		return accidentes.size();
	}
	
	public boolean add(Accidente a) {
		return accidentes.add(a);
	}
	
	public boolean add(Collection<Accidente> a) {
		return accidentes.addAll(a);
	}
	
	public boolean remove(Accidente a) {
		return accidentes.remove(a);
	}
	
	public boolean contains(Accidente a) {
		return accidentes.contains(a);
	}
	
	public float getMediaMuertes() {
		float sum = 0f;
		
		for (Accidente a: accidentes) {
			sum += (float) a.getFatalities();
		}
		
		return sum/accidentes.size();
	}
	
	public void filtrarPorUbicacion(String ubi) {
		ArrayList<Accidente> res = new ArrayList<Accidente>();
		
		for (Accidente a: accidentes) {
			if (a.getLocation().toLowerCase().contains(ubi.toLowerCase())) {
				res.add(a);
			}
		}
		
		accidentes = res;
	}
	
	public Map<TipoAccidente, List<Accidente>> agruparPorTipoDeAccidente() {
		Map<TipoAccidente, List<Accidente>> res = new HashMap<TipoAccidente, List<Accidente>>();
		
		for (Accidente a: accidentes) {
			if (res.get(a.getAccidentType()) != null) {
				res.get(a.getAccidentType()).add(a);
			} else {
				List<Accidente> ar = new ArrayList<Accidente>();
				ar.add(a);
				res.put(a.getAccidentType(), ar);
			}
		}
		
		return res;
	}
	
	public Map<TipoAccidente, Integer> contarPorTipoDeAccidente() {
		Map<TipoAccidente, Integer> res = new HashMap<TipoAccidente, Integer>();
		
		for (Accidente a: accidentes) {
			if (res.get(a.getAccidentType()) != null) {
				res.put(a.getAccidentType(), res.get(a.getAccidentType())+1);
			} else {
				res.put(a.getAccidentType(), 1);
			}
		}
		
		return res;
	}
	
	
	public int hashCode() {
		return Objects.hash(accidentes);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Accidentes other = (Accidentes) obj;
		return Objects.equals(accidentes, other.accidentes);
	}
	
	public String toString() {
		String accidentesStr = accidentes.stream().map(Object::toString).collect(Collectors.joining("\n"));
		return accidentesStr;
	}
}
