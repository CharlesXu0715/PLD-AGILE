import java.util.List;
import java.util.ArrayList;
public class RequestList{
    private String departTime;
    private String departAdd;
    private List<Request> requests;
    public RequestList(String departTime,String departAdd,List<Request>requests){
        this.departTime=departTime;
        this.departAdd=departAdd;
        this.requests=requests;
    }

    public String getDepartTime(){
        return departTime;
    }

    public String getDepartAdd(){
        return departAdd;
    }

    public List<Request> getRequests(){
        return requests;
    }

    public void addRequest(Request r){
        requests.add(r);
    }

}