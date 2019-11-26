package com.sunnylin.preferencecell;

/**
 * Created by SunnyLin on 2017/09/16.
 */
public class PreferenceCell<V> extends PreferenceCellBase<V> {

    private V cache;

    public PreferenceCell(V defaultObject) {
        this(defaultObject, "");
    }

    public PreferenceCell(V defaultObject, String key) {
        this.defaultObject = defaultObject;
        this.objectKey = key;
        initType(defaultObject);
    }

    public V getDefault() {
        return defaultObject;
    }

    public V get() {
        if (cache != null) {
            return cache;
        }
        V retObject = getObject(getObjectKey(),defaultObject);
        if (enableCache) {
            cache = retObject;
            return cache;
        } else {
            return retObject;
        }
    }

    public void set(V object) {
        setObject(getObjectKey(),object);
        if (enableCache) {
            cache = object;
        }
    }
    public void reset() {
        set(defaultObject);
    }

    public void enableCache(Boolean enableCache) {
        this.enableCache = enableCache;
        cache = null;
    }

    public void cleanCache() {
        cache = null;
    }

}
