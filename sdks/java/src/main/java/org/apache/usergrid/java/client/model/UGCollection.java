package org.apache.usergrid.java.client.model;

import org.apache.usergrid.java.client.UsergridClient;

/**
 * Created by ApigeeCorporation on 6/23/15.
 */
public class UGCollection extends
        UGObject {
    String name;

    public UGCollection(String name, UsergridClient client) {
        super(client);
        this.name = name;
    }

    public UGEntity newEntity() {
            return null;
    }
}
