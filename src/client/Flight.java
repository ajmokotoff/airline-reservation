package client;

public class Flight {
    private String flightNo,depCode,arrCode,depTime,arrTime,flightTime,model;
    private int first,coach;
    private double firstPrice,coachPrie;

    public Flight(){}

    // the time need to transferring in the future, now they are saved as string
    public Flight(String flightNo, String depCode, String arrCode,
                  String depTime, String arrTime, String flightTime, String model, int first, int coach, double firstPrice, double coachPrice){
        this.flightNo=flightNo;
        this.depCode=depCode;
        this.arrCode=arrCode;
        this.depTime=depTime;
        this.arrTime=arrTime;
        this.flightTime=flightTime;
        this.model=model;
        this.first=first;
        this.firstPrice=firstPrice;
        this.coach=coach;
        this.coachPrie=coachPrice;
    }

    // constructor with all string params
    public Flight(String flightNo, String depCode, String arrCode,
                  String depTime, String arrTime, String flightTime, String model,
                  String first, String coach, String  firstPrice, String coachPrice){
        // regex is to remove all the irrelevant char
        this(flightNo,depCode,arrCode,depTime,arrTime,flightTime,model,Integer.parseInt(first),Integer.parseInt(coach),
                Double.parseDouble(firstPrice.replaceAll("[^0-9\\.]","")),Double.parseDouble(coachPrice.replaceAll("[^0-9\\.\\s]","")));
    }

    // won't print all the attributes
    public String toString(){
        return "< "+flightNo+", "+depCode+"->"+arrCode+", Model:"+model+", seat reserved (first/coach):"+first+"/"+coach+" >";
    }

}
