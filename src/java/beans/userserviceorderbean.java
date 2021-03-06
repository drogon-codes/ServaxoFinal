/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package beans;

import ejb.AdminLocal;
import ejb.UserLocal;
import entities.*;
import entities.TblServicecart;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author c computer
 */
@Named(value = "userserviceorderbean")
@SessionScoped
public class userserviceorderbean implements Serializable {

    @EJB UserLocal ul;
    @EJB AdminLocal al;
    Integer userId=3;
    Integer serviceId,qty=1,total,pincode,companyId,modelId;
    String vehicleNumber,address1,address2,landmark,remark;
    Collection<TblModel> model;
    //Getter setter thi baaki
    DateFormat df = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
    Date today = Calendar.getInstance().getTime();
    String datetime = df.format(today);
    
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Collection<TblModel> getModel() {
        return model;
    }

    public void setModel(Collection<TblModel> model) {
        this.model = model;
    }
    
    public String addToCart(Integer serviceId){
        this.ul.addToServiceCart(serviceId, userId, datetime, datetime);
        return "";
    }
    
    public Collection<TblServicecart> viewServiceCart(){
        return this.ul.viewServiceCart(userId);
    }
    
    public Collection<TblCompany> allCompanies(){
        return this.al.getAllCompanies();
    }
    
    public void dropCompany(AjaxBehaviorEvent event){
        model = al.getModelsByCompany(companyId);
    }
    
    public String placeOrder(){
        this.ul.addServiceOrder(userId, modelId, vehicleNumber, remark, datetime, address1, address2, pincode, landmark, "Pending", datetime, datetime);
        vehicleNumber = "";
        remark = "";
        address1="";
        address2="";
        pincode=null;
        landmark="";
        return "";
    }
    
    public Collection<TblServiceorder> allOrders(){
        return this.ul.getUserServiceOrders(userId);
    }
    public userserviceorderbean() {
    }
    
}
