import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class Faces {

    private ArrayList<ArrayList<Integer>> interior;
    private ArrayList<Integer> external;
    private int size;

    public Faces(ArrayList<ArrayList<Integer>> interior, ArrayList<Integer> external) {
        if(interior != null && external != null) {
            this.interior = (ArrayList<ArrayList<Integer>>) interior.clone();
            this.external = (ArrayList<Integer>) external.clone();
            size = interior.size() + external.size();
        } else {
            size = 0;
        }
    }

    public ArrayList<ArrayList<Integer>> getInterior() {
        return (ArrayList<ArrayList<Integer>>) interior.clone();
    }

    public ArrayList<Integer> getExternal() {
        return (ArrayList<Integer>) external.clone();
    }

    public String getDotString(Faces f) {
        String result = "";
        for (ArrayList<Integer> l : f.getInterior()) {
            if (l.size() <= 1) continue;
            for (int i = 0; i < l.size() - 1; i++) {
                result += l.get(i) + "--" + l.get(i+1) + ";";
            }
            result += l.get(l.size() - 1) + "--" + l.get(0) + ";";
        }
        return result;
    }

    public String getDotStringWithReplace(Faces f, ArrayList<Integer> pcs) {
        String result = "";
        for (ArrayList<Integer> l : f.getInterior()) {
            if (l.size() <= 1) continue;
            for (int i = 0; i < l.size() - 1; i++) {
                result += pcs.get(l.get(i)) + "--" + pcs.get(l.get(i+1)) + ";";
            }
            result += pcs.get(l.get(l.size() - 1)) + "--" + pcs.get(l.get(0)) + ";";
        }
        return result;
    }

    public String proceedDotString(Faces f, String s) {
        String result = s;
        for (ArrayList<Integer> l : f.getInterior()) {
            if (l.size() <= 1) continue;
            for (int i = 0; i < l.size() - 1; i++) {
                result += l.get(i) + "--" + l.get(i+1) + ";";
            }
            result += l.get(l.size() - 1) + "--" + l.get(0) + ";";
        }
        return result;
    }

    public String proceedDotStringWithReplace(Faces f, String s, ArrayList<Integer> pcs) {
        String result = s;
        for (ArrayList<Integer> l : f.getInterior()) {
            if (l.size() <= 1) continue;
            for (int i = 0; i < l.size() - 1; i++) {
                result += pcs.get(l.get(i)) + "--" + pcs.get(l.get(i+1)) + ";";
            }
            result += pcs.get(l.get(l.size() - 1)) + "--" + pcs.get(l.get(0)) + ";";
        }
        return result;
    }
    @Override
    public String toString() {
        String result = "Faces size = " + size + "\nExternal face:\n" + external + "\nInterior faces:\n";
        for(ArrayList<Integer> f : interior) {
            result += f + "\n";
        }
        return result;
    }
}