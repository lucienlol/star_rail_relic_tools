package com.example.starrail.po;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table char_stat
 */
public class CharStat {
    /**
     * Database Column Remarks:
     *   角色词条id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column char_stat.char_stat_id
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    private Integer charStatId;

    /**
     * Database Column Remarks:
     *   角色id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column char_stat.character_id
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    private Integer characterId;

    /**
     * Database Column Remarks:
     *   词条id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column char_stat.stat_id
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    private Integer statId;

    /**
     * Database Column Remarks:
     *   词条优先级
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column char_stat.priority
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    private Integer priority;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column char_stat.char_stat_id
     *
     * @return the value of char_stat.char_stat_id
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    public Integer getCharStatId() {
        return charStatId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column char_stat.char_stat_id
     *
     * @param charStatId the value for char_stat.char_stat_id
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    public void setCharStatId(Integer charStatId) {
        this.charStatId = charStatId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column char_stat.character_id
     *
     * @return the value of char_stat.character_id
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    public Integer getCharacterId() {
        return characterId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column char_stat.character_id
     *
     * @param characterId the value for char_stat.character_id
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column char_stat.stat_id
     *
     * @return the value of char_stat.stat_id
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    public Integer getStatId() {
        return statId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column char_stat.stat_id
     *
     * @param statId the value for char_stat.stat_id
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    public void setStatId(Integer statId) {
        this.statId = statId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column char_stat.priority
     *
     * @return the value of char_stat.priority
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column char_stat.priority
     *
     * @param priority the value for char_stat.priority
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table char_stat
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
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
        CharStat other = (CharStat) that;
        return (this.getCharStatId() == null ? other.getCharStatId() == null : this.getCharStatId().equals(other.getCharStatId()))
            && (this.getCharacterId() == null ? other.getCharacterId() == null : this.getCharacterId().equals(other.getCharacterId()))
            && (this.getStatId() == null ? other.getStatId() == null : this.getStatId().equals(other.getStatId()))
            && (this.getPriority() == null ? other.getPriority() == null : this.getPriority().equals(other.getPriority()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table char_stat
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCharStatId() == null) ? 0 : getCharStatId().hashCode());
        result = prime * result + ((getCharacterId() == null) ? 0 : getCharacterId().hashCode());
        result = prime * result + ((getStatId() == null) ? 0 : getStatId().hashCode());
        result = prime * result + ((getPriority() == null) ? 0 : getPriority().hashCode());
        return result;
    }
}