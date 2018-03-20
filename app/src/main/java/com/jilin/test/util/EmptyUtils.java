package com.jilin.test.util;

import android.support.annotation.Nullable;

import java.util.Collection;
import java.util.Map;

/**
* TextUtils
* @author Lvfl
* created at 2016/9/8 17:47
*/
public class EmptyUtils {

    public static boolean isEmpty(Collection collection) {
        return null == collection || collection.isEmpty();
    }

    public static boolean isEmpty(Map map) {
        return null == map || map.isEmpty();
    }

    public static boolean isEmpty(Object[] objs) {
        return null == objs || objs.length <= 0;
    }

    public static boolean isEmpty(int[] objs) {
        return null == objs || objs.length <= 0;
    }

    public static boolean isEmpty(@Nullable CharSequence charSequence) {
        return null == charSequence || charSequence.toString().trim().length() == 0;
    }

}
