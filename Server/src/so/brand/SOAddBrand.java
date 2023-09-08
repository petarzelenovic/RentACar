/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.brand;

import baza.DBBroker;
import domain.AbstractDomainObject;
import domain.Brand;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Petar
 */
public class SOAddBrand extends AbstractSO {

    ArrayList<Brand> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Brand)) {
            throw new Exception("Objekat nije instanca klase Brand");
        }
        Brand b = (Brand) ado;
        ArrayList<Brand> brands = (ArrayList<Brand>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Brand brand : brands) {
            if (brand.getBrandModel().equals(b.getBrandModel())) {
                throw new Exception("Vec postoji taj model u bazi!");
            }
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }

}
