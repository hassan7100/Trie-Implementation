//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Trie root = new Trie();
        root.insert("car");
        root.insert("bar");
        root.insert("cab");
        System.out.println(root.search("car"));
        System.out.println(root.search("cab"));
        root.insert("java");
        System.out.println(root.search("java"));
        System.out.println(root.search("jake"));
    }
}