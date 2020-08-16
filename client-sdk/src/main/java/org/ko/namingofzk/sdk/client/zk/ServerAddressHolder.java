package org.ko.namingofzk.sdk.client.zk;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ServerAddressHolder {

    private static final Map<String, String> container = new ConcurrentHashMap<>();

    private volatile String key;

    public String getUrl() {
        return container.get(key);
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void set(String key, String value) {
        container.put(key, value);
    }
}
