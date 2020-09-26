public class Pair {
    public int first_element, second_element;

    Pair(int x, int y) {
        this.first_element = x;
        this.second_element = y;

    }
    Pair() {
        this.second_element = 0;
        this.first_element = 0;
    }

    public String appendTo(String s) {
        s += this.first_element + "--" + this.second_element + ";";
        return s;
    }
}
