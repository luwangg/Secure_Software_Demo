package edu.hm.bugcoin.domain;
/*
 * Created by shreaker on 14.10.16.
 */

import edu.hm.bugcoin.service.BankAccountService;
import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.io.Serializable;


/**
 *
 */
@Entity
@Component
public class Voucher implements Serializable{

    // ----------------------------------------------------------------------------------
    //  Member variable
    // ----------------------------------------------------------------------------------

    private static final long serialVersionUID = 1L;

    @Id
    @Column(unique = true)
    private Long code;

    @Column(nullable = false)
    private Float value;

    @Column(nullable = false, name = "isreedemed")
    private Boolean isReedemed;


    // ----------------------------------------------------------------------------------
    //  Constructor
    // ----------------------------------------------------------------------------------

    public Voucher() { }

    public Voucher(final Long code, final Float value, final Boolean isReedemed)
    {
        this.code = code;
        this.value = value;
        this.isReedemed = isReedemed;
    }


    // ----------------------------------------------------------------------------------
    //  Getter
    // ----------------------------------------------------------------------------------
    public Long getCode() {
        return code;
    }

    public Float getValue() {
        return value;
    }

    public Boolean isReedemed() {
        return isReedemed;
    }

    // ----------------------------------------------------------------------------------
    //  Setter
    // ----------------------------------------------------------------------------------
    public void setCode(final Long code) {
        this.code = code;
    }

    public void setValue(final Float value) {
        this.value = value;
    }

    public void setReedemed(final Boolean reedemed) {
        isReedemed = reedemed;
    }


    // ----------------------------------------------------------------------------------
    //  Override
    // ----------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Voucher{" +
                "code=" + code +
                ", value=" + value +
                ", isReedemed=" + isReedemed +
                '}';
    }
}
