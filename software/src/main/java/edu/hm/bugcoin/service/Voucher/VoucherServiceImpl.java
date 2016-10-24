package edu.hm.bugcoin.service.Voucher;
/*
 * Created by shreaker on 18.10.16.
 */

import edu.hm.bugcoin.domain.Voucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


@Component
@Transactional
public class VoucherServiceImpl implements VoucherService {

    // ----------------------------------------------------------------------------------
    //  Member variable
    // ----------------------------------------------------------------------------------
    @Autowired
    private VoucherRepository voucherRepository;

    // ----------------------------------------------------------------------------------
    //  Request
    // ----------------------------------------------------------------------------------
    @Override
    public Voucher getVoucher(long code) {
        return voucherRepository.findByCode(code);
    }

    @Override
    public List<Voucher> getAllVouchers() {
        return voucherRepository.findAll();
    }


    @Override
    public boolean isVoucherValid(Voucher voucher){
        boolean isValid = true;
        if(voucher == null){
            isValid =false;
        }else{
            if(voucher.isReedemed())
                isValid = false;
        }
        return isValid;
    }
    // ----------------------------------------------------------------------------------
    //  Update / Add
    // ----------------------------------------------------------------------------------
    @Override
    public Voucher addNewVoucher(float value) {
        Assert.notNull(value, "Value must not be null");
        long newVoucherCode = getNewVoucherCode();
        return voucherRepository.saveAndFlush(new Voucher(newVoucherCode, value, false));
    }

    public long getNewVoucherCode() {
        long newVoucherNr;
        do {
            newVoucherNr = generateRandomVoucherNr();
        } while (!isVoucherCodeUnique(newVoucherNr));

        return newVoucherNr;
    }

    private long generateRandomVoucherNr() {
        final long VOUCHER_CODE_LOWER_BOUND = 1000000000000000000l;
        final long VOUCHER_CODE_UPPER_BOUND = Long.MAX_VALUE;
        long random = ThreadLocalRandom.current().nextLong(VOUCHER_CODE_LOWER_BOUND, VOUCHER_CODE_UPPER_BOUND);
        return random;
    }

    private boolean isVoucherCodeUnique(long voucherNr) {
        boolean isUnique = true;
        List<Voucher> vouchers = voucherRepository.findAll();
        for (Voucher voucher : vouchers) {
            if (voucher.getCode() == voucherNr) {
                isUnique = false;
                break;
            }
        }
        return isUnique;
    }

    // ----------------------------------------------------------------------------------
    //  Delete
    // ----------------------------------------------------------------------------------


}
