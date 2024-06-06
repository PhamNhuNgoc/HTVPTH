package dataprovider;

import org.testng.annotations.DataProvider;

public class HSoTLieuDataProvider{
    @DataProvider
    public Object[][] objLoaiHSo(){
        return new Object[][]{
                {"Tất cả"},
                {"Mới tạo"},
        };
    }

    @DataProvider
    public Object[][] objNamLapHSo(){
        return new Object[][]{
                {"2024"},
                {"2023"},
        };
    }

    @DataProvider
    public Object[][] objLinhVuc(){
        return new Object[][]{
                {"Tất cả"},
        };
    }

    @DataProvider
    public Object[][] objNoiTaoHSo(){
        return new Object[][]{
                {"P. Công Nghệ Phần Mềm"},
        };
    }

    @DataProvider
    public Object[][] objTieuDe(){
        return new Object[][]{
                {"Ble ble ble"},
        };
    }

    @DataProvider
    public Object[][] objFilterHSo(){
        return new Object[][]{
                {"Tất cả", "2023", "Tất cả", "P. Công Nghệ Phần Mềm", ""},
        };
    }

    @DataProvider
    public Object[][] objNhapTuExcel(){
        return new Object[][]{
                {"src\\test\\resources\\importedFiles\\ImportExcelTest.xlsx"},
        };
    }

    @DataProvider
    public Object[][] objThemMoiHSo(){
        return new Object[][]{
                {"04062024", "2024", "2", "4", "Tiêu Đề Hồ Sơ", "2", "46", "01/06/2024", "04/06/2024", "04/06/2024", "-Khoa học", "10 năm", "Mới", "Ghi Chú Hồ Sơ"},
                {"11112", "", "1", "100", "Tiêu Đề Hồ Sơ", "1", "111", "01/01/2024", "01/05/2024", "10/05/2024", "-Nghệ thuật", "20 năm", "Mới", "Ghi Chú Hồ Sơ"},
                {"11113", "111", "1", "100", "", "1", "111", "01/01/2024", "01/05/2024", "10/05/2024", "-Nghệ thuật", "20 năm", "Mới", "Ghi Chú Hồ Sơ"},
        };
    }

    @DataProvider
    public Object[][] objChinhSuaHSo(){
        return new Object[][]{
                {"11111", "222", "2", "200", "Tiêu Đề Hồ Sơ", "2", "222", "02/02/2024", "02/05/2024", "28/05/2024", "-Khoa học", "10 năm", "Mới", "Ghi Chú Hồ Sơ"},
        };
    }

    @DataProvider
    public Object[][] objNhapVBanTuExcel(){
        return new Object[][]{
                {"11111", "src\\test\\resources\\importedFiles\\MLVB VB-7577-7719-2019-1306.xlsx"},
        };
    }
    @DataProvider
    public Object[][] objXuatExcelDSachVBan() {
        return new Object[][]{
                {"2024.05.0007"},
        };
    }

    @DataProvider
    public Object[][] objThemVBan() {
        return new Object[][]{
                {"11111", "AAA", "Thành phố Hồ Chí Minh", "01/01/2024", "4", "Trích yếu văn bản", "Ghi chú văn bản", "src\\test\\resources\\importedFiles\\AQ10-Child.pdf"},
                {"11111", "AAAA", "Thành phố Hồ Chí Minh", "01/01/2024", "5", "Trích yếu văn bản", "Ghi chú văn bản", "src\\test\\resources\\importedFiles\\AQ10-Child.pdf"},
                {"11111", "BBBB", "Thành phố Hồ Chí Minh", "01/01/2024", "7", "Trích yếu văn bản", "Ghi chú văn bản", "src\\test\\resources\\importedFiles\\AQ10-Child.pdf"}
        };
    }

    @DataProvider
    public Object[][] objXoaVBan() {
        return new Object[][]{
                {"11111", "AAA", "Hủy"},
                {"11111", "AAA", "Xác nhận"},
        };
    }

    @DataProvider
    public Object[][] objChinhSuaVBan(){
        return new Object[][]{
                {"11111", "AAAA", "AAAAb", "Thành phố Hồ Chí Minh", "01/04/2024", "6", "Trích yếu văn bản b", "Ghi chú văn bản b", "src\\test\\resources\\importedFiles\\IT-Security-test-WS2020.pdf"},
        };
    }

    @DataProvider
    public Object[][] objThemFileVBan(){
        return new Object[][]{
                {"11111", "BBBB", "src\\test\\resources\\importedFiles\\IT-Security-test-WS2020.pdf"},
        };
    }

    @DataProvider
    public Object[][] objTaiVBan(){
        return new Object[][]{
                {"11111", "BBBB"},
        };
    }
}
