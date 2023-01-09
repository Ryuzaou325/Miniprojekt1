public class Main {
    public static void main(String[] args) {
        Flows g = new Flows(args[0]);
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
    }
}