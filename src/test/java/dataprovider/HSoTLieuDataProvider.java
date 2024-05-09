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
                {"SPCIT.CNPM.2024.04.0001"},
        };
    }

    @DataProvider
    public Object[][] objFilterHSo(){
        return new Object[][]{
                {"Tất cả", "2023", "Tất cả", "P. Công Nghệ Phần Mềm", ""},
        };
    }
}
