package matheus.com.br.eaivai.adapter;

/**
 * Created by matheus on 12/01/16.
 */
public class DataObject {
    private String label1;
    private String label2;

    public DataObject (String text1, String text2){
        label1 = text1;
        label2 = text2;
    }

    public String getLabel1() {
        return label1;
    }

    public void setLabel1(String label1) {
        this.label1 = label1;
    }

    public String getLabel2() {
        return label2;
    }

    public void setLabel2(String label2) {
        this.label2 = label2;
    }
}
