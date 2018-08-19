package classes.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * This class represents the state of the server running 
 *
 */
public class Server {

    private String name;
    private String ip;
    public long requestsDone = 0;

    public Server() {
        ip = getIPAddress();
        name = getNameOfTheMachine();
    }

    public String getIp() {
        return this.ip;
    }

    public String getName() {
        return this.name;
    }

    public long getTotalMemory() {
        return Runtime.getRuntime().totalMemory();
    }

    public long getFreeMemory() {
        return Runtime.getRuntime().freeMemory();
    }

    public long getMemoryUsage() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    private String getIPAddress() {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            return inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    private String getNameOfTheMachine() {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            return inetAddress.getHostName();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public long getRequestsDone() {
        return this.requestsDone;
    }
    
    /**
     * Count the number of request that this server has done
     */
    public long addUpRequestsDone() {
        this.requestsDone++;
        return this.requestsDone;
    }
    
    public String toJSON() {
        return "{name:\""+ this.getName() +"\",ip:\""+this.getIp()+"\",requestsDone:\""+this.requestsDone+"\", freeMemory:\""+getFreeMemory()+"\",totalMemory:\""+getTotalMemory()+"\"}";
    }
    

}
