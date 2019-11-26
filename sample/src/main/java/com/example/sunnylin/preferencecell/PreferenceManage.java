package com.example.sunnylin.preferencecell;

import com.sunnylin.preferencecell.PreferenceCell;
import com.sunnylin.preferencecell.PreferenceMapCell;

public class PreferenceManage {

    public final static PreferenceCell<Boolean> BOOL_TYPE = new PreferenceCell<>(true);
    public final static PreferenceCell<Integer> INT_TYPE = new PreferenceCell<>(999);
    public final static PreferenceCell<Long> LONG_TYPE = new PreferenceCell<>(0L);
    public final static PreferenceCell<Float> FLOAT_TYPE = new PreferenceCell<>(0.f);
    public final static PreferenceCell<String> STRING_TYPE = new PreferenceCell<>("Empty String");
    public final static PreferenceCell<ExampleEnum> ENUM_TYPE = new PreferenceCell<>(ExampleEnum.A);
    public final static PreferenceMapCell<Integer, ExampleEnum> MAP_INTEGER_TYPE = new PreferenceMapCell<>(Integer.class, ExampleEnum.C, "IntTypeMap");
    public final static PreferenceMapCell<ExampleEnum,Integer> MAP_ENUM_TYPE = new PreferenceMapCell<>(ExampleEnum.class,0);


    static {
        PreferenceCell.init(ExampleApplication.getInstance(), PreferenceManage.class);
    }
}
