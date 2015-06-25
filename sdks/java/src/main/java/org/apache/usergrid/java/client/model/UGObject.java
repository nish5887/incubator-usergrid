package org.apache.usergrid.java.client.model;

import org.apache.usergrid.java.client.UsergridClient;
import org.apache.usergrid.java.client.UGResult;

/**
 * Created by ApigeeCorporation on 6/23/15.
 */
public abstract class UGObject {
    private UsergridClient client;

    protected UGObject(UsergridClient client) {
        this.client = client;
    }

    public UGResult put() {
        return client.put(this);

    }

    public UGResult post() {
        return client.post(this);
    }

    public UGResult delete() {
        return client.delete(this);
    }

    public UGResult get() {
        return client.get(this);
    }

    public UGResult create() {
        return client.post(this);
    }


}
