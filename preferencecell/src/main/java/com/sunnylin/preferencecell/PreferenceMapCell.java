package com.sunnylin.preferencecell;

import android.content.SharedPreferences;

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

    public PreferenceMapCell(Class<K> keyClass, V defaultObject, String fileName) {
        this(keyClass, defaultObject);
        this.fileName = fileName;
    }

    public PreferenceMapCell(Map<K, V> mapDefaultObject, String fileName) {
        this(mapDefaultObject);
        this.fileName = fileName;
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
        return getObjectKey() + "_" + mapKey;
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

    /**
     * Mark in reset <em>all</em> values from the file
     * preferences. Make sure the file is only use in this Cell
     * or you have disable the Cache of other cells which use the file also.
     */
    public void resetFile() {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.clear();
        editor.commit();
        cleanCache();
    }

    public void enableCache(Boolean enableCache) {
        this.enableCache = enableCache;
        mapCache = new HashMap<>();
    }

    public void cleanCache() {
        mapCache = new HashMap<>();
    }

}
