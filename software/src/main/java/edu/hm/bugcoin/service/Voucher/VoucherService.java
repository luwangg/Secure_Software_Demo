package edu.hm.bugcoin.service.Voucher;
/*
 * Created by shreaker on 18.10.16.
 */

import edu.hm.bugcoin.domain.Bankaccount;
import edu.hm.bugcoin.domain.Voucher;

import java.util.List;


/**
 *
 */
public interface VoucherService {

    // ----------------------------------------------------------------------------------
    //  Request
    // ----------------------------------------------------------------------------------

    Voucher getVoucher(long code);

    List<Voucher> getAllVouchers();

    List<Voucher> getAllNotReedemedVouchers();

    List<Voucher> getAllReedemedVouchers();

    boolean isVoucherValid(Voucher voucher);

    // ----------------------------------------------------------------------------------
    //  Update / Add
    // ----------------------------------------------------------------------------------
    Voucher addNewVoucher(float value);


    // ----------------------------------------------------------------------------------
    //  Delete
    // ----------------------------------------------------------------------------------

}
