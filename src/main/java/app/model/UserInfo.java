package app.model;

import java.util.Date;

public class UserInfo extends UserInfoKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.user_info.password
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.user_info.organization_name
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    private String organizationName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.user_info.enabled
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    private Boolean enabled;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.user_info.email
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.user_info.activate_token
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    private String activateToken;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.user_info.activate_limit
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    private Date activateLimit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.user_info.activate_flag
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    private Boolean activateFlag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.user_info.password
     *
     * @return the value of public.user_info.password
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.user_info.password
     *
     * @param password the value for public.user_info.password
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.user_info.organization_name
     *
     * @return the value of public.user_info.organization_name
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    public String getOrganizationName() {
        return organizationName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.user_info.organization_name
     *
     * @param organizationName the value for public.user_info.organization_name
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName == null ? null : organizationName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.user_info.enabled
     *
     * @return the value of public.user_info.enabled
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.user_info.enabled
     *
     * @param enabled the value for public.user_info.enabled
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.user_info.email
     *
     * @return the value of public.user_info.email
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.user_info.email
     *
     * @param email the value for public.user_info.email
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.user_info.activate_token
     *
     * @return the value of public.user_info.activate_token
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    public String getActivateToken() {
        return activateToken;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.user_info.activate_token
     *
     * @param activateToken the value for public.user_info.activate_token
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    public void setActivateToken(String activateToken) {
        this.activateToken = activateToken == null ? null : activateToken.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.user_info.activate_limit
     *
     * @return the value of public.user_info.activate_limit
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    public Date getActivateLimit() {
        return activateLimit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.user_info.activate_limit
     *
     * @param activateLimit the value for public.user_info.activate_limit
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    public void setActivateLimit(Date activateLimit) {
        this.activateLimit = activateLimit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.user_info.activate_flag
     *
     * @return the value of public.user_info.activate_flag
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    public Boolean getActivateFlag() {
        return activateFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.user_info.activate_flag
     *
     * @param activateFlag the value for public.user_info.activate_flag
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    public void setActivateFlag(Boolean activateFlag) {
        this.activateFlag = activateFlag;
    }
}