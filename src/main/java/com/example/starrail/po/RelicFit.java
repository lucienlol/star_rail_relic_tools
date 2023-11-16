package com.example.starrail.po;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table relic_fit
 */
public class RelicFit {
    /**
     * Database Column Remarks:
     *   遗器适配id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column relic_fit.relic_fit_id
     *
     * @mbg.generated
     */
    private Integer relicFitId;

    /**
     * Database Column Remarks:
     *   遗器id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column relic_fit.relic_id
     *
     * @mbg.generated
     */
    private Integer relicId;

    /**
     * Database Column Remarks:
     *   角色id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column relic_fit.character_id
     *
     * @mbg.generated
     */
    private Integer characterId;

    /**
     * Database Column Remarks:
     *   主词条是否正确
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column relic_fit.is_main_stat_fit
     *
     * @mbg.generated
     */
    private Boolean isMainStatFit;

    /**
     * Database Column Remarks:
     *   套装效果是否正确
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column relic_fit.is_relic_set_fit
     *
     * @mbg.generated
     */
    private Boolean isRelicSetFit;

    /**
     * Database Column Remarks:
     *   副词条适配度
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column relic_fit.sub_stat_fitness
     *
     * @mbg.generated
     */
    private Double subStatFitness;

    /**
     * Database Column Remarks:
     *   副词条适配详情
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column relic_fit.sub_stat_fit_desc
     *
     * @mbg.generated
     */
    private String subStatFitDesc;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column relic_fit.relic_fit_id
     *
     * @return the value of relic_fit.relic_fit_id
     *
     * @mbg.generated
     */
    public Integer getRelicFitId() {
        return relicFitId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column relic_fit.relic_fit_id
     *
     * @param relicFitId the value for relic_fit.relic_fit_id
     *
     * @mbg.generated
     */
    public void setRelicFitId(Integer relicFitId) {
        this.relicFitId = relicFitId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column relic_fit.relic_id
     *
     * @return the value of relic_fit.relic_id
     *
     * @mbg.generated
     */
    public Integer getRelicId() {
        return relicId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column relic_fit.relic_id
     *
     * @param relicId the value for relic_fit.relic_id
     *
     * @mbg.generated
     */
    public void setRelicId(Integer relicId) {
        this.relicId = relicId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column relic_fit.character_id
     *
     * @return the value of relic_fit.character_id
     *
     * @mbg.generated
     */
    public Integer getCharacterId() {
        return characterId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column relic_fit.character_id
     *
     * @param characterId the value for relic_fit.character_id
     *
     * @mbg.generated
     */
    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column relic_fit.is_main_stat_fit
     *
     * @return the value of relic_fit.is_main_stat_fit
     *
     * @mbg.generated
     */
    public Boolean getIsMainStatFit() {
        return isMainStatFit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column relic_fit.is_main_stat_fit
     *
     * @param isMainStatFit the value for relic_fit.is_main_stat_fit
     *
     * @mbg.generated
     */
    public void setIsMainStatFit(Boolean isMainStatFit) {
        this.isMainStatFit = isMainStatFit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column relic_fit.is_relic_set_fit
     *
     * @return the value of relic_fit.is_relic_set_fit
     *
     * @mbg.generated
     */
    public Boolean getIsRelicSetFit() {
        return isRelicSetFit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column relic_fit.is_relic_set_fit
     *
     * @param isRelicSetFit the value for relic_fit.is_relic_set_fit
     *
     * @mbg.generated
     */
    public void setIsRelicSetFit(Boolean isRelicSetFit) {
        this.isRelicSetFit = isRelicSetFit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column relic_fit.sub_stat_fitness
     *
     * @return the value of relic_fit.sub_stat_fitness
     *
     * @mbg.generated
     */
    public Double getSubStatFitness() {
        return subStatFitness;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column relic_fit.sub_stat_fitness
     *
     * @param subStatFitness the value for relic_fit.sub_stat_fitness
     *
     * @mbg.generated
     */
    public void setSubStatFitness(Double subStatFitness) {
        this.subStatFitness = subStatFitness;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column relic_fit.sub_stat_fit_desc
     *
     * @return the value of relic_fit.sub_stat_fit_desc
     *
     * @mbg.generated
     */
    public String getSubStatFitDesc() {
        return subStatFitDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column relic_fit.sub_stat_fit_desc
     *
     * @param subStatFitDesc the value for relic_fit.sub_stat_fit_desc
     *
     * @mbg.generated
     */
    public void setSubStatFitDesc(String subStatFitDesc) {
        this.subStatFitDesc = subStatFitDesc == null ? null : subStatFitDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table relic_fit
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
        RelicFit other = (RelicFit) that;
        return (this.getRelicFitId() == null ? other.getRelicFitId() == null : this.getRelicFitId().equals(other.getRelicFitId()))
            && (this.getRelicId() == null ? other.getRelicId() == null : this.getRelicId().equals(other.getRelicId()))
            && (this.getCharacterId() == null ? other.getCharacterId() == null : this.getCharacterId().equals(other.getCharacterId()))
            && (this.getIsMainStatFit() == null ? other.getIsMainStatFit() == null : this.getIsMainStatFit().equals(other.getIsMainStatFit()))
            && (this.getIsRelicSetFit() == null ? other.getIsRelicSetFit() == null : this.getIsRelicSetFit().equals(other.getIsRelicSetFit()))
            && (this.getSubStatFitness() == null ? other.getSubStatFitness() == null : this.getSubStatFitness().equals(other.getSubStatFitness()))
            && (this.getSubStatFitDesc() == null ? other.getSubStatFitDesc() == null : this.getSubStatFitDesc().equals(other.getSubStatFitDesc()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table relic_fit
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRelicFitId() == null) ? 0 : getRelicFitId().hashCode());
        result = prime * result + ((getRelicId() == null) ? 0 : getRelicId().hashCode());
        result = prime * result + ((getCharacterId() == null) ? 0 : getCharacterId().hashCode());
        result = prime * result + ((getIsMainStatFit() == null) ? 0 : getIsMainStatFit().hashCode());
        result = prime * result + ((getIsRelicSetFit() == null) ? 0 : getIsRelicSetFit().hashCode());
        result = prime * result + ((getSubStatFitness() == null) ? 0 : getSubStatFitness().hashCode());
        result = prime * result + ((getSubStatFitDesc() == null) ? 0 : getSubStatFitDesc().hashCode());
        return result;
    }
}