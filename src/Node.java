import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Node {

    private String label;
    private int frequency;
    private Node lChild = null;
    private Node rChild = null;


    public Node(String label, int frequency){
        this.label = label;
        this.frequency = frequency;
    }


    public Node(String label){
        this.label = label;
    }


    public String getLabel() {
        return label;
    }


    public int getFrequency() {
        return frequency;
    }


    public Node[] getChildren() {
        Node[] children = {lChild, rChild};
        return children;
    }

    @Override
    public String toString() {
        return "Node{" +
                "label='" + label + '\'' +
                ", frequency=" + frequency +
                ", lChild=" + lChild +
                ", rChild=" + rChild +
                '}';
    }

    public void add_from_list(ArrayList<Node> nodes){
        /**
         * ajoute les 2 noeuds avec les labels "minimum" de la liste en paramètre, en tant qu'enfant du noeud courant
         */
        Node[] mins = Node.getMin(nodes);
        this.add_children(mins);
    }

    public void add_children(Node[] children){
        /**
         * ajoute 2 enfants au noeud courant à partir d'une liste passée en paramètre
         */
        lChild = children[0];
        rChild = children[1];
        frequency = children[0].getFrequency() + children[1].getFrequency();
    }


    public static ArrayList<Node> toNode(LinkedHashMap<String, Integer> frequencyList){
        /**
         * transforme un dictionnaire en liste de noeuds
         */
        ArrayList<Node> listNode = new ArrayList<Node>();
        for(String key : frequencyList.keySet()){
            listNode.add(new Node(key, frequencyList.get(key)));
        }
        return listNode;
    }


    public static boolean isNegative(int nb){
        /**
         * retourne si un nombre est négatif ou non
         */
        return nb < 0;
    }


    public static Node[] getMin(ArrayList<Node> frequencyList){
        /**
         * renvoi les 2 noeuds avec les labels "minimum" de la liste en paramètre
         */
        Node[] minList = new Node[2];
        for(int i=0; i<2; i++){
            Node min = frequencyList.get(0);
            for(Node node : frequencyList){
                if(node.getFrequency() < min.getFrequency() ||
                        (node.getFrequency() == min.getFrequency() && isNegative(node.getLabel().compareTo(min.getLabel()))) ||
                        (node.getFrequency() == min.getFrequency() && node.getLabel().equals("generic node"))
                ){
                    min = node;
                }
            }
            minList[i] = min;
            for(Node node : minList){
                frequencyList.remove(node);
            }
        }
        return minList;
    }

    public static Node construct_tree(LinkedHashMap<String, Integer> frequencyList) {
        /**
         * construit un arbre à partir d'une liste de noeuds
         */
        ArrayList<Node> frequencyListNode = Node.toNode(frequencyList);
        while(frequencyListNode.size() > 1){
            Node node = new Node("generic node");
            node.add_from_list(frequencyListNode);
            frequencyListNode.add(node);
            }
        return frequencyListNode.get(0);
    }

    public String[] decode_char(String text){
        /**
         * parcours l'arbre passé en paramètre selon le chemin donné jusqu'à trouver un caractère
         */
        if(getLabel() != "generic node"){
            String[] res = {text, getLabel()};
            return res;
        }else{
            if(text.length() == 0){
                String[] res = {"", "p"};
                return res;
            }else{
                try{
                    return getChildren()[Integer.parseInt(text.substring(0, 1))].decode_char(text.substring(1));
                }
                catch (StringIndexOutOfBoundsException e){
                    return getChildren()[Integer.parseInt(text.substring(0, 1))].decode_char("");
                }
            }
        }
    }

    public String decode_tree(String text){
        /**
         * décode chaque caractère du texte selon la méthode de Huffman
         */
        String result = "";

        while(text.length() > 1 && text.length() > 1){
            String[] decoded_text = decode_char(text);
            result += decoded_text[1];
            text = decoded_text[0];
        }
        return result;
    }

}