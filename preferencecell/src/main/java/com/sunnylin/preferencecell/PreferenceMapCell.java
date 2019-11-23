package com.sunnylin.preferencecell;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by SunnyLin on 2019/09/15.
 */
public class PreferenceMapCell<K,V> extends PreferenceCellBase<V> {

    private Map<K,V> mapDefaultObject;
    private Map<K, V> mapCache = new HashMap<>();


    public PreferenceMapCell(Class<K> keyClass, V defaultObject) {
        this.defaultObject = defaultObject;
        initType(defaultObject);
    }

    public PreferenceMapCell(Map<K, V> mapDefaultObject) {
        this.mapDefaultObject = mapDefaultObject;
        V defaultObject = mapDefaultObject.entrySet().iterator().next().getValue();
        initType(defaultObject);
    }


    public V get(K mapKey) {
        if (enableCache && mapCache != null) {
            V cache = mapCache.get(mapKey);
            if (cache != null) {
                return cache;
            }
        }
        V retObject = getObject(getMapKey(mapKey), getDefaultObject(mapKey));
        if (enableCache) {
            mapCache.put(mapKey, retObject);
            return retObject;
        } else {
            return retObject;
        }
    }

    public void set(K mapKey,V object) {
        setObject(getMapKey(mapKey),object);
        if (enableCache) {
            mapCache.put(mapKey, object);
        }
    }

    private String getMapKey(K mapKey) {
        return getObjectKey() + mapKey;
    }

    private V getDefaultObject(K key) {
        if (mapDefaultObject != null) {
            V value = mapDefaultObject.get(key);
            if (value != null) {
                return value;
            }
        }
        return defaultObject;
    }

    public void reset(K[] keys) {
        for (K k : keys) {
            set(k, getDefaultObject(k));
        }
    }

    public void enableCache(Boolean enableCache) {
        this.enableCache = enableCache;
        mapCache = new HashMap<>();
    }

    public void cleanCache() {
        mapCache = new HashMap<>();
    }

}
