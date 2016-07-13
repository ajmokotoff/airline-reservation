package reserve;

import client.Flight;
import java.util.HashMap;

public class ReserveResult {
    private HashMap<Flight,Boolean> result;
    private boolean AllReserved;
    private boolean LockSucceed;
    private boolean UnlockSucceed;

    public HashMap<Flight,Boolean> getResult(){return result;}
    public boolean isAllReserved(){return AllReserved;}
    public boolean isLockSucceed(){return LockSucceed;}
    public boolean isUnlockSucceed(){return UnlockSucceed;}
    public boolean isAllSet(){return AllReserved&&LockSucceed&&UnlockSucceed;}

    public ReserveResult(HashMap<Flight,Boolean> result,
                         boolean lockSucceed,boolean allReserved, boolean unlockSucceed){
        this.result=result;
        LockSucceed=lockSucceed;
        UnlockSucceed=unlockSucceed;
        AllReserved = allReserved;
    }

    public String toString(){
        return "All reserved:"+AllReserved+" | LockDB succeed:"+LockSucceed+" | Unlocked succeed:"+UnlockSucceed+" \n  detailed result:\n    "+result;
    }
}
