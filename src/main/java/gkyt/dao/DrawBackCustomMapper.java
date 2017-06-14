package gkyt.dao;

import gkyt.model.Drawback;

import java.util.List;
import java.util.Map;

/**
 * Created by lyt38 on 2017/6/14.
 */
public interface DrawBackCustomMapper {
    List<Drawback> getDrawbacks(Map<String, Object> params);

    int countDrawbacks(Map<String, Object> params);

}
