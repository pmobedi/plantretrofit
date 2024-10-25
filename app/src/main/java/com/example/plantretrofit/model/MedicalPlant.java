package com.example.plantretrofit.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "MedicalPlants")
public class MedicalPlant {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")  // استفاده از حروف کوچک برای مطابقت با اسکیمای دیتابیس
    @SerializedName("Id")
    private int id;

    @ColumnInfo(name = "plantsName")  // مطابقت نام ستون
    @SerializedName("PlantsName")
    private String plantsName;

    @ColumnInfo(name = "scName")  // نام ستون در دیتابیس: scName
    @SerializedName("SCName")
    private String scientificName;

    @ColumnInfo(name = "family")  // مطابقت با دیتابیس
    @SerializedName("Family")
    private String family;

    @ColumnInfo(name = "description")
    @SerializedName("Description")
    private String description;

    @ColumnInfo(name = "chemicalCompounds")
    @SerializedName("ChemicalCompounds")
    private String chemicalCompounds;

    @ColumnInfo(name = "habitat")
    @SerializedName("Habitat")
    private String habitat;

    @ColumnInfo(name = "agriculture")
    @SerializedName("Agriculture")
    private String agriculture;

    @ColumnInfo(name = "soilType")
    @SerializedName("SoilType")
    private String soilType;

    @ColumnInfo(name = "waterReq")
    @SerializedName("WaterReq")
    private String waterRequirements;

    @ColumnInfo(name = "kodeNeeds")
    @SerializedName("KodeNeeds")
    private String codeNeeds;

    public Integer getFav() {
        return fav;
    }

    @ColumnInfo(name = "disease")
    @SerializedName("Disease")
    private String disease;

    @ColumnInfo(name = "flowring")
    @SerializedName("Flowring")
    private String flowring;

    @ColumnInfo(name = "properties")
    @SerializedName("Properties")
    private String properties;


    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public void setFav(Integer fav) {
        this.fav = fav;
    }

    @ColumnInfo(name = "img", typeAffinity = ColumnInfo.BLOB)
    @SerializedName("Img")
    public byte[] img; // بایت‌ها برای ذخیره به عنوان BLOB

    @ColumnInfo(name = "img2")
    @SerializedName("Img2")
    private String img2;

    @ColumnInfo(name = "fav")
    @SerializedName("Fav")
    private Integer fav;


    // Getters و Setters برای هر فیلد
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlantsName() {
        return plantsName;
    }

    public void setPlantsName(String plantsName) {
        this.plantsName = plantsName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChemicalCompounds() {
        return chemicalCompounds;
    }

    public void setChemicalCompounds(String chemicalCompounds) {
        this.chemicalCompounds = chemicalCompounds;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getAgriculture() {
        return agriculture;
    }

    public void setAgriculture(String agriculture) {
        this.agriculture = agriculture;
    }

    public String getSoilType() {
        return soilType;
    }

    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }

    public String getWaterRequirements() {
        return waterRequirements;
    }

    public void setWaterRequirements(String waterRequirements) {
        this.waterRequirements = waterRequirements;
    }

    public String getCodeNeeds() {
        return codeNeeds;
    }

    public void setCodeNeeds(String codeNeeds) {
        this.codeNeeds = codeNeeds;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getFlowring() {
        return flowring;
    }

    public void setFlowring(String flowering) {
        this.flowring = flowering;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }



    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }


}
