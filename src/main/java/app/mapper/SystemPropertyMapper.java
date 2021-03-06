package app.mapper;

import app.model.SystemProperty;
import app.model.SystemPropertyExample;
import app.model.SystemPropertyKey;
import java.util.List;

public interface SystemPropertyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.system_property
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    int deleteByPrimaryKey(SystemPropertyKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.system_property
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    int insert(SystemProperty record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.system_property
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    int insertSelective(SystemProperty record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.system_property
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    List<SystemProperty> selectByExample(SystemPropertyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.system_property
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    SystemProperty selectByPrimaryKey(SystemPropertyKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.system_property
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    int updateByPrimaryKeySelective(SystemProperty record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.system_property
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    int updateByPrimaryKey(SystemProperty record);
}