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
                {""},
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
//                {"11111", "111", "1", "100", "Tiêu Đề Hồ Sơ", "1", "111", "01/01/2024", "01/05/2024", "10/05/2024", "-Nghệ thuật", "20 năm", "Mới", "Ghi Chú Hồ Sơ"},
                {"11111", "", "1", "100", "Tiêu Đề Hồ Sơ", "1", "111", "01/01/2024", "01/05/2024", "10/05/2024", "-Nghệ thuật", "20 năm", "Mới", "Ghi Chú Hồ Sơ"},
                {"11111", "111", "1", "100", "", "1", "111", "01/01/2024", "01/05/2024", "10/05/2024", "-Nghệ thuật", "20 năm", "Mới", "Ghi Chú Hồ Sơ"},
        };
    }
}
