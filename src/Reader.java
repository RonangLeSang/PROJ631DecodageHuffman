import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


public class Reader {

    private String path;

    public Reader(String path) {
        this.path = path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public DicoFreq readDicoFile() throws IOException {
        /**
         * lit le fichier de fréquence et renvoi un dictionnaire
         */
        File file = new File(this.path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        DicoFreq dicoFreq = new DicoFreq();
        br.readLine();
        while ((st = br.readLine()) != null) {
            dicoFreq.addFrequency(st.substring(0, 1), Integer.parseInt(st.substring(2, 3)));
        }
        return dicoFreq;
    }


    public String readText() {
        /**
         * lit le texte en binaire
         */
        try {
            final var content = Files.readAllBytes(Path.of(path));
            String text = "";
            for(int byteInFile : content){
                text += String.format("%8s", Integer.toBinaryString(byteInFile & 0xff)).replaceAll(" ", "0");
            }
            return text;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeFile(String path, String text) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write(text);
        writer.close();
    }

}
