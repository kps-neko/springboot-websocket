package app.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BulletinBoardDataExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table public.bulletin_board_data
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table public.bulletin_board_data
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table public.bulletin_board_data
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.bulletin_board_data
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    public BulletinBoardDataExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.bulletin_board_data
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.bulletin_board_data
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.bulletin_board_data
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.bulletin_board_data
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.bulletin_board_data
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.bulletin_board_data
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.bulletin_board_data
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.bulletin_board_data
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.bulletin_board_data
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.bulletin_board_data
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table public.bulletin_board_data
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andPostingDateIsNull() {
            addCriterion("posting_date is null");
            return (Criteria) this;
        }

        public Criteria andPostingDateIsNotNull() {
            addCriterion("posting_date is not null");
            return (Criteria) this;
        }

        public Criteria andPostingDateEqualTo(Date value) {
            addCriterion("posting_date =", value, "postingDate");
            return (Criteria) this;
        }

        public Criteria andPostingDateNotEqualTo(Date value) {
            addCriterion("posting_date <>", value, "postingDate");
            return (Criteria) this;
        }

        public Criteria andPostingDateGreaterThan(Date value) {
            addCriterion("posting_date >", value, "postingDate");
            return (Criteria) this;
        }

        public Criteria andPostingDateGreaterThanOrEqualTo(Date value) {
            addCriterion("posting_date >=", value, "postingDate");
            return (Criteria) this;
        }

        public Criteria andPostingDateLessThan(Date value) {
            addCriterion("posting_date <", value, "postingDate");
            return (Criteria) this;
        }

        public Criteria andPostingDateLessThanOrEqualTo(Date value) {
            addCriterion("posting_date <=", value, "postingDate");
            return (Criteria) this;
        }

        public Criteria andPostingDateIn(List<Date> values) {
            addCriterion("posting_date in", values, "postingDate");
            return (Criteria) this;
        }

        public Criteria andPostingDateNotIn(List<Date> values) {
            addCriterion("posting_date not in", values, "postingDate");
            return (Criteria) this;
        }

        public Criteria andPostingDateBetween(Date value1, Date value2) {
            addCriterion("posting_date between", value1, value2, "postingDate");
            return (Criteria) this;
        }

        public Criteria andPostingDateNotBetween(Date value1, Date value2) {
            addCriterion("posting_date not between", value1, value2, "postingDate");
            return (Criteria) this;
        }

        public Criteria andPostingContentIsNull() {
            addCriterion("posting_content is null");
            return (Criteria) this;
        }

        public Criteria andPostingContentIsNotNull() {
            addCriterion("posting_content is not null");
            return (Criteria) this;
        }

        public Criteria andPostingContentEqualTo(String value) {
            addCriterion("posting_content =", value, "postingContent");
            return (Criteria) this;
        }

        public Criteria andPostingContentNotEqualTo(String value) {
            addCriterion("posting_content <>", value, "postingContent");
            return (Criteria) this;
        }

        public Criteria andPostingContentGreaterThan(String value) {
            addCriterion("posting_content >", value, "postingContent");
            return (Criteria) this;
        }

        public Criteria andPostingContentGreaterThanOrEqualTo(String value) {
            addCriterion("posting_content >=", value, "postingContent");
            return (Criteria) this;
        }

        public Criteria andPostingContentLessThan(String value) {
            addCriterion("posting_content <", value, "postingContent");
            return (Criteria) this;
        }

        public Criteria andPostingContentLessThanOrEqualTo(String value) {
            addCriterion("posting_content <=", value, "postingContent");
            return (Criteria) this;
        }

        public Criteria andPostingContentLike(String value) {
            addCriterion("posting_content like", value, "postingContent");
            return (Criteria) this;
        }

        public Criteria andPostingContentNotLike(String value) {
            addCriterion("posting_content not like", value, "postingContent");
            return (Criteria) this;
        }

        public Criteria andPostingContentIn(List<String> values) {
            addCriterion("posting_content in", values, "postingContent");
            return (Criteria) this;
        }

        public Criteria andPostingContentNotIn(List<String> values) {
            addCriterion("posting_content not in", values, "postingContent");
            return (Criteria) this;
        }

        public Criteria andPostingContentBetween(String value1, String value2) {
            addCriterion("posting_content between", value1, value2, "postingContent");
            return (Criteria) this;
        }

        public Criteria andPostingContentNotBetween(String value1, String value2) {
            addCriterion("posting_content not between", value1, value2, "postingContent");
            return (Criteria) this;
        }

        public Criteria andRegisterDateIsNull() {
            addCriterion("register_date is null");
            return (Criteria) this;
        }

        public Criteria andRegisterDateIsNotNull() {
            addCriterion("register_date is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterDateEqualTo(Date value) {
            addCriterion("register_date =", value, "registerDate");
            return (Criteria) this;
        }

        public Criteria andRegisterDateNotEqualTo(Date value) {
            addCriterion("register_date <>", value, "registerDate");
            return (Criteria) this;
        }

        public Criteria andRegisterDateGreaterThan(Date value) {
            addCriterion("register_date >", value, "registerDate");
            return (Criteria) this;
        }

        public Criteria andRegisterDateGreaterThanOrEqualTo(Date value) {
            addCriterion("register_date >=", value, "registerDate");
            return (Criteria) this;
        }

        public Criteria andRegisterDateLessThan(Date value) {
            addCriterion("register_date <", value, "registerDate");
            return (Criteria) this;
        }

        public Criteria andRegisterDateLessThanOrEqualTo(Date value) {
            addCriterion("register_date <=", value, "registerDate");
            return (Criteria) this;
        }

        public Criteria andRegisterDateIn(List<Date> values) {
            addCriterion("register_date in", values, "registerDate");
            return (Criteria) this;
        }

        public Criteria andRegisterDateNotIn(List<Date> values) {
            addCriterion("register_date not in", values, "registerDate");
            return (Criteria) this;
        }

        public Criteria andRegisterDateBetween(Date value1, Date value2) {
            addCriterion("register_date between", value1, value2, "registerDate");
            return (Criteria) this;
        }

        public Criteria andRegisterDateNotBetween(Date value1, Date value2) {
            addCriterion("register_date not between", value1, value2, "registerDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table public.bulletin_board_data
     *
     * @mbggenerated do_not_delete_during_merge Thu Dec 03 21:22:56 JST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table public.bulletin_board_data
     *
     * @mbggenerated Thu Dec 03 21:22:56 JST 2015
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}