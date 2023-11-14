package com.example.starrail.po;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table stat
 */
public class Stat {
    /**
     * Database Column Remarks:
     *   词条id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column stat.stat_id
     *
     * @mbg.generated
     */
    private Integer statId;

    /**
     * Database Column Remarks:
     *   词条名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column stat.stat_name
     *
     * @mbg.generated
     */
    private String statName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column stat.stat_id
     *
     * @return the value of stat.stat_id
     *
     * @mbg.generated
     */
    public Integer getStatId() {
        return statId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column stat.stat_id
     *
     * @param statId the value for stat.stat_id
     *
     * @mbg.generated
     */
    public void setStatId(Integer statId) {
        this.statId = statId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column stat.stat_name
     *
     * @return the value of stat.stat_name
     *
     * @mbg.generated
     */
    public String getStatName() {
        return statName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column stat.stat_name
     *
     * @param statName the value for stat.stat_name
     *
     * @mbg.generated
     */
    public void setStatName(String statName) {
        this.statName = statName == null ? null : statName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Stat other = (Stat) that;
        return (this.getStatId() == null ? other.getStatId() == null : this.getStatId().equals(other.getStatId()))
            && (this.getStatName() == null ? other.getStatName() == null : this.getStatName().equals(other.getStatName()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getStatId() == null) ? 0 : getStatId().hashCode());
        result = prime * result + ((getStatName() == null) ? 0 : getStatName().hashCode());
        return result;
    }
}