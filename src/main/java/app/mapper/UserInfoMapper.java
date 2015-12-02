package app.mapper;

import app.model.UserInfo;
import app.model.UserInfoExample;
import app.model.UserInfoKey;
import java.util.List;

public interface UserInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.user_info
     *
     * @mbggenerated Tue Dec 01 18:07:57 JST 2015
     */
    int deleteByPrimaryKey(UserInfoKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.user_info
     *
     * @mbggenerated Tue Dec 01 18:07:57 JST 2015
     */
    int insert(UserInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.user_info
     *
     * @mbggenerated Tue Dec 01 18:07:57 JST 2015
     */
    int insertSelective(UserInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.user_info
     *
     * @mbggenerated Tue Dec 01 18:07:57 JST 2015
     */
    List<UserInfo> selectByExample(UserInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.user_info
     *
     * @mbggenerated Tue Dec 01 18:07:57 JST 2015
     */
    UserInfo selectByPrimaryKey(UserInfoKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.user_info
     *
     * @mbggenerated Tue Dec 01 18:07:57 JST 2015
     */
    int updateByPrimaryKeySelective(UserInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.user_info
     *
     * @mbggenerated Tue Dec 01 18:07:57 JST 2015
     */
    int updateByPrimaryKey(UserInfo record);
}