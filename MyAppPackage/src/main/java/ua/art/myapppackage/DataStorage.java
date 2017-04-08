package ua.art.myapppackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class DataStorage {
    private static final DataStorage DATA_STORAGE = new DataStorage();
    private List<String> appNameList;
    private List<MyApp> appList = new ArrayList<>(Arrays.asList(
            new MyApp(R.string.calculator, R.mipmap.calculator),
            new MyApp(R.string.currency_converter, R.mipmap.currency_converter)));

    private DataStorage() {
    }

    static DataStorage getDataStorage() {
        return DATA_STORAGE;
    }

    List<String> getAppNameList() {
        return appNameList;
    }

    void setAppNameList(List<String> appNameList) {
        this.appNameList = appNameList;
    }

    List<MyApp> getAppList() {
        return appList;
    }
}
