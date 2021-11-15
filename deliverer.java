import java.util.List;
import java.util.ArrayList;
public class deliverer{
    private String departTime;
    private intersection departAdd;
    private List<Request> requests;
    public deliverer(String departTime,intersection departAdd){
        this.departTime=departTime;
        this.departAdd=departAdd;
        requests=new ArrayList<Request>();
    }

    public String getDepartTime(){
        return departTime;
    }

    public intersection getDepartAdd(){
        return departAdd;
    }

    public List<Request> getRequests(){
        return requests;
    }

    public void addRequest(Request r){
        requests.add(r);
    }

}