package rootpackage.Pages;

import org.testng.annotations.DataProvider;

public class DataProviderClass{
    @DataProvider(name="SearchProvider")
    public static Object[][] getDataFromDataprovider(){
        return new Object[][]
                {
                        { "ha    noi", "×\nNot found" },
                        { "!<fjks", "×\nNot found" },
                        { "<script>alert(123)</script>", "×\nNot found" }
                };

    }
}
