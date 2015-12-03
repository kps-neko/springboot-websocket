package app.mapper;

import app.model.SchemaVersion;
import app.model.SchemaVersionExample;
import app.model.SchemaVersionKey;
import java.util.List;

public interface SchemaVersionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.schema_version
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    int deleteByPrimaryKey(SchemaVersionKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.schema_version
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    int insert(SchemaVersion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.schema_version
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    int insertSelective(SchemaVersion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.schema_version
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    List<SchemaVersion> selectByExample(SchemaVersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.schema_version
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    SchemaVersion selectByPrimaryKey(SchemaVersionKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.schema_version
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    int updateByPrimaryKeySelective(SchemaVersion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.schema_version
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    int updateByPrimaryKey(SchemaVersion record);
}