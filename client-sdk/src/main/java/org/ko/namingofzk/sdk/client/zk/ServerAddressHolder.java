package org.ko.namingofzk.sdk.client.zk;

import org.omg.CORBA.portable.ValueOutputStream;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class ServerAddressHolder {

    private static final List<String> container = new CopyOnWriteArrayList<>();

    public String getUrl() {
        return container.get(0);
    }

    public void clear () {
        container.clear();
    }

    public void set(List<String> urls) {
        container.addAll(urls);
    }
}
