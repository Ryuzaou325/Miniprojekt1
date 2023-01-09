public class Main {
    public static void main(String[] args) {
        Flows g = new Flows(args[0]);

        //testing...
        System.out.println(g.vertexRemarks);
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

        /* output:
        {66=Kapazitaet: 73, 4=Kapazitaet: 17, 101=Kapazitaet: 84, 102=Kapazitaet: 75, 72=Kapazitaet: 73, 10=Hier ist es sehr kalt, 43=Standort von Rudolphs Ferienwohnung, 111=Kapazitaet: 99, 17=Kapazitaet: 69, 50=Kapazitaet: 49, 114=Kapazitaet: 59, 115=Kapazitaet: 31, 93=Lebkuchenfabrik}
        Neighbours of L4(1): [KP105(105)]
        Neighbours of L5(30): [KP91(91), KP106(106), KP88(88), KP19(19), KP25(25), KP37(37)]
        Neighbours of L1(40): [KP115(115), KP31(31), KP91(91), KP18(18), KP51(51), KP89(89), KP94(94), KP81(81), KP53(53), KP68(68)]
        Neighbours of L2(70): [KP73(73), KP106(106), L5(30), KP21(21), KP62(62), KP78(78), KP67(67)]
        Neighbours of L3(104): [KP108(108), KP80(80), KP109(109), KP55(55)]
        Neighbours of Z(84): []
        Neighbours of S(0): [L5(30), L2(70), L3(104), L4(1), L1(40)]
         */
    }
}