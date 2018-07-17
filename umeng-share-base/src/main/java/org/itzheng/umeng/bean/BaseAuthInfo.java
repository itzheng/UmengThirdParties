package org.itzheng.umeng.bean;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * Title:授权基本信息<br>
 * Description: <br>
 *
 * @email ItZheng@ZoHo.com
 * Created by itzheng on 2018-7-16.
 */
public class BaseAuthInfo implements Serializable {
    /**
     * 用户id，判断登录使用这个就可以
     */
    public String uid;
    /**
     * 用户名
     */
    public String name;
    public String accessToken;
    public String refreshToken;
    /**
     * 过期时间
     */
    public String expiration;
    /**
     * 头像
     */
    public String iconurl;

    /**
     * 将map快速转成对象
     *
     * @param map
     * @return
     */
    public static <T> T mapToObject(Map map, Class<T> clazz) {
        if (clazz == null || map == null || map.isEmpty()) {
            return null;
        }
        Field[] fields = clazz.getDeclaredFields();
        T obj = null;
        try {
            obj = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
        for (Field _field : fields) {
            String fieldName = _field.getName();
            try {
                _field.setAccessible(true);
                _field.set(obj, map.get(fieldName));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }
}
