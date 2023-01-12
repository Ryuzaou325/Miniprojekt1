import java.util.Set;

public class Vertex {

    protected String name;
    protected String remark;
    protected Integer id;

    Vertex(Integer id, String name, String remark) {
        this.id = id;
        this.name = name;
        this.remark = remark;
    }

    public void changeRemarkTo (String remark) {
        this.remark = remark;
    }
}
