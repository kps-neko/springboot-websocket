package app.model;

public class SystemProperty extends SystemPropertyKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.system_property.value
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    private String value;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.system_property.value
     *
     * @return the value of public.system_property.value
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    public String getValue() {
        return value;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.system_property.value
     *
     * @param value the value for public.system_property.value
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }
}