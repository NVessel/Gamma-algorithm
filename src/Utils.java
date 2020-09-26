import java.io.File;

public class Utils {

    public static String toBinary(int n) {
        String r = "";
        if (n == 0) return "000000";
        while (n != 0) {
            r = (n % 2 == 0 ? "0" : "1") + r;
            n /= 2;
        }
        return r;
    }

    public static int minx(int x, int y) {
        return (x<=y)?x:y;
    }

    public static void createDotGraph(String dotFormat,
                                      String filename) {
        Graphviz gv = new Graphviz();
        gv.addln(gv.start_graph());
        gv.add(dotFormat);
        gv.addln(gv.end_graph());
        String type = "pdf";
        gv.decreaseDpi();
        gv.decreaseDpi();
        File out = new File(filename+"."+ type);
        gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
    }
}
