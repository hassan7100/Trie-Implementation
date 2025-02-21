import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Trie {
    private List<Trie> children;
    private boolean isWord;
    private char character ;
    public Trie() {
        children = new ArrayList<>();
        isWord = false;
    }
    public void insert(String word){
        if (!word.isEmpty())
            insert(word.toCharArray(), word.length());
    }
    private void insert(char[] subWord, int firstTimeInsertionLength) {
        Trie beginning = null;
        if(subWord.length == firstTimeInsertionLength) {
            beginning = searchLastOccuranceNode(subWord, firstTimeInsertionLength);
        }
        if (beginning != null && beginning.isWord)
            return;
        if (beginning == null){
            Trie node = new Trie();
            if(subWord.length == 0){
                node.isWord = true;
                children.add(node);
                return;
            }
            node.character = subWord[0];
            children.add(node);
            node.insert(createSubWord(subWord), firstTimeInsertionLength);
        }else{
            char[] synced = syncObjectWithWord(subWord, beginning);
            Trie node = new Trie();
            if(synced.length == 0){
                node.isWord = true;
                children.add(node);
                return;
            }
            node.character = synced[0];
            beginning.children.add(node);
            node.insert(createSubWord(synced), firstTimeInsertionLength);
        }

    }
    private char[] syncObjectWithWord(char[] word, Trie selectedNode){
        for(Trie child : children){
            if (child == selectedNode){
                return new String(word).substring(1).toCharArray();
            }
            return child.syncObjectWithWord(createSubWord(word), selectedNode);
        }
        return null;
    }

    public boolean search(String word) {
        if(!word.isEmpty())
            return search(word.toCharArray());
        else
            return false;
    }
    private boolean search(char[] subWord){
        for(Trie child : children){
            if(child.character == '\u0000' && child.isWord){
                return true;
            }
            if(subWord.length != 0 && child.character != subWord[0]){
                continue;
            }
            return child.search(createSubWord(subWord));
        }
        return false;
    }
    private Trie searchLastOccuranceNode(char[] subWord, int originalWordLength){
        for(Trie child : children){
            if(child.character == '\u0000' && child.isWord || (subWord.length != 0 && child.character != subWord[0] && originalWordLength != subWord.length)){
                return this;
            }
            if(subWord.length != 0 && child.character != subWord[0]){
                continue;
            }
            return child.searchLastOccuranceNode(createSubWord(subWord), originalWordLength);
        }
        return null;
    }
//
//    public boolean startsWith(String prefix) {
//
//    }

    private char[] createSubWord(char[] subWord){
        char[] newSubWord = new char[subWord.length -1];
        for(int i = 1; i < subWord.length; i++){
            newSubWord[i - 1] = subWord[i];
        }
        return newSubWord;
    }
}

