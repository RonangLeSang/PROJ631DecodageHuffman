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


    @Override
    public String toString() {
        return "DicoFreq{" +
                "frequencies=" + frequencies +
                '}';
    }

}
