package gkyt.dao;

import gkyt.model.Jail;

import java.util.List;
import java.util.Map;

/**
 * Created by lyt38 on 2017/6/9.
 */
public interface JailCustomMapper {
    List<Jail> getJails(Map<String, Object> params);

    int countJails(Map<String, Object> params);

}
