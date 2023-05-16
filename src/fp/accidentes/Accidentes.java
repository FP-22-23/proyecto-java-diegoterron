package fp.accidentes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fp.common.TipoAccidente;

public class Accidentes {
	
	private List<Accidente> accidentes;
	
	public Accidentes() {
		accidentes = new ArrayList<Accidente>();
	}
	
	public Accidentes(Collection<Accidente> accidentes) {
		this.accidentes = new ArrayList<Accidente>(accidentes);
	}
	
	public Accidentes(Stream<Accidente> accidentes) {
		this.accidentes = accidentes.collect(Collectors.toList());
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
	
	
	
	public Accidentes filtrarPorUbicacion(String ubi) {
		ArrayList<Accidente> res = new ArrayList<Accidente>();
		
		for (Accidente a: accidentes) {
			if (a.getLocation().toLowerCase().contains(ubi.toLowerCase())) {
				res.add(a);
			}
		}
		
		return new Accidentes(res);
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
	
	
	// STREAMS (ENTREGA 3)
	
	public boolean containsStream(Accidente a) {
		return accidentes.stream()
				.anyMatch(n -> n.equals(a));
	}
	
	public float getMediaMuertesStream() {
		
		return (float) accidentes.stream()
				.mapToDouble(a -> a.getFatalities())
				.average()
				.getAsDouble();
	}
	
	public Accidente getMaxMuertes(String ubi) {
		Comparator<Accidente> comparador = Comparator.comparing(Accidente::getFatalities);
		return this.filtrarPorUbicacionStream(ubi).toList().stream()
				.max(comparador)
				.get();
	}

	public Accidentes filtrarYOrdenarPorFecha(int minimoMuertes) {
		List<Accidente> resultado = accidentes.stream()
                .filter(accidente -> accidente.getFatalities() >= minimoMuertes)
                .sorted(Comparator.comparing(Accidente::getDate))
                .collect(Collectors.toList());

        return new Accidentes(resultado);
	}
	
	public Accidentes filtrarPorUbicacionStream(String ubi) {
		return new Accidentes(accidentes.stream() 
			.filter(a -> a.getLocation().toLowerCase().contains(ubi.toLowerCase()))
			.collect(Collectors.toList()));
	}
	
	public Map<TipoAccidente, List<Accidente>> agruparPorTipoDeAccidenteStreams() {
	    Map<TipoAccidente, List<Accidente>> res = accidentes.stream()
	            .collect(Collectors.groupingBy(Accidente::getAccidentType));

	    return res;
	}
	

	public Map<TipoAccidente,Accidente> getMayorAccidentePorTipo() {
		return accidentes.stream()
				.collect(Collectors.groupingBy(Accidente::getAccidentType,
						Collectors.collectingAndThen(
								Collectors.maxBy(Comparator.comparing(Accidente::getFatalities)),
								o -> o.get())));
	}
	
	public Map<String,Set<TipoAccidente>> getTiposPorUbicacion() {
		return accidentes.stream()
				.collect(Collectors.groupingBy(
						Accidente::getLocation, Collectors.mapping(
								Accidente::getAccidentType, Collectors.toSet())));
	}
	
	public SortedMap<Integer, List<Accidente>> tripulacionPorNMuertes(int n) {
		
	    Map<Integer, List<Accidente>> accidentesPorTripulacion = accidentes.stream()
	            .collect(Collectors.groupingBy(Accidente::getTotalCrew));
	
	    Comparator<Accidente> comparadorPorMuertes = Comparator.comparing(Accidente::getFatalities);
	
	    SortedMap<Integer, List<Accidente>> mejoresPorNMuertes = new TreeMap<>();
	    
	    accidentesPorTripulacion.forEach((tripulacion, listaAccidentes) -> {
	        List<Accidente> masMuertos = listaAccidentes.stream()
	                .sorted(comparadorPorMuertes.reversed())
	                .limit(n)
	                .collect(Collectors.toList());
	        mejoresPorNMuertes.put(tripulacion, masMuertos);
	    });
	
	    return mejoresPorNMuertes;
	}
	
	public SortedMap<Integer, Accidente> tripulacionPorMasMuertes() {
		
	    Map<Integer, List<Accidente>> accidentesPorTripulacion = accidentes.stream()
	            .collect(Collectors.groupingBy(Accidente::getTotalCrew));
	
	    Comparator<Accidente> comparadorPorMuertes = Comparator.comparing(Accidente::getFatalities);
	
	    SortedMap<Integer, Accidente> mejoresPorMuertes = new TreeMap<>();
	    
	    accidentesPorTripulacion.forEach((tripulacion, listaAccidentes) -> {
	        Accidente masMuertos = listaAccidentes.stream()
	                .sorted(comparadorPorMuertes.reversed())
	                .limit(1)
	                .findFirst()
	                .get();
	        mejoresPorMuertes.put(tripulacion, masMuertos);
	    });
	
	    return mejoresPorMuertes;
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
	
	public List<Accidente> toList() {
		return this.accidentes;
	}
}
