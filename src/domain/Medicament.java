package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Medicament {
    private String name;
    private List<Symptom> symptoms = new ArrayList<Symptom>();

    public Medicament(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Ahora usa la SymptomFactory para obtener síntomas únicos
    public Symptom addSymptomByName(String symptomName) {
        Symptom s = symptomFactory.createSymptom(symptomName);
        if (s != null && !symptoms.contains(s)) {  // Verificamos que no esté ya en la lista
            symptoms.add(s);
        }
        return s;
    }

    // El resto de métodos permanecen igual
    public void removeSymptom(Symptom s) {
        symptoms.remove(s);
    }

    public Iterator<Symptom> getSymptoms() {
        return symptoms.iterator();
    }

    public Symptom getSymptomByName(String symptomName) {
        Iterator<Symptom> i = symptoms.iterator();
        Symptom s = null;
        while (i.hasNext()) {
            s = i.next();
            if (s != null && s.getName().compareTo(symptomName) == 0) 
                return s;
        }
        return null;
    }

    public Symptom removeSymptomByName(String symptomName) {
        Symptom s = getSymptomByName(symptomName);
        if (s != null) 
            removeSymptom(s);
        return s;
    }
}

