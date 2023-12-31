package com.example.starrail.po;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table char_relic_set
 */
public class CharRelicSet {
    /**
     * Database Column Remarks:
     *   角色遗器套装id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column char_relic_set.char_relic_set_id
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    private Integer charRelicSetId;

    /**
     * Database Column Remarks:
     *   角色id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column char_relic_set.character_id
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    private Integer characterId;

    /**
     * Database Column Remarks:
     *   遗器套装id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column char_relic_set.relic_set_id
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    private Integer relicSetId;

    /**
     * Database Column Remarks:
     *   遗器套装类型，外圈还是内圈，外圈1，内圈2
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column char_relic_set.relic_set_type
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    private Integer relicSetType;

    /**
     * Database Column Remarks:
     *   效果需求，半套1，全套2
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column char_relic_set.effect_demand
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    private Integer effectDemand;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column char_relic_set.char_relic_set_id
     *
     * @return the value of char_relic_set.char_relic_set_id
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    public Integer getCharRelicSetId() {
        return charRelicSetId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column char_relic_set.char_relic_set_id
     *
     * @param charRelicSetId the value for char_relic_set.char_relic_set_id
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    public void setCharRelicSetId(Integer charRelicSetId) {
        this.charRelicSetId = charRelicSetId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column char_relic_set.character_id
     *
     * @return the value of char_relic_set.character_id
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    public Integer getCharacterId() {
        return characterId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column char_relic_set.character_id
     *
     * @param characterId the value for char_relic_set.character_id
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column char_relic_set.relic_set_id
     *
     * @return the value of char_relic_set.relic_set_id
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    public Integer getRelicSetId() {
        return relicSetId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column char_relic_set.relic_set_id
     *
     * @param relicSetId the value for char_relic_set.relic_set_id
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    public void setRelicSetId(Integer relicSetId) {
        this.relicSetId = relicSetId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column char_relic_set.relic_set_type
     *
     * @return the value of char_relic_set.relic_set_type
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    public Integer getRelicSetType() {
        return relicSetType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column char_relic_set.relic_set_type
     *
     * @param relicSetType the value for char_relic_set.relic_set_type
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    public void setRelicSetType(Integer relicSetType) {
        this.relicSetType = relicSetType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column char_relic_set.effect_demand
     *
     * @return the value of char_relic_set.effect_demand
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    public Integer getEffectDemand() {
        return effectDemand;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column char_relic_set.effect_demand
     *
     * @param effectDemand the value for char_relic_set.effect_demand
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    public void setEffectDemand(Integer effectDemand) {
        this.effectDemand = effectDemand;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table char_relic_set
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
        CharRelicSet other = (CharRelicSet) that;
        return (this.getCharRelicSetId() == null ? other.getCharRelicSetId() == null : this.getCharRelicSetId().equals(other.getCharRelicSetId()))
            && (this.getCharacterId() == null ? other.getCharacterId() == null : this.getCharacterId().equals(other.getCharacterId()))
            && (this.getRelicSetId() == null ? other.getRelicSetId() == null : this.getRelicSetId().equals(other.getRelicSetId()))
            && (this.getRelicSetType() == null ? other.getRelicSetType() == null : this.getRelicSetType().equals(other.getRelicSetType()))
            && (this.getEffectDemand() == null ? other.getEffectDemand() == null : this.getEffectDemand().equals(other.getEffectDemand()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table char_relic_set
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCharRelicSetId() == null) ? 0 : getCharRelicSetId().hashCode());
        result = prime * result + ((getCharacterId() == null) ? 0 : getCharacterId().hashCode());
        result = prime * result + ((getRelicSetId() == null) ? 0 : getRelicSetId().hashCode());
        result = prime * result + ((getRelicSetType() == null) ? 0 : getRelicSetType().hashCode());
        result = prime * result + ((getEffectDemand() == null) ? 0 : getEffectDemand().hashCode());
        return result;
    }
}