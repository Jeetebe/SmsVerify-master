package com.noc.smsverify.object;

/**
 * Created by dung on 6/8/2017.
 */

public class ThongtinObj {

    public ThongtinObj() {
        pEZ        ="";
        pIMEI      ="";
        // --Loai: 1: dau noi=""; 2: dang ky thong tin=""; 3: thay doi thong tinpPhanLoai   NUMBER="";
        pPhanLoai=0;
        pShop_Code  ="";
        pEmployee   ="";
        pisdn       ="";
        pserial     ="";
        photen      ="";

        pNational   ="";
        pngaysinh   ="";
        int pGioiTinh   =0;
        pcmnd       ="";
        pNoiCapCMND ="";
        pNgayCapCMND ="";

        pmatinh      ="";
        pmahuyen     ="";
        pmaxa        ="";
        ptoap        ="";
        pduong       ="";
        psonha       ="";
        psdt_lienhe  ="";
        pEmail       ="";
        pdoituong    ="";

        pfile_cmnd1  ="";
        pfile_cmnd2  ="";
        pfile_hoso   ="";
        pfile_hinh   ="";
        //-- Thay doi thong tin moi co truong nay=""; noi dung xem o bang dnol_reason=""; daunoi va dktt Reasoncode = null
        pReasonCode  ="";
    }

    String pEZ        ;
    String pIMEI      ;
           // --Loai: 1: dau noi; 2: dang ky thong tin; 3: thay doi thong tinString pPhanLoai   NUMBER;
    int pPhanLoai;
    String pShop_Code  ;
    String pEmployee   ;
    String pisdn       ;
    String pserial     ;
    String photen      ;

    String pNational   ;
    String pngaysinh   ;
    int pGioiTinh   ;

    String pcmnd       ;
    String pNoiCapCMND ;
    String pNgayCapCMND ;

    String pmatinh      ;
    String pmahuyen     ;
    String pmaxa        ;
    String ptoap        ;
    String pduong       ;
    String psonha       ;
    String psdt_lienhe  ;
    String pEmail       ;
    String pdoituong    ;

    String pfile_cmnd1  ;
    String pfile_cmnd2  ;
    String pfile_hoso   ;
    String pfile_hinh   ;
    //-- Thay doi thong tin moi co truong nay; noi dung xem o bang dnol_reason; daunoi va dktt Reasoncode = null
    String pReasonCode  ;


    public int getpPhanLoai() {
        return pPhanLoai;
    }

    public void setpPhanLoai(int pPhanLoai) {
        this.pPhanLoai = pPhanLoai;
    }

    public String getpEZ() {
        return pEZ;
    }

    public void setpEZ(String pEZ) {
        this.pEZ = pEZ;
    }

    public String getpIMEI() {
        return pIMEI;
    }

    public void setpIMEI(String pIMEI) {
        this.pIMEI = pIMEI;
    }

    public String getpShop_Code() {
        return pShop_Code;
    }

    public void setpShop_Code(String pShop_Code) {
        this.pShop_Code = pShop_Code;
    }

    public String getpEmployee() {
        return pEmployee;
    }

    public void setpEmployee(String pEmployee) {
        this.pEmployee = pEmployee;
    }

    public String getPisdn() {
        return pisdn;
    }

    public void setPisdn(String pisdn) {
        this.pisdn = pisdn;
    }

    public String getPserial() {
        return pserial;
    }

    public void setPserial(String pserial) {
        this.pserial = pserial;
    }

    public String getPhoten() {
        return photen;
    }

    public void setPhoten(String photen) {
        this.photen = photen;
    }

    public String getpNational() {
        return pNational;
    }

    public void setpNational(String pNational) {
        this.pNational = pNational;
    }

    public String getPngaysinh() {
        return pngaysinh;
    }

    public void setPngaysinh(String pngaysinh) {
        this.pngaysinh = pngaysinh;
    }

    public int getpGioiTinh() {
        return pGioiTinh;
    }

    public void setpGioiTinh(int pGioiTinh) {
        this.pGioiTinh = pGioiTinh;
    }

    public String getPcmnd() {
        return pcmnd;
    }

    public void setPcmnd(String pcmnd) {
        this.pcmnd = pcmnd;
    }

    public String getpNoiCapCMND() {
        return pNoiCapCMND;
    }

    public void setpNoiCapCMND(String pNoiCapCMND) {
        this.pNoiCapCMND = pNoiCapCMND;
    }

    public String getpNgayCapCMND() {
        return pNgayCapCMND;
    }

    public void setpNgayCapCMND(String pNgayCapCMND) {
        this.pNgayCapCMND = pNgayCapCMND;
    }

    public String getPmatinh() {
        return pmatinh;
    }

    public void setPmatinh(String pmatinh) {
        this.pmatinh = pmatinh;
    }

    public String getPmahuyen() {
        return pmahuyen;
    }

    public void setPmahuyen(String pmahuyen) {
        this.pmahuyen = pmahuyen;
    }

    public String getPmaxa() {
        return pmaxa;
    }

    public void setPmaxa(String pmaxa) {
        this.pmaxa = pmaxa;
    }

    public String getPtoap() {
        return ptoap;
    }

    public void setPtoap(String ptoap) {
        this.ptoap = ptoap;
    }

    public String getPduong() {
        return pduong;
    }

    public void setPduong(String pduong) {
        this.pduong = pduong;
    }

    public String getPsonha() {
        return psonha;
    }

    public void setPsonha(String psonha) {
        this.psonha = psonha;
    }

    public String getPsdt_lienhe() {
        return psdt_lienhe;
    }

    public void setPsdt_lienhe(String psdt_lienhe) {
        this.psdt_lienhe = psdt_lienhe;
    }

    public String getpEmail() {
        return pEmail;
    }

    public void setpEmail(String pEmail) {
        this.pEmail = pEmail;
    }

    public String getPdoituong() {
        return pdoituong;
    }

    public void setPdoituong(String pdoituong) {
        this.pdoituong = pdoituong;
    }

    public String getPfile_cmnd1() {
        return pfile_cmnd1;
    }

    public void setPfile_cmnd1(String pfile_cmnd1) {
        this.pfile_cmnd1 = pfile_cmnd1;
    }

    public String getPfile_cmnd2() {
        return pfile_cmnd2;
    }

    public void setPfile_cmnd2(String pfile_cmnd2) {
        this.pfile_cmnd2 = pfile_cmnd2;
    }

    public String getPfile_hoso() {
        return pfile_hoso;
    }

    public void setPfile_hoso(String pfile_hoso) {
        this.pfile_hoso = pfile_hoso;
    }

    public String getPfile_hinh() {
        return pfile_hinh;
    }

    public void setPfile_hinh(String pfile_hinh) {
        this.pfile_hinh = pfile_hinh;
    }

    public String getpReasonCode() {
        return pReasonCode;
    }

    public void setpReasonCode(String pReasonCode) {
        this.pReasonCode = pReasonCode;
    }




    public ThongtinObj(String isdn) {
        pEZ        ="939700303";
        pIMEI      ="123";
        // --Loai: 1: dau noi=""; 2: dang ky thong tin=""; 3: thay doi thong tinpPhanLoai   NUMBER="";
        pPhanLoai=0;
        pShop_Code  ="1";
        pEmployee   ="1";
        pisdn       ="898028970";
        pserial     ="1";
        photen      ="test";

        pNational   ="";
        pngaysinh   ="01/01/2001";
        int pGioiTinh   =0;
        pcmnd       ="1";
        pNoiCapCMND ="";
        pNgayCapCMND ="01/01/2001";

        pmatinh      ="";
        pmahuyen     ="";
        pmaxa        ="";
        ptoap        ="";
        pduong       ="";
        psonha       ="";
        psdt_lienhe  ="";
        pEmail       ="";
        pdoituong    ="";

        pfile_cmnd1  ="";
        pfile_cmnd2  ="";
        pfile_hoso   ="";
        pfile_hinh   ="";
        //-- Thay doi thong tin moi co truong nay=""; noi dung xem o bang dnol_reason=""; daunoi va dktt Reasoncode = null
        pReasonCode  ="";
    }
}
