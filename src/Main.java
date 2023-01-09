public class Main {
    public static void main(String[] args) {
        Flows g = new Flows(args[0]);

        //testing...
        System.out.println(g.vertexRemarks);
        System.out.print(g.getVertexName(1));
        System.out.println(g.getNeighbours(1));
        System.out.print(g.getVertexName(30));
        System.out.println(g.getNeighbours(30));
        System.out.print(g.getVertexName(40));
        System.out.println(g.getNeighbours(40));
        System.out.print(g.getVertexName(70));
        System.out.println(g.getNeighbours(70));
        System.out.print(g.getVertexName(104));
        System.out.println(g.getNeighbours(104));
        System.out.print(g.getVertexName(84));
        System.out.println(g.getNeighbours(84));
        /* output:
        {66=Kapazitaet: 73, 4=Kapazitaet: 17, 101=Kapazitaet: 84, 102=Kapazitaet: 75, 72=Kapazitaet: 73, 10=Hier ist es sehr kalt, 43=Standort von Rudolphs Ferienwohnung, 111=Kapazitaet: 99, 17=Kapazitaet: 69, 50=Kapazitaet: 49, 114=Kapazitaet: 59, 115=Kapazitaet: 31, 93=Lebkuchenfabrik}
        L4[105]
        L5[19, 37, 88, 25, 106, 91]
        L1[81, 18, 51, 115, 68, 53, 89, 91, 94, 31]
        L2[67, 21, 73, 106, 30, 62, 78]
        L3[80, 55, 108, 109]
        Z[]
         */
    }
}