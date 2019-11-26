package com.sunnylin.preferencecell;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public abstract class PreferenceCellBase<V> {
    protected static Context context;
    protected SharedPreferences preferences;

    protected enum PreferencesType {
        BOOL, INT, LONG, FLOAT, STRING, ENUM
    }

    protected static String defaultFileName = null;
    protected static boolean defaultUseKeyAsFileName = false;

    protected V defaultObject;
    /**
     * The key of the preference object.
     */
    protected String objectKey = "";
    protected PreferencesType type;
    protected Boolean enableCache = true;
    protected boolean useKeyAsFileName = false;

    protected  String prefix = "";

    protected String fileName = null;

    protected void initType(V object) {
        if (object instanceof Enum<?>) {
            type = PreferencesType.ENUM;
        } else if (object instanceof Boolean) {
            type = PreferencesType.BOOL;
        } else if (object instanceof Integer) {
            type = PreferencesType.INT;
        } else if (object instanceof Long) {
            type = PreferencesType.LONG;
        } else if (object instanceof Float) {
            type = PreferencesType.FLOAT;
        } else if (object instanceof String) {
            type = PreferencesType.STRING;
        } else {
            throw new ClassCastException("PreferenceCell is support only to save the value which is the type of Boolean, Int, Long, Float, String or Enum, Please check the type what you to save again.");
        }
    }


    protected V getObject(String key, V defaultObject) {
        preferences = getPreferences();
        Object retObject = null;
        switch (type) {
            case BOOL:
                retObject = preferences.getBoolean(key, (Boolean) defaultObject);
                break;
            case INT:
                retObject = preferences.getInt(key, (Integer) defaultObject);
                break;
            case LONG:
                retObject = preferences.getLong(key, (Long) defaultObject);
                break;
            case FLOAT:
                retObject = preferences.getFloat(key, (Float) defaultObject);
                break;
            case STRING:
                retObject = preferences.getString(key, (String) defaultObject);
                break;
            case ENUM:
                Enum<?> enumDefault = (Enum<?>) defaultObject;
                int original = preferences.getInt(key, enumDefault.ordinal());
                Class<?> enumClass = enumDefault.getClass();
                try {
                    Method method = enumClass.getMethod("values", new Class[0]);
                    Object[] enums = (Object[]) method.invoke(null, new Object[]{});
                    retObject = enums[original];
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
        return (V) retObject;
    }

    protected void setObject(String key,V object) {
        preferences = getPreferences();
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        switch (type) {
            case BOOL:
                preferencesEditor.putBoolean(key, (Boolean) object);
                break;
            case INT:
                preferencesEditor.putInt(key, (Integer) object);
                break;
            case LONG:
                preferencesEditor.putLong(key, (Long) object);
                break;
            case FLOAT:
                preferencesEditor.putFloat(key, (Float) object);
                break;
            case STRING:
                preferencesEditor.putString(key, (String) object);
                break;
            case ENUM:
                preferencesEditor.putInt(key, ((Enum<?>) object).ordinal());
                break;
            default:
                break;
        }
        preferencesEditor.commit();
    }

    public void setObjectKey(String objectKey) {
        this.objectKey = objectKey;
    }

    public V getDefault() {
        return defaultObject;
    }

    public Context getDefaultContext() {
        return context;
    }

    public abstract void cleanCache();

    protected String getObjectKey() {
        return prefix + objectKey;
    }

    public  void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    protected SharedPreferences getPreferences() {
        return context.getSharedPreferences(getFileName(), Context.MODE_PRIVATE);
    }

    public static String getDefaultFileName() {
        if (TextUtils.isEmpty(defaultFileName)) {
            return context.getPackageName() + "_share_preferences";
        } else {
            return defaultFileName;
        }
    }

    public static void setDefaultFileName(String defaultFileName) {
        PreferenceCellBase.defaultFileName = defaultFileName;
    }

    public static void setDefaultUseKeyAsFileName(boolean defaultUseKeyAsFileName) {
        PreferenceCellBase.defaultUseKeyAsFileName = defaultUseKeyAsFileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        if (!TextUtils.isEmpty(fileName)) {
            return fileName;
        }
        if (useKeyAsFileName || defaultUseKeyAsFileName) {
            return getObjectKey();
        }
        return getDefaultFileName();
    }

    public static void init(Context context, Class<?> tClass) {
        PreferenceCellBase.context = context;
        Field[] fields = tClass.getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                if (PreferenceCellBase.class.isAssignableFrom(field.getType())) {
                    try {
                        PreferenceCellBase preferenceCellBase = (PreferenceCellBase) field.get(null);
                        if (preferenceCellBase != null && TextUtils.isEmpty(preferenceCellBase.getObjectKey())) {
                            preferenceCellBase.setObjectKey(field.getName());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}
