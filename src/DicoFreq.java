import java.util.LinkedHashMap;

public class DicoFreq {

    private LinkedHashMap<String, Integer> frequencies;


    public DicoFreq(){
        this.frequencies = new LinkedHashMap<String, Integer>();
    }


    public LinkedHashMap<String, Integer> getFrequencies() {
        return frequencies;
    }


    public void addFrequency(String key, Integer value){
        frequencies.put(key, value);
    }


    public int getNbChar(){
        int nbChar = 0;
        for(String key : frequencies.keySet()){
            nbChar += frequencies.get(key);
        }
        return nbChar;
    }


    @Override
    public String toString() {
        return "DicoFreq{" +
                "frequencies=" + frequencies +
                '}';
    }

}
