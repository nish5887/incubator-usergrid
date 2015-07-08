package org.apache.usergrid.java.client.entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.tinkerpop.blueprints.Vertex;
import org.apache.usergrid.java.client.Client;
import org.apache.usergrid.java.client.SingletonClient;
import org.apache.usergrid.java.client.response.ApiResponse;
import org.apache.usergrid.java.client.utils.JsonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.apache.usergrid.java.client.utils.JsonUtils.getStringProperty;
import static org.apache.usergrid.java.client.utils.JsonUtils.setStringProperty;

/**
 * Created by ayeshadastagiri on 7/2/15.
 */
public class Connection {

    protected Map<String, JsonNode> properties = new HashMap<String, JsonNode>();
    public final static String PROPERTY_SOURCE = "sname";
    public final static String PROPERTY_ID = "eId";

    Client client ;
    Entity sSource ;

    protected Connection(){

    }


    public String getType() {
        return this.getType();
    }


    public long getTimestamp(){
        return 0;
    }

//
//    /**
//     * set the timestamp for an edge.
//     */
//    private void setTimestamp() {
//
//    }
//
//    private void setLabel(String label) {
//
//    }
//
//    public String getStringProperty(String label) {
//        return null;
//    }


    public void setLabel(String label) {
        setStringProperty(properties,PROPERTY_SOURCE,label);
    }

    public void setConnectionID(String source, String label, String target) {
        setStringProperty(properties,PROPERTY_ID, new String(source+"-->"+label+"-->"+target));
        System.out.println("prop name for edge : " + getStringProperty(properties,PROPERTY_ID));
    }

    public void setClientDetails(Client client) {
        this.client = client;
    }

    public Client getClientConnection(){
        return this.client;
    }

    public String getPropertyId() {
        System.out.println("get id  :" + getStringProperty(properties,PROPERTY_ID));
        return getStringProperty(properties,PROPERTY_ID);
    }






}
