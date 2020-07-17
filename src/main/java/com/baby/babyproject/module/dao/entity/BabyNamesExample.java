package com.baby.babyproject.module.dao.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BabyNamesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BabyNamesExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andBabyNameIsNull() {
            addCriterion("baby_name is null");
            return (Criteria) this;
        }

        public Criteria andBabyNameIsNotNull() {
            addCriterion("baby_name is not null");
            return (Criteria) this;
        }

        public Criteria andBabyNameEqualTo(String value) {
            addCriterion("baby_name =", value, "babyName");
            return (Criteria) this;
        }

        public Criteria andBabyNameNotEqualTo(String value) {
            addCriterion("baby_name <>", value, "babyName");
            return (Criteria) this;
        }

        public Criteria andBabyNameGreaterThan(String value) {
            addCriterion("baby_name >", value, "babyName");
            return (Criteria) this;
        }

        public Criteria andBabyNameGreaterThanOrEqualTo(String value) {
            addCriterion("baby_name >=", value, "babyName");
            return (Criteria) this;
        }

        public Criteria andBabyNameLessThan(String value) {
            addCriterion("baby_name <", value, "babyName");
            return (Criteria) this;
        }

        public Criteria andBabyNameLessThanOrEqualTo(String value) {
            addCriterion("baby_name <=", value, "babyName");
            return (Criteria) this;
        }

        public Criteria andBabyNameLike(String value) {
            addCriterion("baby_name like", value, "babyName");
            return (Criteria) this;
        }

        public Criteria andBabyNameNotLike(String value) {
            addCriterion("baby_name not like", value, "babyName");
            return (Criteria) this;
        }

        public Criteria andBabyNameIn(List<String> values) {
            addCriterion("baby_name in", values, "babyName");
            return (Criteria) this;
        }

        public Criteria andBabyNameNotIn(List<String> values) {
            addCriterion("baby_name not in", values, "babyName");
            return (Criteria) this;
        }

        public Criteria andBabyNameBetween(String value1, String value2) {
            addCriterion("baby_name between", value1, value2, "babyName");
            return (Criteria) this;
        }

        public Criteria andBabyNameNotBetween(String value1, String value2) {
            addCriterion("baby_name not between", value1, value2, "babyName");
            return (Criteria) this;
        }

        public Criteria andCommitTimeIsNull() {
            addCriterion("commit_time is null");
            return (Criteria) this;
        }

        public Criteria andCommitTimeIsNotNull() {
            addCriterion("commit_time is not null");
            return (Criteria) this;
        }

        public Criteria andCommitTimeEqualTo(Date value) {
            addCriterion("commit_time =", value, "commitTime");
            return (Criteria) this;
        }

        public Criteria andCommitTimeNotEqualTo(Date value) {
            addCriterion("commit_time <>", value, "commitTime");
            return (Criteria) this;
        }

        public Criteria andCommitTimeGreaterThan(Date value) {
            addCriterion("commit_time >", value, "commitTime");
            return (Criteria) this;
        }

        public Criteria andCommitTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("commit_time >=", value, "commitTime");
            return (Criteria) this;
        }

        public Criteria andCommitTimeLessThan(Date value) {
            addCriterion("commit_time <", value, "commitTime");
            return (Criteria) this;
        }

        public Criteria andCommitTimeLessThanOrEqualTo(Date value) {
            addCriterion("commit_time <=", value, "commitTime");
            return (Criteria) this;
        }

        public Criteria andCommitTimeIn(List<Date> values) {
            addCriterion("commit_time in", values, "commitTime");
            return (Criteria) this;
        }

        public Criteria andCommitTimeNotIn(List<Date> values) {
            addCriterion("commit_time not in", values, "commitTime");
            return (Criteria) this;
        }

        public Criteria andCommitTimeBetween(Date value1, Date value2) {
            addCriterion("commit_time between", value1, value2, "commitTime");
            return (Criteria) this;
        }

        public Criteria andCommitTimeNotBetween(Date value1, Date value2) {
            addCriterion("commit_time not between", value1, value2, "commitTime");
            return (Criteria) this;
        }

        public Criteria andUesrIdIsNull() {
            addCriterion("uesr_id is null");
            return (Criteria) this;
        }

        public Criteria andUesrIdIsNotNull() {
            addCriterion("uesr_id is not null");
            return (Criteria) this;
        }

        public Criteria andUesrIdEqualTo(String value) {
            addCriterion("uesr_id =", value, "uesrId");
            return (Criteria) this;
        }

        public Criteria andUesrIdNotEqualTo(String value) {
            addCriterion("uesr_id <>", value, "uesrId");
            return (Criteria) this;
        }

        public Criteria andUesrIdGreaterThan(String value) {
            addCriterion("uesr_id >", value, "uesrId");
            return (Criteria) this;
        }

        public Criteria andUesrIdGreaterThanOrEqualTo(String value) {
            addCriterion("uesr_id >=", value, "uesrId");
            return (Criteria) this;
        }

        public Criteria andUesrIdLessThan(String value) {
            addCriterion("uesr_id <", value, "uesrId");
            return (Criteria) this;
        }

        public Criteria andUesrIdLessThanOrEqualTo(String value) {
            addCriterion("uesr_id <=", value, "uesrId");
            return (Criteria) this;
        }

        public Criteria andUesrIdLike(String value) {
            addCriterion("uesr_id like", value, "uesrId");
            return (Criteria) this;
        }

        public Criteria andUesrIdNotLike(String value) {
            addCriterion("uesr_id not like", value, "uesrId");
            return (Criteria) this;
        }

        public Criteria andUesrIdIn(List<String> values) {
            addCriterion("uesr_id in", values, "uesrId");
            return (Criteria) this;
        }

        public Criteria andUesrIdNotIn(List<String> values) {
            addCriterion("uesr_id not in", values, "uesrId");
            return (Criteria) this;
        }

        public Criteria andUesrIdBetween(String value1, String value2) {
            addCriterion("uesr_id between", value1, value2, "uesrId");
            return (Criteria) this;
        }

        public Criteria andUesrIdNotBetween(String value1, String value2) {
            addCriterion("uesr_id not between", value1, value2, "uesrId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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