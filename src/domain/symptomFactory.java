package domain;

import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class symptomFactory {
    // Biblioteca para almacenar los síntomas únicos
    private static final Map<String, Symptom> symptomLibrary = new HashMap<>();

    private static final List<String> IMPACT_5 = Arrays.asList("fiebre", "tos seca", "astenia","expectoracion");
    private static final List<Double> INDEX_5 = Arrays.asList(87.9, 67.7, 38.1, 33.4);
    private static final List<String> IMPACT_3 = Arrays.asList("disnea", "dolor de garganta", "cefalea","mialgia","escalofrios");
    private static final List<Double> INDEX_3 = Arrays.asList(18.6, 13.9, 13.6, 14.8, 11.4);
    private static final List<String> IMPACT_1 = Arrays.asList("nauseas", "vómitos", "congestión nasal","diarrea","hemoptisis","congestion conjuntival","mareos");
    private static final List<Double> INDEX_1 = Arrays.asList(5.0, 4.8, 3.7, 0.9, 0.8, 0.5, 0.8);

    private static final List<String> DIGESTIVE_SYMPTOMS = Arrays.asList("nauseas", "vómitos", "diarrea");
    private static final List<String> NEUROMUSCULAR_SYMPTOMS = Arrays.asList("fiebre", "astenia", "cefalea", "mialgia", "escalofrios", "mareos");
    private static final List<String> RESPIRATORY_SYMPTOMS = Arrays.asList("tos seca", "expectoracion", "disnea", "dolor de garganta", "congestión nasal", "hemoptisis", "congestion conjuntival");

    public static Symptom createSymptom(String symptomName) {
        // Si el síntoma ya existe en la biblioteca, lo devolvemos
        if (symptomLibrary.containsKey(symptomName)) {
            return symptomLibrary.get(symptomName);
        }

        // Si no existe, lo creamos
        Symptom newSymptom = createNewSymptom(symptomName);
        
        // Si se creó correctamente, lo guardamos en la biblioteca
        if (newSymptom != null) {
            symptomLibrary.put(symptomName, newSymptom);
        }
        
        return newSymptom;
    }

    private static Symptom createNewSymptom(String symptomName) {
        int impact = 0;
        double index = 0;

        // Determine impact and index
        if (IMPACT_5.contains(symptomName)) {
            impact = 5;
            index = INDEX_5.get(IMPACT_5.indexOf(symptomName));
        } else if (IMPACT_3.contains(symptomName)) {
            impact = 3;
            index = INDEX_3.get(IMPACT_3.indexOf(symptomName));
        } else if (IMPACT_1.contains(symptomName)) {
            impact = 1;
            index = INDEX_1.get(IMPACT_1.indexOf(symptomName));
        }

        // Create appropriate symptom type
        if (impact != 0) {
            if (DIGESTIVE_SYMPTOMS.contains(symptomName)) {
                return new DigestiveSymptom(symptomName, (int)index, impact);
            }
            if (NEUROMUSCULAR_SYMPTOMS.contains(symptomName)) {
                return new NeuroMuscularSymptom(symptomName, (int)index, impact);
            }
            if (RESPIRATORY_SYMPTOMS.contains(symptomName)) {
                return new RespiratorySymptom(symptomName, (int)index, impact);
            }
        }
        return null;
    }

    // Método para obtener el número de síntomas únicos creados
    public static int getNumberOfSymptoms() {
        return symptomLibrary.size();
    }
}