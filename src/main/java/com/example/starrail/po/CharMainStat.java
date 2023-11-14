package com.example.starrail.po;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table char_main_stat
 */
public class CharMainStat {
    /**
     * Database Column Remarks:
     *   角色主词条id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column char_main_stat.char_main_stat_id
     *
     * @mbg.generated
     */
    private Integer charMainStatId;

    /**
     * Database Column Remarks:
     *   角色id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column char_main_stat.character_id
     *
     * @mbg.generated
     */
    private Integer characterId;

    /**
     * Database Column Remarks:
     *   遗器部位id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column char_main_stat.relic_type_id
     *
     * @mbg.generated
     */
    private Integer relicTypeId;

    /**
     * Database Column Remarks:
     *   词条id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column char_main_stat.stat_id
     *
     * @mbg.generated
     */
    private Integer statId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column char_main_stat.char_main_stat_id
     *
     * @return the value of char_main_stat.char_main_stat_id
     *
     * @mbg.generated
     */
    public Integer getCharMainStatId() {
        return charMainStatId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column char_main_stat.char_main_stat_id
     *
     * @param charMainStatId the value for char_main_stat.char_main_stat_id
     *
     * @mbg.generated
     */
    public void setCharMainStatId(Integer charMainStatId) {
        this.charMainStatId = charMainStatId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column char_main_stat.character_id
     *
     * @return the value of char_main_stat.character_id
     *
     * @mbg.generated
     */
    public Integer getCharacterId() {
        return characterId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column char_main_stat.character_id
     *
     * @param characterId the value for char_main_stat.character_id
     *
     * @mbg.generated
     */
    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column char_main_stat.relic_type_id
     *
     * @return the value of char_main_stat.relic_type_id
     *
     * @mbg.generated
     */
    public Integer getRelicTypeId() {
        return relicTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column char_main_stat.relic_type_id
     *
     * @param relicTypeId the value for char_main_stat.relic_type_id
     *
     * @mbg.generated
     */
    public void setRelicTypeId(Integer relicTypeId) {
        this.relicTypeId = relicTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column char_main_stat.stat_id
     *
     * @return the value of char_main_stat.stat_id
     *
     * @mbg.generated
     */
    public Integer getStatId() {
        return statId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column char_main_stat.stat_id
     *
     * @param statId the value for char_main_stat.stat_id
     *
     * @mbg.generated
     */
    public void setStatId(Integer statId) {
        this.statId = statId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table char_main_stat
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
        CharMainStat other = (CharMainStat) that;
        return (this.getCharMainStatId() == null ? other.getCharMainStatId() == null : this.getCharMainStatId().equals(other.getCharMainStatId()))
            && (this.getCharacterId() == null ? other.getCharacterId() == null : this.getCharacterId().equals(other.getCharacterId()))
            && (this.getRelicTypeId() == null ? other.getRelicTypeId() == null : this.getRelicTypeId().equals(other.getRelicTypeId()))
            && (this.getStatId() == null ? other.getStatId() == null : this.getStatId().equals(other.getStatId()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table char_main_stat
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCharMainStatId() == null) ? 0 : getCharMainStatId().hashCode());
        result = prime * result + ((getCharacterId() == null) ? 0 : getCharacterId().hashCode());
        result = prime * result + ((getRelicTypeId() == null) ? 0 : getRelicTypeId().hashCode());
        result = prime * result + ((getStatId() == null) ? 0 : getStatId().hashCode());
        return result;
    }
}