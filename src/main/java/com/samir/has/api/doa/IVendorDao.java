package com.samir.has.api.doa;


import com.samir.has.api.object.person.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IVendorDao extends JpaRepository<Vendor, Long> {
}
