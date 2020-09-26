import java.util.Scanner;
import java.io.*;
import java.lang.String;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {
        int dim;
        int[][] graph;
        int non_counter = 0;
        FileReader fr = new FileReader("input.txt");
        FileWriter fw1 = new FileWriter("output1.txt", false);
        FileWriter fw2 = new FileWriter("output2.txt", false);
        Scanner scanner = new Scanner(fr);
        int bYte;
        char reserve;
        String result;
        String q;
        String g6;
        String dotString;
        int ssize;
        int counter;
        Graph gr;
        Graph lil_graph;
        ArrayList<Pair> bridges;
        ArrayList<ArrayList<Integer>> components;
        ArrayList<Integer> dots;
        Faces planar;
        ArrayList<Graph> graphs;
        ArrayList<Integer> vert_order;
        while (scanner.hasNextLine()) {
            g6 = scanner.nextLine();
            if (g6.isEmpty()) break;
            reserve = g6.charAt(0);
            result = "";
            ssize = (int) reserve;
            ssize -= 63;
            dim = ssize;
            graph = new int[dim][dim];
            for (int i = 0; i < dim; i++) {
                for (int j = 0; j < dim; j++) {
                    graph[i][j] = 0;
                }
            }
            for (int i = 1; i < g6.length(); i++) {
                reserve = g6.charAt(i);
                bYte = (int) reserve;
                bYte -= 63;
                q = Utils.toBinary(bYte);
                while (q.length() % 6 != 0) {
                    q = "0" + q;
                }
                result += q;
                while (result.length() % 6 != 0) {
                    result += "0";
                }
            }
            counter = 0;
            for (int j = 0; j < dim; j++) {
                for (int i = 0; i < j; i++) {
                    if (result.charAt(counter++) == '0') {
                        graph[i][j] = 0;
                    } else graph[i][j] = 1;
                }
            }
            for (int j = 0; j < dim; j++) {
                for (int i = 0; i < j; i++) {
                    graph[j][i] = graph[i][j];
                }
            }
            gr = new Graph(graph);
            bridges = gr.getBridges(gr);
            for (int i = 0; i < bridges.size(); i++) {
                int xi,yj;
                xi = bridges.get(i).first_element;
                yj = bridges.get(i).second_element;
                graph[xi][yj] = 0;
                graph[yj][xi] = 0;
            }
            gr = new Graph(graph);
            components = gr.getComps(gr);
            dotString = "";
            if (components.size() == 1) {
                dots = gr.getPoints(gr);
                gr.setPoints(dots);
                planar = gr.getPlanarLaying();
                if (gr.isTree(gr)) {
                    // fw1.write(plan_counter + " ");
                    fw1.write(g6);
                    fw1.write('\n');
                    continue;
                }//tree processing

                if (planar != null) {
                  //  dotString = planar.getDotString(planar);
                    // fw1.write(plan_counter + " ");
                    fw1.write(g6);
                    fw1.write('\n');
                   // createDotGraph(dotString, "dotg");
                } else {
                    non_counter++;
                    fw2.write(non_counter + " ");
                    fw2.write(g6);
                    fw2.write('\n');
                }
            }//connected graph
            else {
                graphs = new ArrayList<>();
                for (int t = 0; t < components.size(); t++) {
                    vert_order = new ArrayList<>();
                    int [][] matrixx = new int[components.get(t).size()][components.get(t).size()];
                    for (int ix = 0; ix < components.get(t).size(); ix++) {
                        for (int jx = 0; jx < components.get(t).size(); jx++) {
                            matrixx[ix][jx] = 0;
                        }
                    }

                    for (int ix = 0; ix < components.get(t).size(); ix++) {
                        for (int jx = 0; jx < components.get(t).size(); jx++) {
                            matrixx[ix][jx] = graph[(components.get(t).get(ix))][(components.get(t).get(jx))];
                        }
                        vert_order.add(components.get(t).get(ix));
                    }
                    lil_graph = new Graph(matrixx);
                    lil_graph.setPlaces(vert_order);
                    graphs.add(lil_graph);
                }
                boolean flag = false; //find unplanar
                boolean from_begin = true; // define which operation
               for (Graph gra : graphs) {
                   if (gra.isTree(gra)) {
                       dotString += gra.getPlaces().get(0) + ";";
                       continue;
                   }
                   dots = gra.getPoints(gra);
                   gra.setPoints(dots);
                   Faces plan = gra.getPlanarLaying();
                   if (plan == null) {
                       flag = true;
                   }
                   else if (from_begin) {
                       from_begin = false;
                       dotString = plan.getDotStringWithReplace(plan, gra.getPlaces());
                   }
                   else {
                       dotString = plan.proceedDotStringWithReplace(plan,dotString,gra.getPlaces());
                   }
               }
                if (flag) {
                    non_counter++;
                    fw2.write(non_counter + " ");
                    fw2.write(g6);
                    fw2.write('\n');
                }
                else {
                    for (Pair p : bridges) {
                        dotString = p.appendTo(dotString);
                    }
                    //   fw1.write(plan_counter + " ");
                    fw1.write(g6);
                    fw1.write('\n');
                   // createDotGraph(dotString, "dotg");
                }
            }//unconnected
        }//upcoming graph

        fw1.close();
        fw2.close();

    }
}