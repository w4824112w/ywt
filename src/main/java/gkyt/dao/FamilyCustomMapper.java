package gkyt.dao;

import gkyt.model.Family;

import java.util.List;
import java.util.Map;

/**
 * Created by lyt38 on 2017/6/12.
 */
public interface FamilyCustomMapper {
    List<Family> getFamilies(Map<String, Object> params);

    int countFamilies(Map<String, Object> params);

}
