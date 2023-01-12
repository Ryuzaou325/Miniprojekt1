public class Main {
    public static void main(String[] args) {
        //testing...
        String inputFile = "in\\Roehrentransportsystem.csv";
        Flows g = new Flows(inputFile);
        System.out.print("Neighbours of " + g.getVertexName(1) + "(1): ");
        System.out.println(g.getVertexNames(g.getNeighbours(1)));
        System.out.print("Neighbours of " + g.getVertexName(30) + "(30): ");
        System.out.println(g.getVertexNames(g.getNeighbours(30)));
        System.out.print("Neighbours of " + g.getVertexName(40) + "(40): ");
        System.out.println(g.getVertexNames(g.getNeighbours(40)));
        System.out.print("Neighbours of " + g.getVertexName(70) + "(70): ");
        System.out.println(g.getVertexNames(g.getNeighbours(70)));
        System.out.print("Neighbours of " + g.getVertexName(104) + "(104): ");
        System.out.println(g.getVertexNames(g.getNeighbours(104)));
        System.out.print("Neighbours of " + g.getVertexName(84) + "(84): ");
        System.out.println(g.getVertexNames(g.getNeighbours(84)));
        System.out.println("Neighbours of S(" + g.S + "): " + g.getVertexNames(g.getNeighbours(g.S)));
        System.out.println("Size: " + g.size());
        System.out.println("Neighbours of " + g.getVertexName(g.size() - 1) + "(" + (g.size() - 1) + "): " + g.getVertexNames(g.getNeighbours(g.size() - 1) ));
        System.out.print("respective edge weights (" + (g.size() - 1) + ",neighbour): ");
        for (Integer i : g.getNeighbours(g.size() - 1)) {
            System.out.print("(" + (g.size() - 1)  + "," + i + ")=" + g.capacity(g.size() - 1, i) + " ");
        }
        System.out.println();
        System.out.println("(KP115,KP115') = (115,128) = " + g.capacity(115,128));


        /**Solution 1*/
        Flows g1 = new Flows(inputFile);
        System.out.println();
        System.out.println("SOLUTION 1");
        System.out.println("==========");
        FordFulkerson algo1 = new FordFulkerson(g1);
        System.out.println("Edge Count: " + g1.getEdgeCount());
        float maxFlow1 = algo1.computeMaxFlow();
        System.out.println("Max flow value: " + maxFlow1);
        System.out.println("Anzahl von Runden (1000 Geschenke): " + Math.ceil(1000 / maxFlow1));

        /**Solution 2*/
        System.out.println();
        Flows g2 = new Flows(inputFile);
        float maxFlow2 = 0f;
        System.out.println("SOLUTION 2 (without L2)");
        System.out.println("==========");
        Integer L2 = -1;
        for (Integer i = 1; i < g2.size(); i++) {
            if (g2.getVertexName(i).equals("L2")) {
                L2 = i;
                break;
            }
        }
        if (L2 == -1)
            System.out.println("Nil. (No 'L2' storage found.)");
        else {
            g2.deleteVertex(L2);
            FordFulkerson algo2 = new FordFulkerson(g2);
            System.out.println("Edge Count: " + g2.getEdgeCount());
            maxFlow2 = algo2.computeMaxFlow();
            System.out.println("Max flow value: " + maxFlow2);
            System.out.println("Anzahl von Runden (1000 Geschenke): " + Math.ceil(1000 / maxFlow2));
        }

        System.out.println();
        System.out.println("RESULT");
        System.out.println("======");
        if (maxFlow2 >= maxFlow1) {
            System.out.println("L2 can be removed.");
        } else {
            System.out.println("L2 should be kept.");
        }

        System.out.println();
        System.out.println("Checking if edges can be deleted such that flow is unaffected");
        System.out.println("======");
        if (algo1.showUselessFlowEdges().size() != 0) {
            System.out.println("Following edges can be deleted:");
            System.out.println(algo1.showUselessFlowEdges());
            System.out.println("# of deletable edges: " + algo1.showUselessFlowEdges().size());
        }
        else {
            System.out.println("no edge can be removed");
        }
    }
}

/* output:
vertexRemarks: {66=Kapazitaet: 73, 4=Kapazitaet: 17, 101=Kapazitaet: 84, 102=Kapazitaet: 75, 72=Kapazitaet: 73, 10=Hier ist es sehr kalt, 43=Standort von Rudolphs Ferienwohnung, 111=Kapazitaet: 99, 17=Kapazitaet: 69, 50=Kapazitaet: 49, 114=Kapazitaet: 59, 115=Kapazitaet: 31, 93=Lebkuchenfabrik}
Neighbours of L4(1): [KP105(105)]
Neighbours of L5(30): [KP91(91), KP106(106), KP88(88), KP19(19), KP25(25), KP37(37)]
Neighbours of L1(40): [KP115(115), KP31(31), KP91(91), KP18(18), KP51(51), KP89(89), KP94(94), KP81(81), KP53(53), KP68(68)]
Neighbours of L2(70): [KP73(73), KP106(106), L5(30), KP21(21), KP62(62), KP78(78), KP67(67)]
Neighbours of L3(104): [KP108(108), KP80(80), KP109(109), KP55(55)]
Neighbours of Z(84): []
Neighbours of S(0): [L5(30), L2(70), L3(104), L4(1), L1(40)]
Size: 129
Neighbours of KP115'(128): [KP85(85), KP91(91), KP33(33), KP5(5), KP23(23), KP100(100), KP11(11), KP97(97)]
respective edge weights (128,neighbour): (128,33)=54.0 (128,97)=44.0 (128,100)=31.0 (128,5)=20.0 (128,85)=85.0 (128,23)=88.0 (128,11)=80.0 (128,91)=64.0
(KP115,KP115') = (115,128) = 31.0

SOLUTION 1
==========
Edge Count: 627
Max flow value: 157.0
Anzahl von Runden (1000 Geschenke): 7.0

SOLUTION 2 (without L2)
==========
Deleting L2...
{67=33.0, 21=35.0, 73=98.0, 106=95.0, 30=49.0, 62=40.0, 78=9.0}
{}
..............
Edge Count: 620
Max flow value: 157.0
Anzahl von Runden (1000 Geschenke): 7.0

RESULT
======
L2 can be removed.
*/