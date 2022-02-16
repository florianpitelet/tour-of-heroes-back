package com.tour.backend;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;


@Entity
public class Hero {

    //prprietes
    private @Id @GeneratedValue long id;
    private String name;
    private String power;
    

    //constructeur vide
    Hero(){}
    //constructeur
    Hero(String name, String power){
        this.name = name;
        this.power = power;
        

    }

    // getters setters----------------------
    public long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getPower(){
        return this.power;
    }

    

    public void setId(Long id) {
        this.id = id;
      }
    
      public void setName(String name) {
        this.name = name;
      }
    
      public void setPower(String power) {
        this.power = power;
      }

      
//-------------------------------------------------
@Override
public boolean equals(Object o){
    if(this == o)
    return true;
    if(!(o instanceof Hero))
    return false;
    Hero hero = (Hero) o;
    return Objects.equals(this.id, hero.id) &&
           Objects.equals(this.name, hero.name) && 
           Objects.equals(this.power, hero.power);
           
}
@Override
public int hashCode(){
    return Objects.hash(this.id,this.name,this.power);
}

@Override
public String toString(){
    return "Hero{"
     + "id=" + this.id 
     + ", name='" + this.name 
     + '\'' 
     + ", role='" + this.power 
     + '\''
     + '}';}





}
