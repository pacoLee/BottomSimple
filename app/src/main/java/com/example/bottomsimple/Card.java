package com.example.bottomsimple;

import java.io.Serializable;
import java.util.ArrayList;

public class Card implements Serializable {

    private String name;
    private String setNumber;
    private ArrayList availability = new ArrayList();
    private ArrayList colorIdentity = new ArrayList();
    private ArrayList colorIndicator = new ArrayList();
    private ArrayList colors = new ArrayList();
    private ArrayList finishes = new ArrayList();
    private String layout;
    private String rarity;
    private ArrayList subTypes = new ArrayList();
    private ArrayList superTypes = new ArrayList();
    private ArrayList Types = new ArrayList();
    private String language;
    private ArrayList keywords = new ArrayList();
    private String uuid;
    private String imagenId;
    private String cost;
    private String type;
    private String text;
    private String power;
    private String toughness;
    private Double manaValue;
    private ArrayList legality = new ArrayList();
    private ArrayList rulings = new ArrayList();
    private int cantidad;

    public Card(){

    }
    public Card(ArrayList availability, ArrayList colorIdentity, ArrayList colorIndicator, ArrayList colors, ArrayList finishes, String layout,
                String rarity, ArrayList subTypes, ArrayList superTypes, ArrayList types, String language, ArrayList keywords,String uuid,
                String imagenId,String cost,String type,String text,Double manaValue,ArrayList legality,ArrayList rulings,String power,String toughness) {
        this.availability = availability;
        this.colorIdentity = colorIdentity;
        this.colorIndicator = colorIndicator;
        this.colors = colors;
        this.finishes = finishes;
        this.layout = layout;
        this.rarity = rarity;
        this.subTypes = subTypes;
        this.superTypes = superTypes;
        Types = types;
        this.language = language;
        this.keywords = keywords;
        this.uuid=uuid;
        this.imagenId=imagenId;
        this.cost=cost;
        this.type=type;
        this.text=text;
        this.manaValue=manaValue;
        this.legality=legality;
        this.rulings=rulings;
        this.power=power;
        this.toughness=toughness;
    }


    public Card(String name, String setNumber, ArrayList availability, ArrayList colorIdentity, ArrayList colorIndicator, ArrayList colors, ArrayList finishes, String layout, String rarity, ArrayList subTypes,
                ArrayList superTypes, ArrayList types, String language, ArrayList keywords,String uuid,String imagenId,
                String cost,String type,String text,Double manaValue,ArrayList legality,ArrayList rulings,String power,String toughness) {
        this.name = name;
        this.setNumber = setNumber;
        this.availability = availability;
        this.colorIdentity = colorIdentity;
        this.colorIndicator = colorIndicator;
        this.colors = colors;
        this.finishes = finishes;
        this.layout = layout;
        this.rarity = rarity;
        this.subTypes = subTypes;
        this.superTypes = superTypes;
        Types = types;
        this.language = language;
        this.keywords = keywords;
        this.uuid=uuid;
        this.imagenId=imagenId;
        this.cost=cost;
        this.type=type;
        this.text=text;
        this.manaValue=manaValue;
        this.legality=legality;
        this.rulings=rulings;
        this.power=power;
        this.toughness=toughness;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }


    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getToughness() {
        return toughness;
    }

    public void setToughness(String toughness) {
        this.toughness = toughness;
    }

    public ArrayList getLegality() {
        return legality;
    }

    public void setLegality(ArrayList legality) {
        this.legality = legality;
    }

    public ArrayList getRulings() {
        return rulings;
    }

    public void setRulings(ArrayList rulings) {
        this.rulings = rulings;
    }

    public Double getManaValue() {
        return manaValue;
    }

    public void setManaValue(Double manaValue) {
        this.manaValue = manaValue;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getImagenId() {
        return imagenId;
    }

    public void setImagenId(String imagenId) {
        this.imagenId = imagenId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(String setNumber) {
        this.setNumber = setNumber;
    }


    public ArrayList getAvailability() {
        return availability;
    }

    public void setAvailability(ArrayList availability) {
        this.availability = availability;
    }

    public ArrayList getColorIdentity() {
        return colorIdentity;
    }

    public void setColorIdentity(ArrayList colorIdentity) {
        this.colorIdentity = colorIdentity;
    }

    public ArrayList getColorIndicator() {
        return colorIndicator;
    }

    public void setColorIndicator(ArrayList colorIndicator) {
        this.colorIndicator = colorIndicator;
    }

    public ArrayList getColors() {
        return colors;
    }

    public void setColors(ArrayList colors) {
        this.colors = colors;
    }

    public ArrayList getFinishes() {
        return finishes;
    }

    public void setFinishes(ArrayList finishes) {
        this.finishes = finishes;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public ArrayList getSubTypes() {
        return subTypes;
    }

    public void setSubTypes(ArrayList subTypes) {
        this.subTypes = subTypes;
    }

    public ArrayList getSuperTypes() {
        return superTypes;
    }

    public void setSuperTypes(ArrayList superTypes) {
        this.superTypes = superTypes;
    }

    public ArrayList getTypes() {
        return Types;
    }

    public void setTypes(ArrayList types) {
        Types = types;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public ArrayList getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList keywords) {
        this.keywords = keywords;
    }
}