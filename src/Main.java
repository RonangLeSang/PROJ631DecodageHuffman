import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {

        String fileName = "ressources/exemple";

        String url = fileName + "_freq.txt";
        Reader testRead = new Reader(url);
        DicoFreq dicoFreq = testRead.readDicoFile();
        testRead.setPath(fileName + "_comp.bin");
        String readBinary = testRead.readText();
        Node node = Node.construct_tree(dicoFreq.getFrequencies());

        String text = node.decode_tree(readBinary);
        System.out.println(text);
        Reader.writeFile(fileName + ".txt", text);

        Path ogPath = Paths.get(fileName + "_comp.bin");
        Path newPath = Paths.get(fileName + ".txt");
        double originalSize = Files.size(ogPath);
        double decompressedSize = Files.size(newPath);
        System.out.println("taux de compresssion : " + originalSize/decompressedSize*100 + " %");
        double bitsPerChar = readBinary.length()/text.length();
        System.out.println("nombre moyen de bits par caractère : " + bitsPerChar);
    }
}