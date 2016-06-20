package client;

public class Airplane {
    private String model, manf;
    private int first, coach;

    public Airplane(){}

    public Airplane(String model, String manf, int first, int coach){
        this.model=model;
        this.manf=manf;
        this.first=first;
        this.coach=coach;
    }

    public Airplane(String model, String manf, String first, String coach){
        this(model,manf,Integer.parseInt(first),Integer.parseInt(coach));
    }

    public String toString(){
        return "< "+model+", "+manf+", seat(first/coach):"+first+"/"+coach+" >";
    }
}
