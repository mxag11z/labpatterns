package observer;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Set;
import iterator.Covid19PacientIterator;

import domain.Symptom;
import domain.symptomFactory;

import java.util.Iterator;


public class Covid19Pacient extends Observable {
    private String name;
    private int age;
    private Map<Symptom, Integer> symptoms = new HashMap<Symptom, Integer>();

    public Covid19Pacient(String name, int years) {
        this.name = name;
        this.age = years;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Ahora usa la SymptomFactory para obtener síntomas únicos

	public Symptom addSymptomByName(String symptomName, Integer w) {
		System.out.println("SE AGREGOOOOOOOOOOO\n");
        Symptom s = symptomFactory.createSymptom(symptomName);
        if (s != null) {
            symptoms.put(s, w);
            setChanged(); 
            notifyObservers();
        }
      
        return s;
    }
    public void addSymptom(Symptom c, Integer w){
		symptoms.put(c,w);
    }
    
    public int getWeight(Symptom s) {
        return symptoms.get(s);
    }

    public Set<Symptom> getSymptoms() {
        return symptoms.keySet();
    }

    public Symptom getSymptomByName(String symptomName) {
        for (Symptom s : symptoms.keySet()) {
            if (s != null && s.getName().equals(symptomName)) 
                return s;
        }
        return null;
    }


	public Symptom removeSymptomByName(String symptomName) {
        Symptom s = getSymptomByName(symptomName);
        if (s != null) {
            symptoms.remove(s);
        setChanged(); 
        notifyObservers();
        }
        return s;
    }
    public Iterator iterator() {
		return new Covid19PacientIterator(this.symptoms.keySet());
	}

    public double covidImpact() {
        double afection = 0;
        double increment = 0;
        
        if (symptoms.isEmpty()) return 0;

        // calculate afection
        for (Map.Entry<Symptom, Integer> entry : symptoms.entrySet()) {
            Symptom s = entry.getKey();
            Integer weight = entry.getValue();
            if (s != null) {
                afection += s.getSeverityIndex() * weight;
            }
        }
        afection = afection / symptoms.size();

        // calculate increment
        if (getAge() > 65) 
            increment = afection * 0.5;

        // calculate total impact
        return afection + increment;
    }
}