package edu.hm.bugcoin.service.Voucher;


import edu.hm.bugcoin.domain.Bankaccount;
import edu.hm.bugcoin.domain.Customer;
import edu.hm.bugcoin.domain.Voucher;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by shreaker on 14.10.16.
 * See: http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods
 */

/**
 * Queries related to the Voucher-Table.
 */
public interface VoucherRepository extends Repository<Voucher, Long>{

    // ----------------------------------------------------------------------------------
    //  Request
    // ----------------------------------------------------------------------------------
    Voucher findByCode(long code);

    List<Voucher> findAll();


    // ----------------------------------------------------------------------------------
    //  Update / Add
    // ----------------------------------------------------------------------------------
    Voucher saveAndFlush(Voucher voucher);


    // ----------------------------------------------------------------------------------
    //  Delete
    // ----------------------------------------------------------------------------------

}
