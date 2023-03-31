import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {

        String fileName = "exemple";

        String url = "C:\\Users\\ronan\\IdeaProjects\\PROJ631DecodageHuffman\\ressources\\" + fileName + "_freq.txt";
        Reader testRead = new Reader(url);
        DicoFreq dicoFreq = testRead.readDicoFile();
        testRead.setPath("C:\\Users\\ronan\\IdeaProjects\\PROJ631DecodageHuffman\\ressources\\" + fileName + "_comp.bin");
        String readBinary = testRead.readText();
        Node node = Node.construct_tree(dicoFreq.getFrequencies());

        System.out.println(node.decode_tree(readBinary));
    }
}